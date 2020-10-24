package seedu.dietbook.command;

import seedu.dietbook.Manager;
import seedu.dietbook.Ui;

public class DataCommand extends Command {

    @Override
    public void execute(Manager manager, Ui ui) {
        manager.getDataBase().init();
        ui.printDatabase(manager.getDataBase().getFoodList());
    }
}
