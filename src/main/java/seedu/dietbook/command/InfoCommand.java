package seedu.dietbook.command;

import seedu.dietbook.Manager;
import seedu.dietbook.Ui;
import seedu.dietbook.exception.DietException;
import seedu.dietbook.parser.Parser;

public class InfoCommand extends Command {
    String userInput;

    public InfoCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(Manager manager, Ui ui) throws DietException {
        if (commandCount == 1) {
            throw new DietException("Please enter your name first!");
        } else if (commandCount != 2) {
            throw new DietException("Basic information has already been input!");
        }
        Parser.executeProcessedInfo(this.userInput, manager);
        commandCount++;
        ui.printInitialisationCompleteMessage();
    }
}
