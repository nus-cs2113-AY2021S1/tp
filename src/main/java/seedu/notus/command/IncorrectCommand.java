package seedu.notus.command;

import com.diogonunes.jcolor.Attribute;
import seedu.notus.ui.Formatter;

import static com.diogonunes.jcolor.Ansi.colorize;

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
        return Formatter.formatString(colorize(message, Attribute.RED_TEXT()));
    }
}
