package seedu.dietbook.command;

import seedu.dietbook.Manager;
import seedu.dietbook.Ui;

public class ClearCommand extends Command {
    @Override
    public void execute(Manager manager, Ui ui) {
        ui.printClearFoodListMessage();
        manager.getFoodList().clear();
    }
}
