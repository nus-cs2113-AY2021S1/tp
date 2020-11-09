package seedu.lifeasier.storage;

import seedu.lifeasier.parser.Parser;
import seedu.lifeasier.ui.Ui;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The FileCommand class will house similar commands used by both NoteStorage and TaskStorage classes.
 */
public class FileCommand {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");

    public static final String BLANK_STRING = "";
    public static final String DEFAULT_DATETIME = "31-12-99 00:00";
    public static final String TIME_DELIMITER = "T";
    public static final String WHITE_SPACE = " ";
    public static final String SAVE_DELIMITER = "=-=";

    private static Logger logger = Logger.getLogger(FileCommand.class.getName());

    private Ui ui;

    public FileCommand() {
        this.ui = new Ui();
    }

    /**
     * Clears all data from the specified save file.
     *
     * @param filePath File path to which file to clear information.
     */
    public void clearSaveFile(String filePath) {
        logger.log(Level.INFO, "Clearing save file");

        try {
            FileWriter fileClear = new FileWriter(filePath);
            fileClear.write(BLANK_STRING);
            fileClear.close();
        } catch (IOException e) {
            ui.showFileWriteError();
            logger.log(Level.SEVERE, "Error encountered clearing save file");
        }

        logger.log(Level.INFO, "Save file cleared");
    }

    /**
     * Converts the saved raw string text in the save file into LocalDateTime objects.
     *
     * @param dateTimeInformation String which is to be parsed into LocalDateTime object.
     * @return Input string parsed into LocalDateTime object.
     */
    public LocalDateTime convertToLocalDateTime(String dateTimeInformation) {
        LocalDateTime taskDateTime;

        try {
            dateTimeInformation = dateTimeInformation.replace(TIME_DELIMITER, WHITE_SPACE);
            taskDateTime = LocalDateTime.parse(dateTimeInformation, DATE_TIME_FORMATTER);

        } catch (DateTimeParseException e) {
            ui.showLocalDateTimeParseError();
            logger.log(Level.WARNING, "Error encountered parsing LocalDateTime from save file");
            //Set as default time
            taskDateTime = LocalDateTime.parse(DEFAULT_DATETIME, DATE_TIME_FORMATTER);
        }
        assert taskDateTime != null : "taskDateTime must never be null";

        return taskDateTime;
    }

    /**
     * Counts the number of save delimiters found in the read save file line.
     *
     * @param string String with which to count the number of save delimiters.
     * @param baseDelimiterCount Base value to compare with.
     * @return true when the count in given string matches baseDelimiterCount.
     */
    public boolean checkForDelimiterCount(String string, int baseDelimiterCount) {
        boolean isStillContainingDelimiter;
        int delimiterCount = 0;

        do {
            isStillContainingDelimiter = string.contains(SAVE_DELIMITER);
            if (isStillContainingDelimiter) {
                delimiterCount++;
                string = string.replaceFirst(SAVE_DELIMITER, BLANK_STRING);
            }
        } while (isStillContainingDelimiter);

        return delimiterCount == baseDelimiterCount;
    }

    /**
     * Checks if times read from save file are in logical order, where start time is before end time.
     *
     * @param startTime Task start time.
     * @param endTime Task end time.
     * @throws LogicalTimeException When the start time comes before the end time.
     */
    public void checkForLogicalTime(int startTime, int endTime) throws LogicalTimeException {
        logger.log(Level.INFO, "Checking for logical time");
        if (startTime > endTime) {
            logger.log(Level.SEVERE, "Start time after end time detected");
            throw new LogicalTimeException();
        }
    }

    /**
     * Checks if the dates in the save file for lessons and events have the same date.
     *
     * @param startDate First date parameter in save file.
     * @param endDate Second date parameter in save file.
     */
    public void checkForTaskSameDate(String startDate, String endDate) throws UnequalSaveDateException {
        logger.log(Level.INFO, "Checking for same date");
        if (!startDate.equals(endDate)) {
            logger.log(Level.INFO, "Unequal dates found");
            throw new UnequalSaveDateException();
        }
    }

    /**
     * Checks if the module code for lessons in the save file are valid on read.
     *
     * @param moduleCode String to be checked for valid module code.
     */
    public void checkForValidModuleCode(String moduleCode) {
        Parser parser = new Parser();

        if (!parser.checkIfValidModuleCode(moduleCode)) {
            logger.log(Level.INFO, "Invalid module code found");
            ui.showSaveInvalidModuleCodePrompt();
        }
    }

    /**
     * Checks if midnight times have been input.
     *
     * @param time The string to be checked for whether it is a midnight time.
     * @return True when the time is midnight.
     */
    public boolean checkForInvalidMidnight(String time) {
        if (time.equals("00:00") || time.equals("24:00")) {
            return true;
        }
        return false;
    }

}
