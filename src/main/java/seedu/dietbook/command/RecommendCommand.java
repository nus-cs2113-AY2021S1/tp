package seedu.dietbook.command;

import seedu.dietbook.Manager;
import seedu.dietbook.Ui;
import seedu.dietbook.checker.InputChecker;
import seedu.dietbook.exception.DietException;
import seedu.dietbook.person.Person;

public class RecommendCommand extends Command {
    private Person person;
    private String input;

    public RecommendCommand(Person person, String input) {
        this.person = person;
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
        int recommendation = manager.getCalculator().calculateRecomendation(this.person);
        ui.printCalorieRecommendation(this.person.getName(), recommendation);
    }
}
