package seedu.duke.command;

import seedu.duke.tool.Access;
import seedu.duke.tool.Storage;
import seedu.duke.tool.Ui;

public class randomCommand extends Command {
    public randomCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(Access access, Ui ui, Storage storage) {
        System.out.println("Sorry, I cannot understand this command");
    }
}
