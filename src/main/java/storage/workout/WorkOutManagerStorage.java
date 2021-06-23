package storage.workout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import exceptions.workout.workoutmanager.SchwIoException;
import logger.SchwarzeneggerLogger;
import models.PastWorkoutSessionRecord;
import seedu.duke.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

//@@author wgzesg
/**
 * A class that is responsible for storing Workout Manager data.
 */
public class WorkOutManagerStorage {

    /**
     * This list contains all the past record files' file name and some other relevant info.
     * It is saved in a file called history.json.
     * Each time it initialises, it will read history.json file and save it to this list.
     * Each time adding/removing record will update this list and write again to local storage.
     */
    private Gson gson;
    private final Logger logger = SchwarzeneggerLogger.getInstanceLogger();

    /**
     * This variable keeps track of file name.
     */
    private int recordCount = 0;

    public void init() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Reads all past records.
     *
     * @return A list of PastWorkoutSessionRecord.
     * @throws SchwIoException If error in reading the file.
     */
    public ArrayList<PastWorkoutSessionRecord> readPastRecords() throws SchwIoException {
        logger.info("Start loading files");

        ArrayList<PastWorkoutSessionRecord> pastFiles;
        File file = new File(Constants.PATH_TO_WORKOUT_SESSION_HISTORY);
        Type taskListType = new TypeToken<ArrayList<PastWorkoutSessionRecord>>() {
        }.getType();
        try {
            JsonReader reader = new JsonReader(new FileReader(file.getPath()));
            pastFiles = gson.fromJson(reader, taskListType);
        } catch (FileNotFoundException e) {
            logger.info("File is not found.");
            createMetaFile(Constants.PATH_TO_WORKOUT_SESSION_HISTORY);
            pastFiles = new ArrayList<>();
        } catch (JsonSyntaxException e) {
            logger.info("File is corrupted.");
            pastFiles = new ArrayList<>();
        }
        if (pastFiles == null) {
            pastFiles = new ArrayList<>();
        }
        assert (pastFiles != null);
        recordCount = pastFiles.size();
        logger.info("Loading completed");
        return pastFiles;
    }

    /**
     * Writes all past records to local storage.
     *
     * @param pastFiles The list of records to be stored.
     * @throws SchwIoException If error occurs in writing to file.
     */
    public void writePastRecords(List<PastWorkoutSessionRecord> pastFiles) throws SchwIoException {
        logger.info("Saving the changes...");
        File file = new File(Constants.PATH_TO_WORKOUT_SESSION_HISTORY);
        FileWriter writer;
        try {
            writer = new FileWriter(file.getPath());
            gson.toJson(pastFiles, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            logger.info("Error occured when saving the progress.");
            throw new SchwIoException("Error occured when saving the progress...");
        }
        logger.info("All changes saved.");
        recordCount = pastFiles.size();
    }

    private void createMetaFile(String path) throws SchwIoException {
        File file = new File(path);
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            logger.info("File cannot be created at " + path + ".");
            throw new SchwIoException("The local storage file cannot be created at " + path);
        }
    }

    /**
     * Creates a new file to store a new workout session data.
     *
     * @return File path of new created file
     * @throws SchwIoException If error occurs in creating the file.
     */
    public String createfile() throws SchwIoException {
        String newFilePath = Constants.PATH_TO_WORKOUT_SESSION_FOLDER + recordCount + ".json";
        File file = new File(newFilePath);
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
            return newFilePath;
        } catch (IOException e) {
            logger.info("File is not created at " + newFilePath + ".");
            throw new SchwIoException("The local storage file cannot be created at " + newFilePath);
        }
    }
}
