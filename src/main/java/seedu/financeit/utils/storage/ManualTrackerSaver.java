package seedu.financeit.utils.storage;

import seedu.financeit.common.CategoryMap;
import seedu.financeit.common.Common;
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

    public static void clear() {
        ManualTracker.getLedgerList().removeAllItems();
    }

    /**
     * This method obtains ledger list from manual tracker and use this list to capture all objects
     * in the list in String format and store them in ./data/saveMt.txt
     * @param paths Can be called with no param or 2 params depending on whether you wish to specify
     *              a directory path and a file path or use the default paths.
     */
    public void save(String... paths) {
        try {
            if (paths.length == 2) {
                buildFile(paths[0], paths[1]);
            } else {
                buildFile();
            }
            LedgerList ledList = ManualTracker.getLedgerList();
            StringBuilder saveString = new StringBuilder();
            int size = ledList.getListSize();
            for (int i = 0; i < size; i++) {
                Ledger ledger = (Ledger) ledList.getItemAtIndex(i);
                saveString.append(getSaveString(ledger));
                EntryList entryList = ledger.entryList;
                int entryListSize = entryList.getListSize();
                for (int x = 0; x < entryListSize; x++) {
                    Entry ent = (Entry) entryList.getItemAtIndex(x);
                    saveString.append(getSaveString(ent));
                }
            }
            FileWriter fileWriter = new FileWriter(paths.length == 2 ? paths[1] : fullPath);
            fileWriter.write(String.valueOf(saveString));
            fileWriter.close();
        } catch (Exception e) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    "Manual Tracker dynamic save failed: " + e);
            e.printStackTrace();
        }
    }

    /**
     * This method reads from default or specified save file and rearrange the inputs from each line
     * into the correct format to be parsed by parseInput(). parseInput() will provide command packet
     * that is used to create each entry or ledger.
     * @param paths Can be called with no param or 2 params depending on whether you wish to specify
     *              a directory path and a file path or use the default paths.
     */
    public void load(String... paths) {
        try {
            Scanner scanner = null;
            if (paths.length == 2) {
                buildFile(paths[0], paths[1]);
            } else {
                buildFile();
            }
            File file = new File(paths.length == 2 ? paths[1] : fullPath);
            scanner = new Scanner(file);

            String[] classContents;
            String inputString = "";
            int ledgerIndex = -1;
            int line = 0;
            while (scanner.hasNext()) {
                try {
                    String saveString = scanner.nextLine().trim();
                    line++;
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
                        UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                                "Class is not recognised to load.");
                        break;
                    }
                } catch (Exception e) {
                    UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                            "saveMt.txt line " + line + " failed to load: " + e);
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    "Manual Tracker load failed: " + e);
            e.printStackTrace();
        }
    }
}
