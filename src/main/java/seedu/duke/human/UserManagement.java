package seedu.duke.human;

import seedu.duke.exception.AniException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserManagement {
    Storage storage;
    private static final Logger logger = Logger.getLogger("UserLogger");
    protected ArrayList<User> userList = new ArrayList<>();
    protected User activeUser;

    public UserManagement(Storage storage) {
        this.storage = storage;
        activeUser = null;
    }

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User inputUser) {
        logger.log(Level.INFO, "User switched: " + inputUser.getName());
        activeUser = inputUser;
    }

    public int getTotalUsers() {
        return userList.size();
    }

    public Storage getStorage() {
        return storage;
    }

    public User addUser(String name, String dob, String gender) throws ParseException, AniException {
        User newUser = new User(name, dob, gender);
        userList.add(newUser);
        storage.saveUser(newUser);

        logger.log(Level.INFO, "User created: " + name + " | " + dob + " | " + gender);
        return newUser;
    }

    public void addUserDialogue(Ui ui) {
        boolean userCreated = false;

        while (!userCreated) {
            try {
                ui.printMessage("What's your name?");
                String name = ui.readInput();
                ui.printMessage("Hello " + name + "! What might your date of birth be? (DD/MM/YYYY)");
                String dob = ui.readInput();
                ui.printMessage("What might your gender be? (Male/Female/Other)");
                String gender = ui.readInput();

                activeUser = addUser(name, dob, gender);
                ui.printMessage(" Successfully added new user:");
                ui.printMessage(activeUser.getName());
                ui.printMessage(activeUser.getDobString());
                ui.printMessage(activeUser.getGender().toString());
                userCreated = true;
            } catch (ParseException | AniException exception) {
                ui.printErrorMessage(exception.getMessage());
            }
        }
    }
}
