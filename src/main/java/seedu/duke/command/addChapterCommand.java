package seedu.duke.command;

import seedu.duke.level.Admin;
import seedu.duke.level.Chapter;
import seedu.duke.level.Module;
import seedu.duke.tool.Access;
import seedu.duke.tool.Storage;
import seedu.duke.tool.Ui;

public class addChapterCommand extends Command {
    public addChapterCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(Access access, Ui ui, Storage storage) {
        String filter = fullCommand.replace("addChapter ", "");
        if(access.getModuleLevel() != "") {
            Module newModule = access.getModule();
            newModule.add(new Chapter(filter));
            access.setModule(newModule);
            storage.createChapter(filter, access.getModuleLevel());
        }
    }
}
