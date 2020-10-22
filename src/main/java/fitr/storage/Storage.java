package fitr.storage;

import fitr.Goal;
import fitr.list.GoalList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Handles the loading and saving of data to a file.
 */
public class Storage {
    private static final String DEFAULT_GOAL_LIST_FILEPATH = "goals.txt";
    private static final String TIP_LIST_FILEPATH = "src/main/resources/tips.txt";
    private static final String COMMA_SEPARATOR = ",";

    private static final Logger LOGGER = Logger.getLogger(Storage.class.getName());

    private final String goalListPath;

    /**
     * Set up the files required in the application, by creating the files if the files do not exist and
     * setting the file paths.
     *
     * @param goalListPath file path of the goal list
     * @throws IOException if an I/O error has occurred
     */
    public Storage(String goalListPath) throws IOException {
        this.goalListPath = goalListPath;

        File goalListFile = new File(goalListPath);
        if (!goalListFile.exists()) {
            goalListFile.createNewFile();
            LOGGER.fine("Goal list file created: " + goalListPath);
        }
    }

    public Storage() throws IOException {
        this(DEFAULT_GOAL_LIST_FILEPATH);
    }

    /**
     * Loads the user's goals from a file and returns an ArrayList of Goal objects.
     *
     * @return an ArrayList of Goal objects
     * @throws FileNotFoundException if the file is not found
     */
    public ArrayList<Goal> loadGoalList() throws FileNotFoundException {
        LOGGER.fine("Attempting to read file: " + goalListPath);
        ArrayList<Goal> goalList = new ArrayList<>();
        String line;
        String[] arguments;
        File goalListFile = new File(goalListPath);
        Scanner readFile = new Scanner(goalListFile);

        while (readFile.hasNext()) {
            line = readFile.nextLine();
            arguments = line.split(COMMA_SEPARATOR);
            goalList.add(new Goal(arguments[0], arguments[1]));
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
    public void writeGoalList(GoalList goalList) throws IOException {
        LOGGER.fine("Attempting to write to file: " + goalListPath);
        FileWriter fileWriter = new FileWriter(goalListPath);
        Goal goal;

        for (int i = 0; i < goalList.getSize(); i++) {
            goal = goalList.getGoal(i);
            fileWriter.write(goal.getGoalType()
                    + COMMA_SEPARATOR + goal.getDescription() + System.lineSeparator());
        }

        LOGGER.fine("Goal list file written successfully.");
        fileWriter.close();
    }

    /**
     * Loads the tips from a file and returns an ArrayList of String tips.
     *
     * @return an ArrayList of String tips
     * @throws IOException if an I/O error has occurred
     */
    public ArrayList<String> loadTipList() throws IOException {
        LOGGER.fine("Attempting to read file: " + TIP_LIST_FILEPATH);
        ArrayList<String> tipList = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(TIP_LIST_FILEPATH));
        String line;
        while ((line = br.readLine()) != null) {
            tipList.add(line);
        }

        LOGGER.fine("Tip list file written successfully.");
        return tipList;

    }

}
