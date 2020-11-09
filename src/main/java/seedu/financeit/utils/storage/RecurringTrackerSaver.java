package seedu.financeit.utils.storage;

import seedu.financeit.common.Common;
import seedu.financeit.datatrackers.recurringtracker.RecurringEntry;
import seedu.financeit.datatrackers.recurringtracker.RecurringEntryList;
import seedu.financeit.datatrackers.recurringtracker.RecurringTracker;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.UiManager;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

//@@author Feudalord
public class RecurringTrackerSaver extends SaveHandler {

    private static RecurringTrackerSaver saver;

    private RecurringTrackerSaver() {
        super();
    }

    private RecurringTrackerSaver(String directory, String filepath) {
        super(directory, filepath);
    }

    public static RecurringTrackerSaver getInstance(String... paths) {
        if (saver == null) {
            if (paths.length == 2) {
                saver = new RecurringTrackerSaver(paths[0], paths[1]);
            } else {
                saver = new RecurringTrackerSaver();
            }
        }
        return saver;
    }

    public static void clear() {
        RecurringTracker.getEntries().removeAllItems();
    }

    /**
     * This method obtains RecurringEntryList from RecurringTracker and use that to get a list of
     * entries to be stored onto a text file from the default or specified location.
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
            RecurringEntryList entries = RecurringTracker.getEntries();
            StringBuilder saveString = new StringBuilder();
            int size = entries.getListSize();
            for (int i = 0; i < size; i++) {
                RecurringEntry entry = (RecurringEntry) entries.getItemAtIndex(i);
                saveString.append(entry.toSave() + System.lineSeparator());
            }
            FileWriter fileWriter = new FileWriter(paths.length == 2 ? paths[1] : fullPath);
            fileWriter.write(String.valueOf(saveString));
            fileWriter.close();
        } catch (Exception e) {

            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    "Recurring Tracker dynamic save failed: " + e);
            e.printStackTrace();
        }
    }

    /**
     * This method reads from default or specified text file and the contents are converted into
     * the correct format to be parsed by parseInput and the resulting command packet is used to
     * load each entry.
     * @param paths Can be called with no param or 2 params depending on whether you wish to specify
     *              a directory path and a file path or use the default paths.
     */
    public void load(String... paths) {
        try {
            if (paths.length == 2) {
                buildFile(paths[0], paths[1]);
            } else {
                buildFile();
            }
            File file = new File(paths.length == 2 ? paths[1] : fullPath);
            Scanner scanner = new Scanner(file);
            String[] classContents;
            String inputString;
            String incomeExpense;
            int line = 0;
            while (scanner.hasNext()) {
                try {
                    String saveString = scanner.nextLine();
                    line++;
                    classContents = saveString.split(">&@#<");
                    if (!classContents[2].equals("")) {
                        classContents[2] = classContents[2].substring(2, classContents[2].length() - 2);
                        incomeExpense = "-e ";
                    } else {
                        classContents[3] = classContents[3].substring(2, classContents[3].length() - 2);
                        incomeExpense = "-i ";
                    }

                    if (classContents[5].equals("Auto deduction")) {
                        classContents[5] = "-auto ";
                    } else {
                        classContents[5] = "";
                    }
                    inputString = "add " + incomeExpense + classContents[5] + "/desc " + classContents[1]
                            + " /amt " + classContents[2] + classContents[3] + " /day " + classContents[0];

                    if (classContents.length == 7) {
                        inputString += " /notes " + classContents[6];
                    }
                    RecurringTracker.loadEntry(InputParser.getInstance().parseInput(inputString));
                } catch (Exception e) {
                    UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                            "saveAt.txt line " + line + " failed to load: " + e);
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    "Recurring Tracker load failed: " + e);
            e.printStackTrace();
        }
    }
}
