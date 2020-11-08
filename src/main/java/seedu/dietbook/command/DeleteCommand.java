package seedu.dietbook.command;

import seedu.dietbook.checker.InputChecker;
import seedu.dietbook.exception.DietException;
import seedu.dietbook.Manager;
import seedu.dietbook.ui.Ui;

public class DeleteCommand extends Command {
    int index;
    String input;

    public DeleteCommand(String input, int index) {
        this.input = input;
        this.index = index;
    }

    @Override
    public void execute(Manager manager, Ui ui) throws DietException {
        if (commandCount == 1) {
            throw new DietException("Please enter your name first!");
        } else if (commandCount == 2) {
            throw new DietException("Please enter your basic information first!");
        }
        InputChecker.checkDeleteCommand(this.input);
        try {
            ui.printDeletedFood(manager.getFoodList().delete(this.index));
            manager.setCalculator();
        } catch (IndexOutOfBoundsException e) {
            throw new DietException("No such index!");
        }
    }
}
