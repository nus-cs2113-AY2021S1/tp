package seedu.dietbook.command;

import seedu.dietbook.Manager;
import seedu.dietbook.checker.InputChecker;
import seedu.dietbook.ui.Ui;
import seedu.dietbook.exception.DietException;

//@@author tikimonarch
public class HelpCommand extends Command {
    private String input;

    public HelpCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(Manager manager, Ui ui) throws DietException {
        if (commandCount == 1) {
            throw new DietException("Please enter your name first!");
        } else if (commandCount == 2) {
            throw new DietException("Please enter your basic information first!");
        }
        InputChecker.checkSingleCommand(this.input);
        ui.printHelpCommandMessage();
    }
}
