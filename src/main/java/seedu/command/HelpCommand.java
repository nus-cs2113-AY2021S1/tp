package seedu.command;

import seedu.ui.Ui;
import seedu.user.User;
import seedu.user.UserList;

/**
 * Command to display help instructions.
 *
 */
public class HelpCommand extends Command {
    public HelpCommand() {
        super(null);
    }

    /**
     * Check if user is logged in and display help instructions.
     *
     * @param users object of UserList containing all available user's data
     * @param ui containing the outputs to print
     * @param nowUser object of currently logged in user
     */
    @Override
    public void execute(UserList users, Ui ui, User nowUser) {
        
        boolean isLoggedIn = true;

        if (nowUser == null) {
            isLoggedIn = false;
        }
        ui.printHelp(isLoggedIn, nowUser);
    }
}