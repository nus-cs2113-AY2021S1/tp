package seedu.dietbook.command;

import seedu.dietbook.DietBook;
import seedu.dietbook.Manager;
import seedu.dietbook.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(Manager manager, Ui ui) {
        ui.printExitMessage();
        DietBook.isExit = true;
    }
}
