package seedu.command;

import seedu.exception.NotLoggedInException;
import seedu.exception.WhereGotTimeException;
import seedu.task.Event;
import seedu.ui.Ui;
import seedu.user.User;
import seedu.user.UserList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Clears timetable of user on a particular day or the entire timetable.
 */
public class ClearCommand extends Command {

    public ClearCommand(String information) {
        super(information);
    }

    private static Logger logger = Logger.getLogger("LogClearCommand");

    @Override
    public void execute(UserList users, Ui ui, User nowUser) throws WhereGotTimeException {

        setupInputLogger();
        logger.log(Level.INFO, "Beginning ClearCommand...");

        if (nowUser == null) {
            logger.log(Level.WARNING, "User not logged in, ending ClearCommand function");
            throw new NotLoggedInException("Sorry! You are not logged in to any account :-(");
        }

        int userIndex = -1;
        for (int i = 0; i < users.getTotalUserCount(); i++) {
            if ((users.getUser(i + 1).getName().equals(nowUser.getName()))) {
                userIndex = i + 1;
                logger.log(Level.INFO, "User has been found, user index: " + userIndex);
                break;
            }
        }

        String[] parsedInputs = input.split("/", 2);
        String day = parsedInputs[1].toLowerCase();
        int[] counter = {0};
        if (day.equals("all")) {
            logger.log(Level.INFO, "Deleting everything in the timetable...");
            deleteTimetable(users, ui, userIndex, counter, day, "mon");
            deleteTimetable(users, ui, userIndex, counter, day, "tue");
            deleteTimetable(users, ui, userIndex, counter, day, "wed");
            deleteTimetable(users, ui, userIndex, counter, day, "thu");
            deleteTimetable(users, ui, userIndex, counter, day, "fri");
            deleteTimetable(users, ui, userIndex, counter, day, "sat");
            deleteTimetable(users, ui, userIndex, counter, day, "sun");
        } else {
            logger.log(Level.INFO, "Deleting all classes on " + day);
            deleteTimetable(users, ui, userIndex, counter, day, day);
        }
        logger.log(Level.INFO, "ClearCommand function has ended successfully");
    }

    /**
     * Clears timetable of user on a particular day.
     *
     * @param users list of users.
     * @param ui user interface.
     * @param userIndex index of current user.
     * @param counter counts the number of empty days in the timetable.
     * @param input the input entered by the user.
     * @param day the day in the timetable that is currently being deleted.
     *
     * @throws WhereGotTimeException if timetable for clearing a single day is empty.
     */
    public void deleteTimetable(UserList users, Ui ui, int userIndex, int[] counter, String input, String day)
            throws WhereGotTimeException {
        ArrayList<Event> timetable = (users.getUser(userIndex).getTimetable()).getTimetable(day);
        ArrayList<Object> deletedTimetable = new ArrayList<>(timetable);
        if (!timetable.isEmpty()) {
            timetable.clear();
            logger.log(Level.INFO, "Successfully deleted class(es) on " + day);
        } else if (input.equals(day)) {
            logger.log(Level.INFO, "Timetable on " + day + " is empty");
            throw new WhereGotTimeException("There is no class in your timetable for " + day + "!");
        } else {
            counter[0]++;
        }
        ui.printClear(deletedTimetable, counter[0], input, day);
    }

    private void setupInputLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);
        try {
            FileHandler fh = new FileHandler("ClearCommand.log", true);
            fh.setLevel(Level.INFO);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "File logging is not functional");
        }
    }
}
