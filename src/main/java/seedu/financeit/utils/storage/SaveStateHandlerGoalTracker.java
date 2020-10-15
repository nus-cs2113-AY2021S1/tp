package seedu.financeit.utils.storage;

import seedu.financeit.goaltracker.GoalTracker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//@@author Feudalord
public class SaveStateHandlerGoalTracker extends SaveStateHandler {

    public SaveStateHandlerGoalTracker() {
        super();
    }

    public SaveStateHandlerGoalTracker(String filepath, String directory) {
        super(filepath, directory);
    }

    public void save() throws IOException {
        buildFile();
        StringBuilder saveString = new StringBuilder();
        saveString.append(GoalTracker.getExpenseGoal() + System.lineSeparator());
        saveString.append(GoalTracker.getIncomeGoal());
        FileWriter fileWriter = new FileWriter(fullPath);
        fileWriter.write(String.valueOf(saveString));
        fileWriter.close();
    }

    public void load() throws IOException {
        buildFile();
        File file = new File(fullPath);
        Scanner scanner = new Scanner(file);
        int expense = Integer.parseInt(scanner.nextLine());
        int income = Integer.parseInt(scanner.nextLine());
        GoalTracker.setGoals(expense, income);
    }
}
