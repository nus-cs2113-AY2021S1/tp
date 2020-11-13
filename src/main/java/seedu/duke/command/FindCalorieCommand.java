package seedu.duke.command;

import seedu.duke.exception.EmptyKeywordException;
import seedu.duke.exception.KeywordNotFoundException;

import static seedu.duke.Trakcal.logging;
import static seedu.duke.ui.ExceptionMessages.displayEmptyDescriptionMessage;
import static seedu.duke.ui.ExceptionMessages.displayEmptyKeywordMessage;
import static seedu.duke.ui.ExceptionMessages.displayKeywordNotFoundMessage;

import java.time.LocalDate;
import java.time.LocalDateTime;


//@@author poonchuanan
/**
 * Initialises Command to find calorie count.
 */
public class  FindCalorieCommand extends Command {
    protected LocalDate date;
    protected String calorie;

    /**
     * Find matching results based on calorie input.
     *
     * @param calorie calories to search
     */
    public FindCalorieCommand(String calorie) {
        this.date = LocalDateTime.now().toLocalDate();
        this.calorie = calorie;

    }

    @Override
    public void execute() {
        try {
            dayMap.listActivitiesContainingCalorie(calorie);
            dayMap.drawListAfterFindCommand();
        } catch (KeywordNotFoundException e) {
            displayKeywordNotFoundMessage();
            logging.writeToLogWarning("No results found after find (calorie)");
        } catch (EmptyKeywordException e) {
            displayEmptyKeywordMessage();
            logging.writeToLogWarning("Empty keyword for input (calorie)");
        }
    }
}

