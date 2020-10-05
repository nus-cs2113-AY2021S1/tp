package seedu.duke.command;

import seedu.duke.command.Command;
import seedu.duke.level.Admin;
import seedu.duke.tool.Access;
import seedu.duke.tool.Ui;

public class backModuleCommand extends Command {
    public backModuleCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(Access access, Ui ui, Admin admin) {
        access.setModuleLevel("");
    }
}
