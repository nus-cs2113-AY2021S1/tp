package seedu.duke.command;

import seedu.duke.userprofile.UserProfile;

/**
 * Creates new user profile.
 */
public class CreateNewUserCommand extends Command {
    public CreateNewUserCommand() {
    }

    @Override
    public void execute() {
        UserProfile.createNewProfile();
    }
}
