package seedu.command;

import seedu.exception.WhereGotTimeException;
//import seedu.duke.storage.Storage;
//import seedu.duke.task.Event;
//import seedu.duke.task.TaskList;
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
 * Adds an event to the task list.
 */
public class LogInCommand extends Command {

    public LogInCommand(String input) {
        super(input);
    }

    private static Logger logger = Logger.getLogger("LogAddCommand");

    @Override
    public void execute(UserList users, Ui ui, User nowUser) throws WhereGotTimeException {
        boolean doesExist = false;
        setupInputLogger();

        String[] position = input.split("/", 3);
        position[1] = position[1].trim();
        
        for (int i = 1; i <= users.getTotalUserCount(); i++) {
            if (position[1].equals((users.getUser(i)).getName())) {
                if (position[2].equals((users.getUser(i)).getPassword())) {
                    currentUser = users.getUser(i);

                    ui.greetReturningUser(currentUser);
                    
                    doesExist = true;
                } else {
                    logger.log(Level.WARNING,"Wrong Password" + "\n");
                    throw new WhereGotTimeException("Wrong Password");
                }
            }
        } 
        
        if (doesExist == false) {
            User newUser = new User(position[1], position[2]);
            currentUser = newUser;
            users.addUser(newUser);
            
            ui.greetUser(currentUser);
            
            logger.log(Level.INFO,"User Name successfully added:  " + position[1]);
        }
        
        isLogIn = true;
        assert isLogIn() == true : "Username or Password is missing";
        
        logger.log(Level.INFO, "end of processing"  + "\n");
    }

    private void setupInputLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);
        try {
            FileHandler fh = new FileHandler("LogInCommand.log",true);
            fh.setLevel(Level.INFO);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);

        } catch (IOException e) {
            logger.log(Level.SEVERE,"File logging not working");
        }
    }
}
