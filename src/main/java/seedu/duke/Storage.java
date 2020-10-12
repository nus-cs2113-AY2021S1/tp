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
     * Set up the files required in the application, by creating the files if the files do not exist and
     * setting the file paths.
     *
     * @param userConfigPath file path of the user's profile
     * @param foodListPath file path of the food list
     * @param exerciseListPath file path of the exercise list
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
     * Reads the user's data from the user config file.
     *
     * @param user the user to load the file into
     * @return True if the file is read successfully, False if not
     * @throws FileNotFoundException if the file is not found
     */
    public boolean readUserConfigFile(User user) throws FileNotFoundException {
        boolean isReadSuccess = false;
        File file = new File(userConfigPath);
        Scanner readFile = new Scanner(file);
        String line;
        String name;
        String gender;
        int age;
        double height;
        double weight;
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
            isReadSuccess = true;
        }

        return isReadSuccess;
    }

    /**
     * Writes the user's data into the user config file.
     *
     * @param user the user to load the file into
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
     * Loads the list of the user's consumed food from a file and returns an ArrayList of Food objects.
     *
     * @return an ArrayList of Food objects
     * @throws FileNotFoundException if the file is not found
     */
    public ArrayList<Food> loadFoodList() throws FileNotFoundException {
        ArrayList<Food> foodList = new ArrayList<>();
        String line;
        String[] arguments;
        File foodListFile = new File(foodListPath);
        Scanner readFile = new Scanner(foodListFile);

        while (readFile.hasNext()) {
            line = readFile.nextLine();
            arguments = line.split(COMMA_SEPARATOR);
            foodList.add(new Food(arguments[0],
                    new Calorie(Integer.parseInt(arguments[1])), Integer.parseInt(arguments[2])));
        }

        return foodList;
    }

    /**
     * Writes the food list data into a file.
     *
     * @param foodList the food list to write to the file
     * @throws IOException if an I/O error has occurred
     */
    public void writeFoodList(FoodList foodList) throws IOException {
        FileWriter file = new FileWriter(foodListPath);
        Food food;

        for (int i = 0; i < foodList.getSize(); i++) {
            food = foodList.getFood(i);
            file.write(food.getFoodName()
                    + COMMA_SEPARATOR + food.getCalories()
                    + COMMA_SEPARATOR + food.getAmountOfFood() + System.lineSeparator());
        }

        file.close();
    }

    /**
     * Loads the user's exercises from a file and returns an ArrayList of Exercise objects.
     *
     * @return an ArrayList of Exercise objects
     * @throws FileNotFoundException if the file is not found
     */
    public ArrayList<Exercise> loadExerciseList() throws FileNotFoundException {
        ArrayList<Exercise> exerciseList = new ArrayList<>();
        String line;
        String[] arguments;
        File exerciseListFile = new File(exerciseListPath);
        Scanner readFile = new Scanner(exerciseListFile);

        while (readFile.hasNext()) {
            line = readFile.nextLine();
            arguments = line.split(COMMA_SEPARATOR);
            exerciseList.add(new Exercise(arguments[0],
                    new Calorie(Integer.parseInt(arguments[1])), Integer.parseInt(arguments[2])));
        }

        return exerciseList;
    }

    /**
     * Writes the exercise list data into a file.
     *
     * @param exerciseList the exercise list to write to the file
     * @throws IOException if an I/O error has occurred
     */
    public void writeExerciseList(ExerciseList exerciseList) throws IOException {
        FileWriter file = new FileWriter(exerciseListPath);
        Exercise exercise;

        for (int i = 0; i < exerciseList.getSize(); i++) {
            exercise = exerciseList.getExercise(i);
            file.write(exercise.getNameOfExercise()
                    + COMMA_SEPARATOR + exercise.getCalories()
                    + COMMA_SEPARATOR + exercise.getDuration() + System.lineSeparator());
        }

        file.close();
    }
}
