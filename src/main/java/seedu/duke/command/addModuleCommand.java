package seedu.duke.command;

import seedu.duke.level.Admin;
import seedu.duke.level.Chapter;
import seedu.duke.tool.Access;
import seedu.duke.level.Module;
import seedu.duke.tool.Storage;
import seedu.duke.tool.Ui;

public class addModuleCommand extends Command {
    public addModuleCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(Access access, Ui ui, Storage storage) {
        String filter = fullCommand.replace("addModule ", "");
        if(access.getModuleLevel() == "") {
            Admin newAdmin = access.getAdmin();
            newAdmin.add(new Module(filter));
            access.setAdmin(newAdmin);
            storage.createModule(filter);
        }
    }
}
