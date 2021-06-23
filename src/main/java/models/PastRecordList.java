package models;

import exceptions.InvalidDateFormatException;
import exceptions.SchwarzeneggerException;
import exceptions.workout.workoutmanager.SchwIoException;
import logger.SchwarzeneggerLogger;
import logic.parser.WorkoutManagerParser;
import storage.workout.WorkOutManagerStorage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static ui.CommonUi.LS;
import static ui.CommonUi.searchRecords;

//@@author wgzesg
/**
 * A singleton class representing list of past records.
 */
public class PastRecordList {
    public static final int OFFSET = 1;
    private static PastRecordList singlePastFile = null;
    private final Logger logger = SchwarzeneggerLogger.getInstanceLogger();

    private static List<PastWorkoutSessionRecord> pastFiles;
    WorkOutManagerStorage storage;

    /**
     * Constructs PastRecordList object.
     */
    private PastRecordList() {
        storage = new WorkOutManagerStorage();
        storage.init();
        try {
            pastFiles = storage.readPastRecords();
            logger.log(Level.INFO, "Workout records loaded.");
        } catch (SchwIoException e) {
            pastFiles = new ArrayList<>();
            logger.log(Level.INFO, "Workout records cannot be loaded.");
            System.out.println("got bug");
        }
    }

    /**
     * Gets instance of PastRecordList.
     *
     * @return Instance of PastRecordList.
     */
    public static PastRecordList getInstance() {
        if (singlePastFile == null) {
            singlePastFile = new PastRecordList();
        }
        return singlePastFile;
    }

    /**
     * Clears all the local storage file and past records.
     *
     * @throws SchwIoException If error occurred writing to file.
     */
    public void clear() throws SchwIoException {
        while (pastFiles.size() != 0) {
            delete(1);
        }
        logger.log(Level.WARNING, "Record is cleared.");
    }

    /**
     * Deletes a file and its past record at a given index.
     *
     * @param index Index of the file to be cleared.
     * @throws SchwIoException If error occurred while writing to file.
     */
    public void delete(int index) throws SchwIoException {
        PastWorkoutSessionRecord deletedRecord;
        deletedRecord = pastFiles.get(index - 1);
        pastFiles.remove(index - 1);
        File myFile = new File(deletedRecord.getFilePath());
        myFile.delete();
        storage.writePastRecords(pastFiles);
        logger.log(Level.INFO, "Record is deleted.");
    }

    /**
     * Searches based on conditions and returns a string representation of all the records
     * that satisfies the condition.
     *
     * @param args String of user input.
     * @return String representation of all the records that satisfies the condition.
     * @throws InvalidDateFormatException handles invalid date input.
     */
    public String search(String args) throws InvalidDateFormatException {
        ArrayList<Predicate<PastWorkoutSessionRecord>> conditions =
                WorkoutManagerParser.getInstance().parseSearchConditions(args);

        List<PastWorkoutSessionRecord> result = pastFiles.stream()
                .filter(conditions.stream().reduce(x -> true, Predicate::and))
                .collect(Collectors.toList());

        String info;

        if (result.size() == 0) {
            info = "You do not have any record that satisfies the condition.";
            logger.log(Level.INFO, "Search completed.");
            return info;
        }

        info = searchRecords(result.size(), ((result.size() > 1) ? "records have" : "record has"));

        info = getListInTable(result, info);
        logger.log(Level.INFO, "Search completed.");
        return info;
    }

    private String getListInTable(List<PastWorkoutSessionRecord> result, String info) {
        info += String.format("%-8s", "Index") + String.format("%-16s", "Creation date")
                + String.format("%-8s", "Tags") + LS;
        StringBuilder infoBuilder = new StringBuilder(info);
        int index;
        for (PastWorkoutSessionRecord wsr : result) {
            index = pastFiles.indexOf(wsr);
            String row = String.format("%-8s", index + OFFSET) + wsr.toString() + LS;
            infoBuilder.append(row);
        }
        info = infoBuilder.toString().trim();
        return info;
    }

    /**
     * Edits a file and its past record at a given index.
     *
     * @param index Index of the file to be edited.
     * @return File path string.
     * @throws SchwIoException If error occurred while reading or writing to file.
     */
    public String edit(int index) throws SchwIoException {
        PastWorkoutSessionRecord editedRecord;
        editedRecord = pastFiles.get(index - 1);
        PastWorkoutSessionRecord newRecord = editedRecord.edit();
        pastFiles.set(index - 1, newRecord);
        storage.writePastRecords(pastFiles);
        logger.log(Level.INFO, "Edit completed.");
        return newRecord.getFilePath();
    }

    /**
     * Adds a new file and records.
     *
     * @param tags Tags on the new record.
     * @return new file path string.
     * @throws SchwarzeneggerException If error occurred while reading or writing to file.
     */
    public String add(ArrayList<String> tags) throws SchwarzeneggerException {
        String newFilePath = storage.createfile();
        PastWorkoutSessionRecord newRecord = new PastWorkoutSessionRecord(newFilePath, tags);
        pastFiles.add(newRecord);
        storage.writePastRecords(pastFiles);
        logger.log(Level.INFO, "Add completed.");
        return newFilePath;
    }

    /**
     * Lists all records.
     *
     * @param args User's input.
     * @return message for dates.
     * @throws InvalidDateFormatException handles invalid dates format.
     */
    public String list(String args) throws InvalidDateFormatException {

        ArrayList<Predicate<PastWorkoutSessionRecord>> conditions = WorkoutManagerParser.getInstance().parseList(args);

        List<PastWorkoutSessionRecord> result = pastFiles.stream()
                .filter(conditions.stream().reduce(x -> true, Predicate::and))
                .collect(Collectors.toList());

        String info = "";
        if (pastFiles.size() == 0) {
            info = "You do not have any record yet. Key in 'new' to start one.";
            logger.log(Level.INFO, "List completed.");
            return info;
        } else if (result.size() == 0) {
            info = "You do not have any records between that period.";
            logger.log(Level.INFO, "List completed.");
            return info;
        }
        info = getListInTable(result, info);
        logger.log(Level.INFO, "List completed.");
        return info;
    }
}
