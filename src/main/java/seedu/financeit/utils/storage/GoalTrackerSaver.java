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

    private static GoalTrackerSaver saver;

    private GoalTrackerSaver() {
        super();
    }

    private GoalTrackerSaver(String directory, String filepath) {
        super(directory, filepath);
    }

    public static GoalTrackerSaver getInstance(String... paths) {
        if (saver == null) {
            if (paths.length == 2) {
                saver = new GoalTrackerSaver(paths[0], paths[1]);
            } else {
                saver = new GoalTrackerSaver();
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
        while (scanner.hasNext()) {
            String saveString = scanner.nextLine();
            classContents = saveString.split(";");
            GoalTracker.setGoals(classContents[1], classContents[0], classContents[2]);
        }
    }
}
