package seedu.command;

import seedu.exception.IdenticalTimingsException;
import seedu.exception.IllogicalTimingException;
import seedu.exception.IncorrectCommandFormatException;
import seedu.exception.InvalidIndexException;
import seedu.exception.InvalidTimingFormatException;
import seedu.exception.NotLoggedInException;
import seedu.exception.WhereGotTimeException;
import seedu.timetable.SortTimetable;
import seedu.ui.Ui;
import seedu.user.User;
import seedu.user.UserList;
import seedu.task.Event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Command to edit a timetable.
 */
public class EditCommand extends Command {
    
    private static Logger logger = Logger.getLogger("LogEditCommand");

    /**
     * Constructor for editing an event.
     */
    public EditCommand(String input) {
        super(input);
    }

    /**
     * Parses timings to be edited and check for validity.
     *
     * @param users   object of UserList containing all available user's data
     * @param ui      containing the outputs to print
     * @param nowUser object of currently logged in user
     * @throws WhereGotTimeException           all possible exceptions extended from WhereGotTimeException can be thrown
     * @throws NotLoggedInException            if user invoke edit command without first logging in
     * @throws IncorrectCommandFormatException if user inputs a format that is not valid, e.g. extra or missing fields
     * @throws IdenticalTimingsException       if the new timings are identical with original timings
     * @throws InvalidIndexException           if the user inputs an index that is out of bound
     */
    @Override
    public void execute(UserList users, Ui ui, User nowUser) throws WhereGotTimeException {

        setupInputLogger();

        if (nowUser == null) {
            logger.log(Level.WARNING, "Not logged in" + "\n");
            throw new NotLoggedInException("Sorry! You are not logged in to any account!");
        }

        String[] parsedInputs = input.split("/", 2);
        
        if (parsedInputs.length != 2) {
            throw new IncorrectCommandFormatException("Incorrect format for edit command! Enter 'help' for the correct "
                    + "input format!");
        }

        String date = parsedInputs[1].trim();
        logger.log(Level.INFO, "Correct date added: " + parsedInputs[1]);
        ArrayList<Event> dateTimetable = nowUser.getTimetable().getTimetable(date);

        if (dateTimetable.size() == 0) {

            ui.printEditEmptyClass(nowUser, date);
            logger.log(Level.WARNING, "No class in this day." + "\n");

        } else {
            try {
                ui.printEditLessonList(nowUser, date, dateTimetable);

                Scanner scanner = new Scanner(System.in);
                String editInput = scanner.nextLine().trim();
                String[] parsedEditInput = editInput.split("/", 3);

                if (parsedEditInput.length != 3) {
                    throw new IncorrectCommandFormatException("You have entered an invalid edit format!");
                } else if (!parsedEditInput[0].isEmpty()) {
                    throw new IncorrectCommandFormatException("You have entered an invalid edit format!");
                }
                
                int index = Integer.parseInt(parsedEditInput[1].trim());
                logger.log(Level.INFO, "Correct index added: " + index);

                String[] newTime = parsedEditInput[2].split("-");
                
                if (newTime.length != 2) {
                    logger.log(Level.WARNING, "Invalid time format!" + "\n");
                    throw new IncorrectCommandFormatException("Invalid time format. Enter 'help' for the "
                            + "correct input format!");
                }
                checkTimeValidity(newTime);

                logger.log(Level.INFO, "Correct start time added: " + newTime[0]);
                logger.log(Level.INFO, "Correct end time added: " + newTime[1]);

                Event originalEvent;
                for (int i = 0; i < users.getTotalUserCount(); i++) {
                    if ((users.getUser(i + 1).getName().equals(nowUser.getName()))) {
                        originalEvent = nowUser.getTimetable().getTimetable(date).get(index - 1);
                        String originalStartTime = originalEvent.getTimeStart();
                        String originalEndTime = originalEvent.getTimeEnd();
                        if (newTime[0].equals(originalStartTime) && newTime[1].equals(originalEndTime)) {
                            logger.log(Level.WARNING, "Identical class detected." + "\n");
                            throw new IdenticalTimingsException("You have entered a timing that is exactly "
                                    + "the \nsame as the original one! Hence, no changes were made!");
                        }
                        Event modifiedEvent = new Event(originalEvent.getDescription(),
                                originalEvent.getLocation(), newTime[0], newTime[1]);
                        nowUser.getTimetable().getTimetable(date).set(index - 1, modifiedEvent);
                        ui.printEdit(originalEvent, modifiedEvent);
                        logger.log(Level.INFO, "Class edited successfully:\n" + originalEvent + "\n" + modifiedEvent);
                    }
                }
                SortTimetable.sortTimetable(users, nowUser, date);

            } catch (NumberFormatException e) {
                logger.log(Level.WARNING, "Invalid edit format!" + "\n");
                throw new IncorrectCommandFormatException("You've entered an invalid edit format!");
            } catch (IndexOutOfBoundsException e) {
                logger.log(Level.WARNING, "Invalid index!" + "\n");
                throw new InvalidIndexException("You've entered an invalid index!");
            }
        }
    }

