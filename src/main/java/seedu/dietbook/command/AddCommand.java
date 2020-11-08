package seedu.dietbook.command;

import seedu.dietbook.Manager;
import seedu.dietbook.checker.InputChecker;
import seedu.dietbook.parser.Parser;
import seedu.dietbook.ui.Ui;
import seedu.dietbook.exception.DietException;

public class AddCommand extends Command {
    String input;

    public AddCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(Manager manager, Ui ui) throws DietException {
        if (commandCount == 1) {
            throw new DietException("Please enter your name first!");
        } else if (commandCount == 2) {
            throw new DietException("Please enter your basic information first!");
        }
        InputChecker.checkSlashes(this.input);
        ui.printNewFood(Parser.getProcessedAdd(this.input, manager.getFoodList()));
        manager.setCalculator();
    }
}
