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
 * Finds class(es) in the timetable that matches the keyword.
 */
public class FindCommand extends Command {

    public FindCommand(String keyword) {
        super(keyword);
    }

    private static Logger logger = Logger.getLogger("LogFindCommand");

    @Override
    public void execute(UserList users, Ui ui, User nowUser) throws WhereGotTimeException {

        setupInputLogger();
        logger.log(Level.INFO, "Beginning FindCommand...");

        if (nowUser == null) {
            logger.log(Level.WARNING, "User not logged in, ending FindCommand function");
            throw new NotLoggedInException("Sorry! You are not logged in to any account :-(");
        }

        String[] parsedInputs = input.split("/", 2);
        String keyword = parsedInputs[1];
        int userIndex = -1;
        int count = 1;

        for (int i = 0; i < users.getTotalUserCount(); i++) {
            if ((users.getUser(i + 1).getName().equals(nowUser.getName()))) {
                userIndex = i + 1;
                logger.log(Level.INFO, "User has been found, user index: " + userIndex);
                break;
            }
        }

        assert userIndex != -1 : "User not found";

        logger.log(Level.INFO, "Begin searching through timetable for keyword");

        String classesFound = "";

        ArrayList<Event> timetableMon = (users.getUser(userIndex).getTimetable()).getTimetable("mon");
        for (Object c : timetableMon) {
            String classEvent = c.toString();
            if (classEvent.contains(keyword)) {
                classesFound = classesFound + "\n" + count + ". (Monday) " + classEvent;
                count++;
            }
        }

        ArrayList<Event> timetableTue = (users.getUser(userIndex).getTimetable()).getTimetable("tue");
        for (Object c : timetableTue) {
            String classEvent = c.toString();
            if (classEvent.contains(keyword)) {
                classesFound = classesFound + "\n" + count + ". (Tuesday) " + classEvent;
                count++;
            }
        }

        ArrayList<Event> timetableWed = (users.getUser(userIndex).getTimetable()).getTimetable("wed");
        for (Object c : timetableWed) {
            String classEvent = c.toString();
            if (classEvent.contains(keyword)) {
                classesFound = classesFound + "\n" + count + ". (Wednesday) " + classEvent;
                count++;
            }
        }
        ArrayList<Event> timetableThu = (users.getUser(userIndex).getTimetable()).getTimetable("thu");
        for (Object c : timetableThu) {
            String classEvent = c.toString();
            if (classEvent.contains(keyword)) {
                classesFound = classesFound + "\n" + count + ". (Thursday) " + classEvent;
                count++;
            }
        }

        ArrayList<Event> timetableFri = (users.getUser(userIndex).getTimetable()).getTimetable("fri");
        for (Object c : timetableFri) {
            String classEvent = c.toString();
            if (classEvent.contains(keyword)) {
                classesFound = classesFound + "\n" + count + ". (Friday) " + classEvent;
                count++;
            }
        }

        ArrayList<Event> timetableSat = (users.getUser(userIndex).getTimetable()).getTimetable("sat");
        for (Object c : timetableSat) {
            String classEvent = c.toString();
            if (classEvent.contains(keyword)) {
                classesFound = classesFound + "\n" + count + ". (Saturday) " + classEvent;
                count++;
            }
        }

        ArrayList<Event> timetableSun = (users.getUser(userIndex).getTimetable()).getTimetable("sun");
        for (Object c : timetableSun) {
            String classEvent = c.toString();
            if (classEvent.contains(keyword)) {
                classesFound = classesFound + "\n" + count + ". (Sunday) " + classEvent;
                count++;
            }
        }
        logger.log(Level.INFO, "Search has ended");
        ui.printFind(classesFound, keyword);
        logger.log(Level.INFO, "FindCommand function has ended successfully");
    }

    private void setupInputLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);
        try {
            FileHandler fh = new FileHandler("FindCommand.log", true);
            fh.setLevel(Level.INFO);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "File logging is not functional");
        }
    }
}