    /**
     * Checks for time validity.
     *
     * @param time the array of new times to be edited
     * @throws WhereGotTimeException        all possible exceptions extending from WhereGotTimeException can be thrown
     * @throws InvalidTimingFormatException if user inputs a time that is not 4-digit, not conforming to 24-hour
     *                                      format and not in 1-hour block
     * @throws IllogicalTimingException     if user inputs identical start and end time, or if start time is later
     *                                      than end time
     */
    private void checkTimeValidity(String[] time) throws WhereGotTimeException {
        String startTimeHour = time[0].substring(0, 2);
        String startTimeMinute = time[0].substring(2);
        String endTimeHour = time[1].substring(0, 2);
        String endTimeMinute = time[1].substring(2);
        int startTimeHourInt = Integer.parseInt(startTimeHour);
        int startTimeMinuteInt = Integer.parseInt(startTimeMinute);
        int endTimeHourInt = Integer.parseInt((endTimeHour));
        int endTimeMinuteInt = Integer.parseInt(endTimeMinute);
        int startTime = Integer.parseInt(time[0]);
        int endTime = Integer.parseInt(time[1]);

        if (time[0].length() != 4 || time[1].length() != 4) {
            throw new InvalidTimingFormatException("Invalid hour format! It should be in "
                    + "24-hour format and in 1-hour block! e.g. 0900, 1300, 2300, etc.");
        } else if (startTimeHourInt < 0 || startTimeHourInt > 23) {
            throw new InvalidTimingFormatException("Invalid hour for start time! It should be in "
                    + "24-hour format and in 1-hour block! e.g. 0900, 1300, 2300, etc.");
        } else if (startTimeMinuteInt < 0 || startTimeMinuteInt > 59) {
            throw new InvalidTimingFormatException("Invalid minute for start time! It should be in "
                    + "24-hour format and in 1-hour block! e.g. 0900, 1300, 2300, etc.");
        } else if (endTimeHourInt < 0 || endTimeHourInt > 23) {
            throw new InvalidTimingFormatException("Invalid hour for end time! It should be in "
                    + "24-hour format and in 1-hour block! e.g. 0900, 1300, 2300, etc.");
        } else if (endTimeMinuteInt < 0 || endTimeMinuteInt > 59) {
            throw new InvalidTimingFormatException("Invalid minute for end time! It should be in "
                    + "24-hour format and in 1-hour block! e.g. 0900, 1300, 2300, etc.");
        } else if ((startTimeMinuteInt % 60) != 0 || (endTimeMinuteInt % 60) != 0) {
            throw new InvalidTimingFormatException("The timings should be in 1-hour block! e.g. 0900, 1300, 2300, etc");
        } else if (startTime == endTime) {
            throw new IllogicalTimingException("Start time cannot be the same as end time!");
        } else if (startTime > endTime) {
            throw new IllogicalTimingException("Start time cannot be later than end time!");
        }
    }

    private void setupInputLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);
        try {
            FileHandler fh = new FileHandler("EditCommand.log", true);
            fh.setLevel(Level.INFO);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "File logging fails!");
        }
    }

}