package seedu.financeit.utils.storage;

import seedu.financeit.parser.InputParser;
import seedu.financeit.datatrackers.recurringtracker.RecurringEntry;
import seedu.financeit.datatrackers.recurringtracker.RecurringEntryList;
import seedu.financeit.datatrackers.recurringtracker.RecurringTracker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//@@author Feudalord
public class RecurringTrackerSaver extends SaveHandler {

    public RecurringTrackerSaver() {
        super();
    }

    public RecurringTrackerSaver(String filepath, String directory) {
        super(filepath, directory);
    }

    public void save() throws IOException {
        buildFile();
        RecurringEntryList entries = RecurringTracker.getEntries();
        StringBuilder saveString = new StringBuilder();
        int size = entries.getItemsSize();
        for (int i = 0; i < size; i++) {
            RecurringEntry entry = (RecurringEntry) entries.getItemAtCurrIndex(i);
            saveString.append(entry.toString() + System.lineSeparator());
        }
        FileWriter fileWriter = new FileWriter(fullPath);
        fileWriter.write(String.valueOf(saveString));
        fileWriter.close();
    }

    public void load() throws IOException {
        buildFile();
        File file = new File(fullPath);
        Scanner scanner = new Scanner(file);
        String[] classContents;
        String inputString;
        String incomeExpense;
        while (scanner.hasNext()) {
            String saveString = scanner.nextLine();
            classContents = saveString.split(";");
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
                + " /amt " + classContents[2] + classContents[3] + " /day " + classContents[0]
                + " /notes " + classContents[6];
            RecurringTracker.loadEntry(InputParser.getInstance().parseInput(inputString));
        }
    }
}
