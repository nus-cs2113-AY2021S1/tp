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
        Parser.executeProcessedInfo(this.userInput, manager);
        ui.printTutorialMessage();
    }
}
