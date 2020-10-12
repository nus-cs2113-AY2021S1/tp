package seedu.duke.human;

import seedu.duke.exception.AniException;
import seedu.duke.ui.Ui;
import java.text.ParseException;

public class UserManagement {
    protected User currentUser;
    Ui ui;

    public UserManagement(Ui ui) {
        this.ui = ui;
        currentUser = null;
    }

    public User getUser() {
        return currentUser;
    }

    public void setUser(User inputUser) {
        currentUser = inputUser;
    }

    public void createUser() {
        boolean userCreated = false;

        while (!userCreated) {
            try {
                ui.printMessage("What's your name?");
                String name = ui.readInput();
                ui.printMessage("Hello " + name + "! What might your date of birth be? (DD/MM/YYYY)");
                String dob = ui.readInput();
                ui.printMessage("What might your gender be? (Male/Female/Others)");
                String gender = ui.readInput();

                currentUser = new User(name, dob, gender);
                userCreated = true;
            } catch (ParseException | AniException exception) {
                ui.printErrorMessage(exception.getMessage());
            }
        }
    }
}
