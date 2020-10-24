package seedu.dietbook.command;

import seedu.dietbook.exception.DietException;
import seedu.dietbook.Manager;
import seedu.dietbook.Ui;

public class DeleteCommand extends Command {
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Manager manager, Ui ui) throws DietException {
        try {
            ui.printDeletedFood(manager.getFoodList().delete(this.index));
            manager.setCalculator();
        } catch (IndexOutOfBoundsException e) {
            throw new DietException("No such index!");
        }
    }
}
