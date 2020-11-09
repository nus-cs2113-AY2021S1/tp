package fitr.storage;

import fitr.common.DateManager;
import fitr.goal.Goal;
import fitr.exception.InvalidFileFormatException;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.list.GoalList;
import fitr.ui.Ui;
import fitr.user.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import static fitr.common.Messages.KEYWORD_EXERCISE_LESS_THAN;
import static fitr.common.Messages.KEYWORD_EXERCISE_MORE_THAN;
import static fitr.common.Messages.KEYWORD_FOOD_LESS_THAN;
import static fitr.common.Messages.KEYWORD_FOOD_MORE_THAN;
import static fitr.common.Messages.SPLIT_SPACE;
import static fitr.common.Messages.SYMBOL_EXERCISE;
import static fitr.common.Messages.SYMBOL_FOOD;
import static fitr.common.Messages.SYMBOL_NO;
import static fitr.common.Messages.SYMBOL_YES;
import static fitr.storage.StorageManager.COMMA_SEPARATOR;

public class GoalStorage {
    private static final Logger LOGGER = Logger.getLogger(GoalStorage.class.getName());
    private static final String DEFAULT_GOAL_LIST_PATH = "goalList.txt";
    private final String goalListPath;

    public GoalStorage() throws IOException {
        this(DEFAULT_GOAL_LIST_PATH);
    }

    public GoalStorage(String goalListPath) throws IOException {
        this.goalListPath = goalListPath;
        File goalListFile = new File(goalListPath);

        if (!goalListFile.exists()) {
            boolean isFileExists = goalListFile.createNewFile();
            LOGGER.fine("Goal list file created: " + goalListPath);
        }
    }

    /**
     * Loads the user's goals from a file and returns an ArrayList of Goal objects.
     *
     * @return an ArrayList of Goal objects
     * @throws FileNotFoundException if the file is not found
     */
    public ArrayList<Goal> loadGoalList() throws FileNotFoundException, ArrayIndexOutOfBoundsException {
        LOGGER.fine("Attempting to read file: " + goalListPath);
        ArrayList<Goal> goalList = new ArrayList<>();
        String line;
        String[] arguments;
        File goalListFile = new File(goalListPath);
        Scanner readFile = new Scanner(goalListFile);
        boolean hasUnrecognisedGoals = false;

        while (readFile.hasNext()) {
            line = readFile.nextLine();
            arguments = line.split(COMMA_SEPARATOR);

            if (isValidGoalLine(arguments)) {
                goalList.add(new Goal(LocalDate.parse(arguments[0], DateManager.formatter),
                        arguments[1], arguments[2], arguments[3]));
            } else {
                LOGGER.fine("Invalid entry found in goal list file.");
                hasUnrecognisedGoals = true;
            }
        }

        if (hasUnrecognisedGoals) {
            Ui.printCustomError("Unrecognised goals were not added to your goal list!");
        }

        LOGGER.fine("Goal list file read successfully.");
        return goalList;
    }

    /**
     * Writes the goal list data into a file.
     *
     * @param goalList the goal list to write to the file
     * @throws IOException if an I/O error has occurred
     */
    public void writeGoalList(GoalList goalList, FoodList foodList, ExerciseList exerciseList,
                              User user) throws IOException {
        LOGGER.fine("Attempting to write to file: " + goalListPath);
        FileWriter fileWriter = new FileWriter(goalListPath);
        Goal goal;

        assert user != null : "user cannot be null!";
        assert goalList != null : "goalList cannot be null!";
        assert foodList != null : "foodList cannot be null!";
        assert exerciseList != null : "exerciseList cannot be null!";

        for (int i = 0; i < goalList.getSize(); i++) {
            goal = goalList.getGoal(i);
            fileWriter.write(goal.getCreatedDate()
                    + COMMA_SEPARATOR + goal.getGoalType()
                    + COMMA_SEPARATOR + goal.getStatus(goal,foodList, exerciseList, user)
                    + COMMA_SEPARATOR + goal.getDescription() + System.lineSeparator());
        }

        LOGGER.fine("Goal list file written successfully.");
        fileWriter.close();
    }

    private static boolean isValidGoalLine(String[] arguments) {
        boolean isValidGoalType = false;
        boolean isValidStatus = false;

        if (arguments.length != 4) {
            return false;
        }
        try {
            String createdDate = arguments[0];
            String goalType = arguments[1];
            String goalStatus = arguments[2];
            String goalDescription = arguments[3];
            //Check if goal type is valid (E or F)
            if (goalType.equals(SYMBOL_EXERCISE) || goalType.equals(SYMBOL_FOOD)) {
                isValidGoalType = true;
            }
            //Check if status is valid (Y or N or 0.0 < status < 100.0)
            if (goalStatus.equals(SYMBOL_YES) || goalStatus.equals(SYMBOL_NO)) {
                isValidStatus = isValidSmartGoalCalorie(goalDescription);
            } else if (Double.parseDouble(goalStatus.substring(0, arguments[2].length() - 1)) >= 0.0
                    && Double.parseDouble(goalStatus.substring(0, arguments[2].length() - 1)) <= 100) {
                isValidStatus = isValidSmartGoalCalorie(goalDescription);
            }

            LocalDate date = LocalDate.parse(createdDate, DateManager.formatter);

            return (isValidGoalType && isValidStatus);
        } catch (NumberFormatException | DateTimeException | StringIndexOutOfBoundsException e) {
            return false;
        }
    }

    private static boolean isValidSmartGoalCalorie(String goalDescription) throws NumberFormatException {
        if (goalDescription.contains(KEYWORD_FOOD_LESS_THAN)
                || goalDescription.contains(KEYWORD_EXERCISE_LESS_THAN)) {
            return !((Integer.parseInt(goalDescription.split(SPLIT_SPACE)[3]) >= 100000
                    || Integer.parseInt(goalDescription.split(SPLIT_SPACE)[3]) <= 0));
        } else if (goalDescription.contains(KEYWORD_FOOD_MORE_THAN)
                || goalDescription.contains(KEYWORD_EXERCISE_MORE_THAN)) {
            return !((Integer.parseInt(goalDescription.split(SPLIT_SPACE)[3]) >= 100000)
                    || (Integer.parseInt(goalDescription.split(SPLIT_SPACE)[3]) < 0)
                    || ((goalDescription.split(SPLIT_SPACE)[3]).equals("-0")));
        }
        return true;
    }
}
