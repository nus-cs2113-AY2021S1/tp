package seedu.financeit.utils.storage;

import seedu.financeit.common.Goal;
import seedu.financeit.goaltracker.GoalTracker;
import seedu.financeit.goaltracker.TotalGoalList;

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
        TotalGoalList goalList = GoalTracker.getTotalGoalList();
        String cat;
        Goal goal;
        for (int i = 0; i < goalList.getListSize(); i++) {
            goal = goalList.getGoal().get(i);
            cat = goal.getCategory();
            if (cat.equals("Expense")){
                saveString.append("Expense;" + goal.getExpenseGoal() + ";" + goal.getExpenseMonth() + System.lineSeparator());
            } else {
                saveString.append("Income;" + goal.getIncomeGoal() + ";" + goal.getIncomeMonth() + System.lineSeparator());
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
        String[] classContents;
        while (scanner.hasNext()) {
            String saveString = scanner.nextLine();
            classContents = saveString.split(";");
            GoalTracker.setGoals(classContents[1], classContents[0], classContents[2]);
        }
    }
}
