package seedu.duke.command;

import seedu.duke.userprofile.UserProfile;

import java.io.IOException;

import static seedu.duke.ui.ExceptionMessages.displayFileError;

public class EditUserProfileCommand extends Command {
    private String description;

    public EditUserProfileCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute() {
        try {
            UserProfile.edit(description);
        } catch (IOException e) {
            displayFileError();
        }
    }
}
