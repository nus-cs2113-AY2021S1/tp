package seedu.dietbook.command;

import seedu.dietbook.Manager;
import seedu.dietbook.Ui;

public class HelpCommand extends Command {
    @Override
    public void execute(Manager manager, Ui ui) {
        ui.printTutorialMessage();
    }
}
