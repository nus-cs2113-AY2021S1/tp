package seedu.dietbook.command;

import seedu.dietbook.Manager;
import seedu.dietbook.ui.Ui;
import seedu.dietbook.exception.DietException;
import seedu.dietbook.person.Person;

public class RecommendCommand extends Command {
    Person person;

    public RecommendCommand(Person person) {
        this.person = person;
    }

    @Override
    public void execute(Manager manager, Ui ui) throws DietException {
        if (commandCount == 1) {
            throw new DietException("Please enter your name first!");
        } else if (commandCount == 2) {
            throw new DietException("Please enter your basic information first!");
        }
        int recommendation = manager.getCalculator().calculateRecomendation(this.person);
        ui.printCalorieRecommendation(this.person.getName(), recommendation);
    }
}
