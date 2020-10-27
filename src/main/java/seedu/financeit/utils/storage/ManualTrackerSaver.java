package seedu.financeit.utils.storage;

import seedu.financeit.common.CategoryMap;
import seedu.financeit.common.Constants;
import seedu.financeit.datatrackers.entrytracker.Entry;
import seedu.financeit.datatrackers.entrytracker.EntryList;
import seedu.financeit.datatrackers.entrytracker.EntryTracker;
import seedu.financeit.datatrackers.manualtracker.Ledger;
import seedu.financeit.datatrackers.manualtracker.LedgerList;
import seedu.financeit.datatrackers.manualtracker.ManualTracker;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.UiManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//@@author Feudalord
public class ManualTrackerSaver extends SaveHandler {

    private static ManualTrackerSaver saver;

    private ManualTrackerSaver() {
        super();
    }

    private ManualTrackerSaver(String directory, String filepath) {
        super(directory, filepath);
    }

    public static ManualTrackerSaver getInstance(String... paths) {
        if (saver == null) {
            if (paths.length == 2) {
                saver = new ManualTrackerSaver(paths[0], paths[1]);
            } else {
                saver = new ManualTrackerSaver();
            }
        }
        return saver;
    }

    public void save(String... paths) throws IOException {
        if (paths.length == 2) {
            buildFile(paths[0], paths[1]);
        } else {
            buildFile();
        }
        LedgerList ledList = ManualTracker.getLedgerList();
        StringBuilder saveString = new StringBuilder();
        int size = ledList.getItemsSize();
        for (int i = 0; i < size; i++) {
            Ledger ledger = (Ledger) ledList.getItemAtCurrIndex(i);
            saveString.append(getSaveString(ledger));
            EntryList entryList = ledger.entryList;
            int entryListSize = entryList.getItemsSize();
            for (int x = 0; x < entryListSize; x++) {
                Entry ent = (Entry) entryList.getItemAtCurrIndex(x);
                saveString.append(getSaveString(ent));
            }
        }
        FileWriter fileWriter = new FileWriter(paths.length == 2 ? paths[1] : fullPath);
        fileWriter.write(String.valueOf(saveString));
        fileWriter.close();
    }

    public void load(String... paths) throws IOException {
        if (paths.length == 2) {
            buildFile(paths[0], paths[1]);
        } else {
            buildFile();
        }
        File file = new File(paths.length == 2 ? paths[1] : fullPath);
        Scanner scanner = new Scanner(file);
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
                EntryTracker.setCurrLedger((Ledger) ManualTracker.getLedgerList().getItemAtCurrIndex(ledgerIndex));
                inputString = "new /time " + classContents[4] + " /cat "
                        + classContents[2] + " /desc " + classContents[5] + " /amt "
                        + classContents[3] + classContents[1];
                EntryTracker.setCommandPacket(InputParser.getInstance().parseInput(inputString));
                EntryTracker.createEntry();
                break;
            case "Ledger":
                inputString = "new /date " + classContents[1];
                ManualTracker.setCommandPacket(InputParser.getInstance().parseInput(inputString));
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
