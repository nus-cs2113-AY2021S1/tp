package seedu.dietbook.command;

import seedu.dietbook.Manager;
import seedu.dietbook.Ui;
import seedu.dietbook.exception.DietException;
import seedu.dietbook.parser.Parser;

public class EditInfoCommand extends Command {
    String userInput;

    public EditInfoCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(Manager manager, Ui ui) throws DietException {
        if (commandCount == 1) {
            throw new DietException("Please enter your name first!");
        } else if (commandCount == 2) {
            throw new DietException("Please enter your basic information first!");
        }
        Parser.executeEditInfo(this.userInput, manager);
        ui.printEditedPersonInfo(manager.getPerson().toString());
    }
}
