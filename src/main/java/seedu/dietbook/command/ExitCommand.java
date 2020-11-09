package seedu.dietbook.command;

import seedu.dietbook.DietBook;
import seedu.dietbook.Manager;
import seedu.dietbook.checker.InputChecker;
import seedu.dietbook.exception.DietException;
import seedu.dietbook.ui.Ui;

public class ExitCommand extends Command {

    String input;

    public ExitCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(Manager manager, Ui ui) throws DietException {
        if (Manager.commandCount == 1) {
            throw new DietException("Please enter your name first!");
        } else if (Manager.commandCount == 2) {
            throw new DietException("Please enter your basic information first!");
        }
        manager.save();
        ui.dataSuccessfullySavedMessage();
        InputChecker.checkSingleCommand(this.input);
        ui.printExitMessage();
        DietBook.isExit = true;
    }
}
