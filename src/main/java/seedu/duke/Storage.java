package seedu.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the loading and saving of data to a file.
 */
public class Storage {
    private static final String DEFAULT_EXERCISE_LIST_FILEPATH = "exercises.txt";
    private static final String DEFAULT_FOOD_LIST_FILEPATH = "food.txt";

    private final String exerciseListPath;
    private final String foodListPath;

    /**
     * Creates 2 new files if they do not exist.
     *
     * @throws IOException if an I/O error has occurred
     */
    public Storage(String foodListPath, String exerciseListPath) throws IOException {
        this.foodListPath = foodListPath;
        this.exerciseListPath = exerciseListPath;

        File exerciseListFile = new File(exerciseListPath);
        File foodListFile = new File(foodListPath);

        if (!exerciseListFile.exists()) {
            exerciseListFile.createNewFile();
        } else if (!foodListFile.exists()) {
            foodListFile.createNewFile();
        }
    }

    public Storage() throws IOException {
        this(DEFAULT_FOOD_LIST_FILEPATH, DEFAULT_EXERCISE_LIST_FILEPATH);
    }

    /**
     * Reads the data from the food list file.
     *
     * @throws FileNotFoundException if the file is not found     */
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
