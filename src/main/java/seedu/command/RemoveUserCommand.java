package seedu.command;

import seedu.exception.WhereGotTimeException;
import seedu.task.Event;
import seedu.ui.Ui;
import seedu.user.User;
import seedu.user.UserList;

import java.util.ArrayList;

public class RemoveUserCommand extends Command {

    public RemoveUserCommand(String information) {
        super(information);
    }

    @Override
    public void execute(UserList users, Ui ui, User nowUser) throws WhereGotTimeException {
        String[] position = input.split("/",3);
        position[1] = position[1].trim();
        position[2] = position[2].trim();
        users.removeUser(position[1], position[2]);
    }
}