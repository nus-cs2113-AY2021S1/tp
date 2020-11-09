package seedu.dietbook.command;

import seedu.dietbook.Manager;
import seedu.dietbook.checker.InputChecker;
import seedu.dietbook.ui.Ui;
import seedu.dietbook.exception.DietException;
import seedu.dietbook.parser.Parser;

public class EditInfoCommand extends Command {
    String userInput;

    public EditInfoCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(Manager manager, Ui ui) throws DietException {
        if (Manager.commandCount == 1) {
            throw new DietException("Please enter your name first!");
        } else if (Manager.commandCount == 2) {
            throw new DietException("Please enter your basic information first!");
        }
        InputChecker.checkSlashes(this.userInput);
        Parser.executeEditInfo(this.userInput, manager);
        ui.printEditedPersonInfo(manager.getPerson().toString());
        manager.save();
    }
}
