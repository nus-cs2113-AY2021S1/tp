package seedu.dietbook.command;


import seedu.dietbook.Manager;
import seedu.dietbook.Ui;
import seedu.dietbook.exception.DietException;


public class NameCommand extends Command {
    String name;

    public NameCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute(Manager manager, Ui ui) throws DietException {
        if (commandCount != 1) {
            throw new DietException("Name has already been entered!");
        }
        commandCount++;
        manager.setName(this.name);
        ui.printAskForUserInfoMessage(manager.getName());
    }
}
