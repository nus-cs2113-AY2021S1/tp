package seedu.dietbook.command;

import seedu.dietbook.Manager;
import seedu.dietbook.Ui;
import seedu.dietbook.exception.DietException;

public class DataCommand extends Command {

    @Override
    public void execute(Manager manager, Ui ui) throws DietException {
        if (commandCount == 1) {
            throw new DietException("Please enter your name first!");
        } else if (commandCount == 2) {
            throw new DietException("Please enter your basic information first!");
        }
        manager.getDataBase().init();
        //ui.printDatabase(manager.getDataBase().getFoodList());
    }
}
