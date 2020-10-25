package seedu.financeit.utils.storage;

import seedu.financeit.data.Goal;
import seedu.financeit.datatrackers.goaltracker.GoalTracker;
import seedu.financeit.datatrackers.goaltracker.TotalGoalList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//@@author Feudalord
public class GoalTrackerSaver extends SaveHandler {

    public GoalTrackerSaver() {
        super();
    }

    public GoalTrackerSaver(String filepath, String directory) {
        super(filepath, directory);
    }

    public void save() throws IOException {
        buildFile();
        StringBuilder saveString = new StringBuilder();
        TotalGoalList goalList = GoalTracker.getTotalGoalList();
        String cat;
        Goal go;
        for (int i = 0; i < goalList.getListSize(); i++) {
            go = goalList.getGoal().get(i);
            cat = go.getCategory();
            if (cat.equals("Expense")) {
                saveString.append(cat + ";" + go.getExpenseGoal() + ";" + go.getExpenseMonth() + "\n");
            } else {
                saveString.append(cat + ";" + go.getIncomeGoal() + ";" + go.getIncomeMonth() + "\n");
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
