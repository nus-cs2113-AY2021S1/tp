package seedu.financeit.utils.storage;

import seedu.financeit.common.CategoryMap;
import seedu.financeit.common.Constants;
import seedu.financeit.manualtracker.Ledger;
import seedu.financeit.manualtracker.LedgerList;
import seedu.financeit.manualtracker.ManualTracker;
import seedu.financeit.manualtracker.subroutine.Entry;
import seedu.financeit.manualtracker.subroutine.EntryList;
import seedu.financeit.manualtracker.subroutine.EntryTracker;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.UiManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SaveStateHandlerManualTracker extends SaveStateHandler {

    public SaveStateHandlerManualTracker() {
        super();
    }

    public SaveStateHandlerManualTracker(String filepath, String directory) {
        super(filepath, directory);
    }

    public void save() throws IOException {
        buildFile();
        LedgerList ledList = ManualTracker.getLedgerList();
        StringBuilder saveString = new StringBuilder();
        int size = ledList.getItemsSize();
        for (int i = 0; i < size; i++) {
            Ledger ledger = (Ledger) ledList.getItemAtIndex(i);
            saveString.append(this.getSaveString(ledger));
            EntryList entryList = ledger.entryList;
            int entryListSize = entryList.getItemsSize();
            for (int x = 0; x < entryListSize; x++) {
                Entry ent = (Entry) entryList.getItemAtIndex(x);
                saveString.append(this.getSaveString(ent));
            }
        }
        FileWriter fileWriter = new FileWriter(fullPath);
        fileWriter.write(String.valueOf(saveString));
        fileWriter.close();
    }

    public void load() throws IOException {
        buildFile();
        File file = new File(fullPath);
        Scanner scanner = new Scanner(file);
        InputParser in = new InputParser();
        String[] classContents;
        String inputString = "";
        int ledgerIndex = -1;
        while (scanner.hasNext()) {
            String saveString = scanner.nextLine();
            classContents = saveString.split(";");
            switch (classContents[0]) {
            case "Entry":
                if (classContents[1].equals("Expense")) {
                    classContents[1] = " -e";
                } else if (classContents[1].equals("Income")) {
                    classContents[1] = " -i";
                }
                classContents[2] = CategoryMap.categoryToInputMap.get(classContents[2]);
                EntryTracker.setCurrLedger((Ledger) ManualTracker.getLedgerList().getItemAtIndex(ledgerIndex));
                inputString = "new /time " + classContents[4] + " /cat "
                    + classContents[2] + " /desc " + classContents[5] + " /amt "
                    + classContents[3] + classContents[1];
                EntryTracker.setCommandPacket(in.parseInput(inputString));
                EntryTracker.createEntry();
                break;
            case "Ledger":
                inputString = "new /date " + classContents[1];
                ManualTracker.setCommandPacket(in.parseInput(inputString));
                ManualTracker.createLedger();
                ledgerIndex++;
                break;
            default:
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    "Class is not recognised to load.");
                break;
            }
        }
    }
}
