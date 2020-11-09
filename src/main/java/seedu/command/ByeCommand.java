package seedu.command;

import seedu.ui.Ui;
import seedu.user.User;
import seedu.user.UserList;

/**
 * Terminates Duke program.
 */
public class ByeCommand extends Command {

    public ByeCommand() {
        super(null);
    }

    //@Override
    public void execute(UserList users, Ui ui, User nowUser/*, Storage storage*/) {
        isExit = true;
        ui.showBye();
    }
}
