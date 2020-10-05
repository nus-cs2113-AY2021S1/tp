package seedu.duke.command;

import seedu.duke.tool.Access;
import seedu.duke.level.Admin;
import seedu.duke.level.Module;
import seedu.duke.tool.Ui;

public class addModuleCommand extends Command {
    public addModuleCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(Access access, Ui ui, Admin admin) {
        String filter = fullCommand.replace("addModule ", "");
        admin.addModule(new Module(filter));
    }
}
