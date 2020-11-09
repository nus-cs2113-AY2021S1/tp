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
 * Deletes a task from the task list.
 */
public class DeleteCommand extends Command {

    public DeleteCommand(String information) {
        super(information);
    }

    private static Logger logger = Logger.getLogger("LogDeleteCommand");

    @Override
    public void execute(UserList users, Ui ui, User nowUser) throws WhereGotTimeException {

        setupInputLogger();
        logger.log(Level.INFO, "Beginning DeleteCommand...");

        if (nowUser == null) {
            logger.log(Level.WARNING, "User not logged in, ending DeleteCommand function");
            throw new NotLoggedInException("Sorry! You are not logged in to any account :-(");
        }
        
        try {
            String[] parsedInputs = input.split("/", 3);
            String day = parsedInputs[1].trim().toLowerCase();
            int index;
            index = Integer.parseInt(parsedInputs[2]);
            logger.log(Level.INFO, "Deleting item " + index + ", on " + day);
            for (int i = 0; i < users.getTotalUserCount(); i++) {
                if ((users.getUser(i + 1).getName().equals(nowUser.getName()))) {
                    ArrayList<Event> timetable = (users.getUser(i + 1).getTimetable()).getTimetable(day);
                    String removedClass = timetable.get(index - 1).toString();
                    timetable.remove(index - 1);
                    ui.printDelete(removedClass, day, timetable.size());
                    logger.log(Level.INFO, "Lesson has been deleted successfully");
                    break;
                }
            }
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Index entered is not an integer, ending session...");
            throw new WhereGotTimeException("You've entered an invalid index!");
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "No such index in timetable, ending session...");
            throw new WhereGotTimeException("You've entered an invalid index!");
        }
        logger.log(Level.INFO, "DeleteCommand function has ended successfully");
    }

    private void setupInputLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);
        try {
            FileHandler fh = new FileHandler("DeleteCommand.log", true);
            fh.setLevel(Level.INFO);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "File logging is not functional");
        }
    }
}
