package seedu.duke.command;

import seedu.duke.level.Module;
import seedu.duke.tool.Access;
import seedu.duke.level.Admin;
import seedu.duke.tool.Ui;

public class addChapterCommand extends Command {
    public addChapterCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(Access access, Ui ui, Admin admin) {
        String filter = fullCommand.replace("addChapter ", "");
        if(access.getModuleLevel() != "") {
            admin.addChapter(filter, access.getModuleLevel());
        }
    }
}
