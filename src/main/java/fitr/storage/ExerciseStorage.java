package fitr.storage;

import fitr.calorie.Calorie;
import fitr.common.DateManager;
import fitr.exercise.Exercise;
import fitr.list.ExerciseList;
import fitr.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import static fitr.storage.StorageManager.COMMA_SEPARATOR;

/**
 * Handles the read and write operations of the user's exercises.
 */
public class ExerciseStorage {
    private static final Logger LOGGER = Logger.getLogger(ExerciseStorage.class.getName());
    private static final String DEFAULT_EXERCISE_LIST_PATH = "exerciseList.txt";
    private final String exerciseListPath;

    public ExerciseStorage() throws IOException {
        this(DEFAULT_EXERCISE_LIST_PATH);
    }

    public ExerciseStorage(String exerciseListPath) throws IOException {
        this.exerciseListPath = exerciseListPath;
        File exerciseListFile = new File(exerciseListPath);
        if (!exerciseListFile.exists()) {
            boolean isFileExists = exerciseListFile.createNewFile();
            LOGGER.fine("Exercise list file created: " + exerciseListPath);
        }
    }

    /**
     * Loads the user's exercises from a file and returns an ArrayList of Exercise objects.
     *
     * @return an ArrayList of Exercise objects
     * @throws FileNotFoundException if the file is not found
     */
    public ArrayList<Exercise> loadExerciseList() throws FileNotFoundException {
        LOGGER.fine("Attempting to read file: " + exerciseListPath);
        ArrayList<Exercise> exerciseList = new ArrayList<>();
        String line;
        String[] arguments;
        File exerciseListFile = new File(exerciseListPath);
        Scanner readFile = new Scanner(exerciseListFile);
        boolean containsInvalidEntry = false;

        while (readFile.hasNext()) {
            line = readFile.nextLine();
            arguments = line.split(COMMA_SEPARATOR);

            if (isValidEntry(arguments)) {
                exerciseList.add(new Exercise(arguments[0], new Calorie(Integer.parseInt(arguments[1])),
                        LocalDate.parse(arguments[2], DateManager.formatter)));
            } else {
                LOGGER.fine("Invalid entry found in exercise list file.");
                containsInvalidEntry = true;
            }
        }

        if (containsInvalidEntry) {
            Ui.printCustomError("Invalid exercise entries found and are not added to your exercise list!");
        }

        LOGGER.fine("Exercise list file read successfully.");
        return exerciseList;
    }

    /**
     * Writes the exercise list data into a file.
     *
     * @param exerciseList the exercise list to write to the file
     * @throws IOException if an I/O error has occurred
     */
    public void writeExerciseList(ExerciseList exerciseList) throws IOException {
        LOGGER.fine("Attempting to write to file: " + exerciseListPath);
        FileWriter file = new FileWriter(exerciseListPath);
        Exercise exercise;

        for (int i = 0; i < exerciseList.getSize(); i++) {
            exercise = exerciseList.getExercise(i);
            file.write(exercise.getNameOfExercise()
                    + COMMA_SEPARATOR + exercise.getCalories()
                    + COMMA_SEPARATOR + exercise.getDate() + System.lineSeparator());
        }

        LOGGER.fine("Exercise list file written successfully.");
        file.close();
    }

    private boolean isValidEntry(String[] arguments) {
        if (arguments.length != 3) {
            return false;
        }

        String name = arguments[0];
        if (name.isBlank()) {
            return false;
        }

        try {
            int calories = Integer.parseInt(arguments[1]);
            if (calories < 1 || calories > 10000) {
                return false;
            }

            LocalDate date = LocalDate.parse(arguments[2], DateManager.formatter);
        } catch (NumberFormatException | DateTimeParseException e) {
            return false;
        }

        return true;
    }
}
