package seedu.command;

import seedu.exception.NotLoggedInException;
import seedu.exception.WhereGotTimeException;
import seedu.ui.Ui;
import seedu.user.User;
import seedu.user.UserList;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


/**
 * Prints a list of all tasks to the user.
 */
public class ListCommand extends Command {

    public ListCommand(String input) {
        super(input);
    }

    private static Logger logger = Logger.getLogger("LogListCommand");

    @Override
    public void execute(UserList users, Ui ui, User nowUser) throws WhereGotTimeException {

        setupInputLogger();
        logger.log(Level.INFO, "Beginning ListCommand...");

        int userIndex = -1;

        if (nowUser == null) {
            logger.log(Level.WARNING, "User not logged in, ending ListCommand function");
            throw new NotLoggedInException("Sorry! You are not logged in to any account!");
        }

        for (int i = 0; i < users.getTotalUserCount(); i++) {
            if ((users.getUser(i + 1).getName().equals(nowUser.getName()))) {
                userIndex = i + 1;
                logger.log(Level.INFO, "User has been found, user index: " + userIndex);
                break;
            }
        }

        assert userIndex != -1 : "User not found";

        String day = input.substring(1).toLowerCase();
        if (day.equals("all")) {
            logger.log(Level.INFO, "List all classes");
            ui.printList(users, userIndex, "mon");
            System.out.println();
            ui.printList(users, userIndex, "tue");
            System.out.println();
            ui.printList(users, userIndex, "wed");
            System.out.println();
            ui.printList(users, userIndex, "thu");
            System.out.println();
            ui.printList(users, userIndex, "fri");
            System.out.println();
            ui.printList(users, userIndex, "sat");
            System.out.println();
            ui.printList(users, userIndex, "sun");
        } else {
            logger.log(Level.INFO, "List classes only on " + day);
            ui.printList(users, userIndex, day);
        }
        logger.log(Level.INFO, "ListCommand function has ended successfully");
    }

    private void setupInputLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);
        try {
            FileHandler fh = new FileHandler("ListCommand.log", true);
            fh.setLevel(Level.INFO);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "File logging is not functional");
        }
    }
}
