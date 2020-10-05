package seedu.duke.command;

import seedu.duke.command.Command;
import seedu.duke.level.Admin;
import seedu.duke.tool.Access;
import seedu.duke.tool.Ui;

public class goModuleCommand extends Command {
    public goModuleCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(Access access, Ui ui, Admin admin) {
        String filter = fullCommand.replace("goModule ", "");
        access.setModuleLevel(filter);
    }
}
