package seedu.notus.command;

import seedu.notus.ui.Formatter;

//@@author Chongjx
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
        return Formatter.formatString(message);
    }

}
