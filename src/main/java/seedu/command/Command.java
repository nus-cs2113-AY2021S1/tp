package seedu.command;

import seedu.exception.WhereGotTimeException;
import seedu.ui.Ui;
import seedu.user.User;
import seedu.user.UserList;

public abstract class Command {
    protected String input;
    protected boolean isExit = false;
    protected User currentUser = null;
    protected boolean isLogIn = false;

    /**
     * Creates a new command.
     *
     * @param input the command given by the user in string.
     */
    public Command(String input) {
        this.input = input;
    }

    /**
     * Executes the Command based on the TaskList, Ui and Storage.
     *
     * @param users the UserList given to execute command on.
     * @param ui    the corresponding messages based on the task.
     * @throws WhereGotTimeException if execution encounters error.
     */
    public abstract void execute(UserList users, Ui ui, User nowUser) throws WhereGotTimeException;

    public boolean isExit() {
        return isExit;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isLogIn() {
        return isLogIn;
    }
}
