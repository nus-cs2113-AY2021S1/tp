package seedu.duke.command;

import seedu.duke.tool.Access;
import seedu.duke.tool.Storage;
import seedu.duke.tool.Ui;

public class backChapterCommand extends Command {
    public backChapterCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(Access access, Ui ui, Storage storage) {
        access.setChapterLevel("");
    }
}
