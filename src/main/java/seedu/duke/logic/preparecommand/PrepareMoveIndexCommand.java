package seedu.duke.logic.preparecommand;

import seedu.duke.command.Command;
import seedu.duke.command.InvalidCommand;
import seedu.duke.command.MoveActivityCommand;

import static seedu.duke.Trakcal.logging;
import static seedu.duke.ui.ExceptionMessages.displayIndexNotNumberExceptionMessage;
import static seedu.duke.ui.Ui.PROMPT_USER_OF_HELP_MESSAGE;

//@@author chewyang
/**
 * Prepares move command.
 */
public class PrepareMoveIndexCommand extends PrepareCommand {

    public static final String SPACE = " ";
    public static final String FROM_TAG = "from/";
    public static final String BELOW_TAG = "below/";
    public static final String BOTH_TAGS_MISSING_ERROR_MESSAGE = "'from/' and 'below/' keyword is missing!\n "
            + PROMPT_USER_OF_HELP_MESSAGE;
    public static final String FROM_TAG_MISSING_ERROR_MESSAGE = "'from/' keyword is missing!\n"
            + PROMPT_USER_OF_HELP_MESSAGE;
    public static final String BELOW_TAG_MISSING_ERROR_MESSAGE = "'below/' keyword is missing!\n"
            + PROMPT_USER_OF_HELP_MESSAGE;
    private static final String INDEX_NOT_A_NUMBER_MESSAGE = "Accessing a list with a index that is not a number, "
            + "move unsuccessful";

    public PrepareMoveIndexCommand(String[] description) {
        super(description);
    }

    /**
     * Prepares the arguments needed for moving an activity from one index to another.
     *
     * @return the moveCommand
     * @throws IndexOutOfBoundsException if the index is not valid
     */
    @Override
    public Command prepareCommand() throws IndexOutOfBoundsException {
        //Removing additional spaces in the user's input
        String after = description[1].trim().replaceAll(" +", SPACE).toLowerCase();


        int firstIndex = after.indexOf(FROM_TAG) + FROM_TAG.length(); //index after first keyword
        int secondIndex = after.indexOf(BELOW_TAG) + BELOW_TAG.length(); //index after second keyword

        if (!after.contains(FROM_TAG) && !after.contains(BELOW_TAG)) {
            return new InvalidCommand(BOTH_TAGS_MISSING_ERROR_MESSAGE);
        } else if (!after.contains(FROM_TAG)) {
            return new InvalidCommand(FROM_TAG_MISSING_ERROR_MESSAGE);
        } else if (!after.contains(BELOW_TAG)) {
            return new InvalidCommand(BELOW_TAG_MISSING_ERROR_MESSAGE);
        }


        String firstIndexString = after.substring(firstIndex).trim().split(SPACE)[0];
        String secondIndexString = after.substring(secondIndex).trim().split(SPACE)[0];
        int indexToBeChanged;
        int indexToBeInsertedBelow;
        try {

            indexToBeChanged = Integer.parseInt(firstIndexString);
            assert indexToBeChanged > 0 : "Index should be more than 0";

            indexToBeInsertedBelow = Integer.parseInt(secondIndexString);
            assert indexToBeInsertedBelow >= 0 : "Index should be more than 0";

            return new MoveActivityCommand(indexToBeChanged, indexToBeInsertedBelow);
        } catch (NumberFormatException e) {
            displayIndexNotNumberExceptionMessage();
            logging.writeToLogWarning(INDEX_NOT_A_NUMBER_MESSAGE);
        }
        return null;
    }
}
