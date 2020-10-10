package seedu.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the loading and saving of data to a file.
 */
public class Storage {
    private static final String DEFAULT_EXERCISE_LIST_FILEPATH = "exercises.txt";
    private static final String DEFAULT_FOOD_LIST_FILEPATH = "food.txt";
    private static final String DEFAULT_USER_CONFIG_FILEPATH = "user.txt";
    private static final String COMMA_SEPARATOR = ",";

    private final String exerciseListPath;
    private final String foodListPath;
    private final String userConfigPath;

    /**
     * Creates 2 new files if they do not exist.
     *
     * @throws IOException if an I/O error has occurred
     */
    public Storage(String userConfigPath, String foodListPath, String exerciseListPath) throws IOException {
        this.userConfigPath = userConfigPath;
        this.foodListPath = foodListPath;
        this.exerciseListPath = exerciseListPath;

        File exerciseListFile = new File(exerciseListPath);
        File foodListFile = new File(foodListPath);
        File userConfigFile = new File(userConfigPath);

        if (!exerciseListFile.exists()) {
            exerciseListFile.createNewFile();
        }

        if (!foodListFile.exists()) {
            foodListFile.createNewFile();
        }

        if (!userConfigFile.exists()) {
            userConfigFile.createNewFile();
        }
    }

    public Storage() throws IOException {
        this(DEFAULT_USER_CONFIG_FILEPATH, DEFAULT_FOOD_LIST_FILEPATH, DEFAULT_EXERCISE_LIST_FILEPATH);
    }

    /**
     * Read's the user's data from the user config file.
     *
     * @throws FileNotFoundException if the file is not found
     */
    public void readUserConfigFile(User user) throws FileNotFoundException {
        File file = new File(userConfigPath);
        Scanner readFile = new Scanner(file);
        String line, name, gender;
        int age;
        double height, weight;
        String[] arguments;

        while (readFile.hasNext()) {
            line = readFile.nextLine();
            arguments = line.split(COMMA_SEPARATOR);
            name = arguments[0];
            gender = arguments[1];
            age = Integer.parseInt(arguments[2]);
            height = Double.parseDouble(arguments[3]);
            weight = Double.parseDouble(arguments[4]);
            user.loadUserData(name, age, height, weight, gender);
        }
    }

    /**
     * Writes the user's data into the user config file.
     *
     * @throws IOException if an I/O error has occurred
     */
    public void writeUserConfigFile(User user) throws IOException {
        FileWriter file = new FileWriter(userConfigPath);

        file.write(User.getName()
                + COMMA_SEPARATOR + User.getGender()
                + COMMA_SEPARATOR + User.getAge()
                + COMMA_SEPARATOR + User.getHeight()
                + COMMA_SEPARATOR + User.getWeight());

        file.close();
    }

    /**
     * Reads the data from the food list file.
     *
     * @throws FileNotFoundException if the file is not found
     */
    public ArrayList<Food> loadFoodList() throws FileNotFoundException {
        ArrayList<Food> foodList = new ArrayList<>();

        String line;
        File foodListFile = new File(foodListPath);
        Scanner readFile = new Scanner(foodListFile);
        while (readFile.hasNext()) {
            line = readFile.nextLine();
            System.out.println(line);
        }

        return foodList;
    }

    /**
     * Reads the data from the exercise list file.
     *
     * @throws FileNotFoundException if the file is not found
     */
    public ArrayList<Exercise> loadExerciseList() throws FileNotFoundException {
        ArrayList<Exercise> exerciseList = new ArrayList<>();

        String line;
        File exerciseListFile = new File(exerciseListPath);
        Scanner readFile = new Scanner(exerciseListFile);
        while (readFile.hasNext()) {
            line = readFile.nextLine();
            System.out.println(line);
        }

        return exerciseList;
    }
}
