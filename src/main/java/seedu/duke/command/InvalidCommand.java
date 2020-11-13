package seedu.duke.command;

import static seedu.duke.Trakcal.logging;
import static seedu.duke.ui.ExceptionMessages.print;

//@@author 1-Karthigeyan-1
/**
 * Represents an invalid command.
 */
public class InvalidCommand extends Command {

    String invalidCommandMessage;

    public InvalidCommand(String invalidCommandMessage) {
        this.invalidCommandMessage = invalidCommandMessage;
    }


    @Override
    public void execute() {
        logging.writeToLogInfo("Executing invalid command.");
        print(invalidCommandMessage);
    }
}
