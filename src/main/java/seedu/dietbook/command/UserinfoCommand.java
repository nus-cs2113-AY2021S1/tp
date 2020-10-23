package seedu.dietbook.command;

import seedu.dietbook.Manager;
import seedu.dietbook.Ui;

public class UserinfoCommand extends Command {
    @Override
    public void execute(Manager manager, Ui ui) {
        ui.printPersonInfo(manager.getPerson().toString());
    }
}
