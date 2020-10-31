package seedu.financeit.utils.storage;

import seedu.financeit.datatrackers.recurringtracker.RecurringEntry;
import seedu.financeit.datatrackers.recurringtracker.RecurringEntryList;
import seedu.financeit.datatrackers.recurringtracker.RecurringTracker;
import seedu.financeit.parser.InputParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//@@author Feudalord
public class AutoTrackerSaver extends SaveHandler {

    private static AutoTrackerSaver saver;

    private AutoTrackerSaver() {
        super();
    }

    private AutoTrackerSaver(String directory, String filepath) {
        super(directory, filepath);
    }

    public static AutoTrackerSaver getInstance(String... paths) {
        if (saver == null) {
            if (paths.length == 2) {
                saver = new AutoTrackerSaver(paths[0], paths[1]);
            } else {
                saver = new AutoTrackerSaver();
            }
        }
        return saver;
    }

    public static void clear() {
        RecurringTracker.getEntries().removeAllItems();
    }

    public void save(String... paths) throws IOException {
        if (paths.length == 2) {
            buildFile(paths[0], paths[1]);
        } else {
            buildFile();
        }
        RecurringEntryList entries = RecurringTracker.getEntries();
        StringBuilder saveString = new StringBuilder();
        int size = entries.getItemsSize();
        for (int i = 0; i < size; i++) {
            RecurringEntry entry = (RecurringEntry) entries.getItemAtCurrIndex(i);
            saveString.append(entry.toSave() + System.lineSeparator());
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
        String inputString;
        String incomeExpense;
        while (scanner.hasNext()) {
            String saveString = scanner.nextLine();
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
        }
    }
}
