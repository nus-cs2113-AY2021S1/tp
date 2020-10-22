package fitr.storage;

import fitr.Calorie;
import fitr.Exercise;
import fitr.list.ExerciseList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import static fitr.storage.StorageManager.COMMA_SEPARATOR;

public class ExerciseStorage {
    private static final Logger LOGGER = Logger.getLogger(ExerciseStorage.class.getName());
    private static final String EXERCISE_LIST_PATH = "exercise.txt";

    public ExerciseStorage() throws IOException {
        File exerciseListFile = new File(EXERCISE_LIST_PATH);
        if (!exerciseListFile.exists()) {
            boolean isFileExists = exerciseListFile.createNewFile();
            LOGGER.fine("Exercise list file created: " + EXERCISE_LIST_PATH);
        }
    }

    /**
     * Loads the user's exercises from a file and returns an ArrayList of Exercise objects.
     *
     * @return an ArrayList of Exercise objects
     * @throws FileNotFoundException if the file is not found
     */
    public ArrayList<Exercise> loadExerciseList() throws FileNotFoundException {
        LOGGER.fine("Attempting to read file: " + EXERCISE_LIST_PATH);
        ArrayList<Exercise> exerciseList = new ArrayList<>();
        String line;
        String[] arguments;
        File exerciseListFile = new File(EXERCISE_LIST_PATH);
        Scanner readFile = new Scanner(exerciseListFile);

        while (readFile.hasNext()) {
            line = readFile.nextLine();
            arguments = line.split(COMMA_SEPARATOR);
            exerciseList.add(new Exercise(arguments[0],
                    new Calorie(Integer.parseInt(arguments[1]))));
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
        LOGGER.fine("Attempting to write to file: " + EXERCISE_LIST_PATH);
        FileWriter file = new FileWriter(EXERCISE_LIST_PATH);
        Exercise exercise;

        for (int i = 0; i < exerciseList.getSize(); i++) {
            exercise = exerciseList.getExercise(i);
            file.write(exercise.getNameOfExercise()
                    + COMMA_SEPARATOR + exercise.getCalories() + System.lineSeparator());
        }

        LOGGER.fine("Exercise list file written successfully.");
        file.close();
    }
}
