package seedu.duke.command;

import seedu.duke.ui.Formatter;

/**
 * Represents an incorrect command. Upon execution, produces a message.
 */
public class IncorrectCommand extends Command {

    public final String message;

    public IncorrectCommand(String message) {
        this.message = message;
    }

    @Override
    public String execute() {
        return Formatter.formatString(message, false);
    }

}
