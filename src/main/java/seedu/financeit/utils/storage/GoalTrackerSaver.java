package seedu.financeit.utils.storage;

import seedu.financeit.common.Common;
import seedu.financeit.data.Goal;
import seedu.financeit.datatrackers.goaltracker.GoalTracker;
import seedu.financeit.datatrackers.goaltracker.TotalGoalList;
import seedu.financeit.ui.UiManager;

import java.io.File;
import java.io.FileWriter;
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

    public static void clear() {
        GoalTracker.getTotalGoalList().getGoal().clear();
    }

    /**
     * This method reads items in TotalGoaList onto a text file from the default or specified
     * file path.
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
        } catch (Exception e) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    "Goal Tracker dynamic save failed: " + e);
            e.printStackTrace();
        }
    }

    /**
     * This method read from default or specified file and pass the contents into setGoals().
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
            int line = 0;
            while (scanner.hasNext()) {
                try {
                    String saveString = scanner.nextLine().trim();
                    line++;
                    classContents = saveString.split(";");
                    GoalTracker.setGoals(classContents[1], classContents[0], classContents[2]);
                } catch (Exception e) {
                    UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                            "saveGt.txt line " + line + " failed to load: " + e);
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    "Goal Tracker load failed: " + e);
            e.printStackTrace();
        }
    }
}
