package fitr.storage;

import fitr.common.DateManager;
import fitr.goal.Goal;
import fitr.exception.InvalidFileFormatException;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.list.GoalList;
import fitr.user.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import static fitr.storage.StorageManager.COMMA_SEPARATOR;

public class GoalStorage {
    private static final Logger LOGGER = Logger.getLogger(GoalStorage.class.getName());
    private static final String DEFAULT_GOAL_LIST_PATH = "goals.txt";
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
    public ArrayList<Goal> loadGoalList() throws FileNotFoundException, ArrayIndexOutOfBoundsException,
            InvalidFileFormatException {
        LOGGER.fine("Attempting to read file: " + goalListPath);
        ArrayList<Goal> goalList = new ArrayList<>();
        String line;
        String[] arguments;
        File goalListFile = new File(goalListPath);
        Scanner readFile = new Scanner(goalListFile);

        while (readFile.hasNext()) {
            line = readFile.nextLine();
            arguments = line.split(COMMA_SEPARATOR);

            if (arguments.length != 4) {
                throw new InvalidFileFormatException();
            }
            goalList.add(new Goal(LocalDate.parse(arguments[0], DateManager.formatter),
                    arguments[1], arguments[2], arguments[3]));
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
}
