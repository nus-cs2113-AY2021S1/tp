package seedu.dietbook.command;

import seedu.dietbook.Manager;
import seedu.dietbook.checker.InputChecker;
import seedu.dietbook.ui.Ui;
import seedu.dietbook.exception.DietException;

public class ClearCommand extends Command {
    String input;

    public ClearCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(Manager manager, Ui ui) throws DietException {
        if (Manager.commandCount == 1) {
            throw new DietException("Please enter your name first!");
        } else if (Manager.commandCount == 2) {
            throw new DietException("Please enter your basic information first!");
        }
        InputChecker.checkSingleCommand(this.input);
        ui.printClearFoodListMessage();
        manager.getFoodList().clear();
        manager.save();
    }
}
