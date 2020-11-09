package seedu.dietbook.command;


import seedu.dietbook.Manager;
import seedu.dietbook.ui.Ui;
import seedu.dietbook.exception.DietException;


public class NameCommand extends Command {
    String name;

    public NameCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute(Manager manager, Ui ui) throws DietException {
        if (Manager.commandCount != 1) {
            throw new DietException("Name has already been entered!");
        }
        Manager.commandCount++;
        manager.setName(this.name);
        ui.printAskForUserInfoMessage(manager.getName());
    }
}
