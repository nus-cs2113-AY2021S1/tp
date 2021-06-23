package storage.workout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import models.Exercise;
import models.ExerciseList;
import ui.CommonUi;
import ui.workout.workoutsession.WorkoutSessionUi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

//@@author yujinyang1998
/**
 * A class that saves and loads Workout Session data on local hard disk.
 */
public class WorkoutSessionStorage {
    private CommonUi ui = new CommonUi();
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Writes to storage file with exercise data.
     *
     * @param filePath Path to data file.
     * @param exerciseList List of exercise.
     * @throws IOException If file is missing or corrupted.
     */
    public void writeToStorage(String filePath, ExerciseList exerciseList) throws IOException {
        assert (filePath != null && exerciseList != null) : "File corrupted";
        File file = new File(filePath);
        FileWriter writer = new FileWriter(file.getPath());
        gson.toJson(exerciseList.exerciseList, writer);
        writer.flush();
        writer.close();
    }

    /**
     * Reads file from drive and loads exercise information.
     *
     * @param filePath Path to data file.
     * @param exerciseList List of exercise.
     * @throws FileNotFoundException If file is missing or corrupted.
     */
    public void readFileContents(String filePath, ExerciseList exerciseList) throws FileNotFoundException {
        assert (filePath != null && exerciseList != null) : "File corrupted";
        File file = new File(filePath);

        Type taskListType = new TypeToken<ArrayList<Exercise>>() {
        }.getType();
        JsonReader reader = new JsonReader(new FileReader(file.getPath()));
        exerciseList.exerciseList.clear();
        try {
            exerciseList.exerciseList.addAll(gson.fromJson(reader, taskListType));
        } catch (NullPointerException e) {
            System.out.print("");
        } catch (JsonSyntaxException e) {
            ui.showToUser(WorkoutSessionUi.saveCorruptedError(filePath));
        }
    }


}
