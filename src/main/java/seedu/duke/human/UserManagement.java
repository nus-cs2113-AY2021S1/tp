package seedu.duke.human;

import seedu.duke.exception.AniException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;
import java.text.ParseException;
import java.util.ArrayList;

public class UserManagement {
    protected User currentUser;
    Ui ui;
    Storage storage;

    public UserManagement(Ui ui, Storage storage) {
        this.ui = ui;
        this.storage = storage;
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User inputUser) {
        currentUser = inputUser;
    }

    public User addUser(String name, String dob, String gender) throws ParseException, AniException {
        return new User(name, dob, gender);
    }

    public void addUserDialogue() {
        boolean userCreated = false;

        while (!userCreated) {
            try {
                ui.printMessage("What's your name?");
                String name = ui.readInput();
                ui.printMessage("Hello " + name + "! What might your date of birth be? (DD/MM/YYYY)");
                String dob = ui.readInput();
                ui.printMessage("What might your gender be? (Male/Female/Others)");
                String gender = ui.readInput();

                currentUser = addUser(name, dob, gender);
                storage.writeUserProfileFile(ui, currentUser);

                userCreated = true;
            } catch (ParseException | AniException exception) {
                ui.printErrorMessage(exception.getMessage());
            }
        }
    }


}
