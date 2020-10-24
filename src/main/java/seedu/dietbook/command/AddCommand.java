package seedu.dietbook.command;

import seedu.dietbook.Manager;
import seedu.dietbook.Ui;

public class AddCommand extends Command {
    String foodName;

    public AddCommand(String foodName) {
        this.foodName = foodName;
    }

    @Override
    public void execute(Manager manager, Ui ui) {
        ui.printNewFood(this.foodName);
        manager.setCalculator();
    }
}
