package seedu.dietbook.command;

import seedu.dietbook.Manager;
import seedu.dietbook.checker.InputChecker;
import seedu.dietbook.ui.Ui;
import seedu.dietbook.exception.DietException;
import seedu.dietbook.parser.Parser;

//@@author tikimonarch
public class InfoCommand extends Command {
    String userInput;

    public InfoCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(Manager manager, Ui ui) throws DietException {
        if (Manager.commandCount == 1) {
            throw new DietException("Please enter your name first!");
        } else if (Manager.commandCount != 2) {
            throw new DietException("Basic information has already been input!");
        }
        InputChecker.checkSlashes(this.userInput);
        Parser.executeProcessedInfo(this.userInput, manager);
        Manager.commandCount++;
        ui.printInitialisationCompleteMessage();
    }
}
