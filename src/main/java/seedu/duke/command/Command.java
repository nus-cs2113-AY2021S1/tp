package seedu.duke.command;

import seedu.duke.tool.Access;
import seedu.duke.tool.Storage;
import seedu.duke.tool.Ui;

public abstract class Command {
    String fullCommand;

    public Command(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public abstract void execute(Access access, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    };
}
