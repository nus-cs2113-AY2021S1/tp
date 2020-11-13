package seedu.duke.logic.preparecommand;

import seedu.duke.command.Command;
import seedu.duke.command.CreateNewSetCommand;
import seedu.duke.exception.ListNotFoundException;

public class PrepareNewSetCommand extends PrepareCommand {
    public PrepareNewSetCommand(String[] description) {
        super(description);
    }

    @Override
    public Command prepareCommand() {
        return new CreateNewSetCommand(description[1]);
    }
}
