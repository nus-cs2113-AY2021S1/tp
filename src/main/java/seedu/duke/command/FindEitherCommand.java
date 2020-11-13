package seedu.duke.command;

import seedu.duke.exception.EmptyKeywordException;
import seedu.duke.exception.FindSlashException;
import seedu.duke.exception.KeywordNotFoundException;

import static seedu.duke.Trakcal.logging;
import static seedu.duke.ui.ExceptionMessages.displayEmptyKeywordMessage;
import static seedu.duke.ui.ExceptionMessages.displayKeywordNotFoundMessage;
import static seedu.duke.ui.ExceptionMessages.displayFindSlashExceptionMessage;

import java.time.LocalDate;
import java.time.LocalDateTime;

//@@author poonchuanan
/**
 * Initialises Command to find all description tags.
 */
public class FindEitherCommand extends Command {
    protected LocalDate date;
    protected String userInput;

    /**
     * Find matching results based on input tags.
     *
     * @param userInput keywords to be matched
     */
    public FindEitherCommand(String userInput) {
        this.date = LocalDateTime.now().toLocalDate();
        this.userInput = userInput;
    }

    @Override
    public void execute() {
        try {
            dayMap.listActivitiesContainingEither(userInput);
            dayMap.drawListAfterFindCommand();
        } catch (KeywordNotFoundException e) {
            displayKeywordNotFoundMessage();
            logging.writeToLogWarning("No results found after find (either)");
        } catch (EmptyKeywordException e) {
            displayEmptyKeywordMessage();
            logging.writeToLogWarning("Empty keyword for input (either)");
        } catch (FindSlashException e) {
            displayFindSlashExceptionMessage();
            logging.writeToLogWarning("Consecutive slashes present in find input (either)");
        }
    }
}