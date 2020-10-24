package seedu.dietbook.command;


import seedu.dietbook.Manager;
import seedu.dietbook.Ui;


public class NameCommand extends Command {
    String name;

    public NameCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute(Manager manager, Ui ui) {
        manager.setName(this.name);
        ui.printAskForUserInfoMessage(manager.getName());
    }
}
