package seedu.duke.command;

import seedu.duke.tool.Access;
import seedu.duke.level.Admin;
import seedu.duke.tool.Ui;

public abstract class Command {
    String fullCommand;

    public Command(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public abstract void execute(Access access, Ui ui, Admin admin);

    public boolean isExit() {
        return false;
    };
}
