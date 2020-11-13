package seedu.duke.logic.parser;

import seedu.duke.command.Command;
import seedu.duke.command.CreateNewSetCommand;
import seedu.duke.command.InvalidCommand;
import seedu.duke.exception.ListNotFoundException;
import seedu.duke.logic.preparecommand.PrepareAddCommand;
import seedu.duke.logic.preparecommand.PrepareAddSetCommand;
import seedu.duke.logic.preparecommand.PrepareByeCommand;
import seedu.duke.logic.preparecommand.PrepareCommand;
import seedu.duke.logic.preparecommand.PrepareDeleteCommand;
import seedu.duke.logic.preparecommand.PrepareEditCommand;
import seedu.duke.logic.preparecommand.PrepareFindCommand;
import seedu.duke.logic.preparecommand.PrepareGraphCommand;
import seedu.duke.logic.preparecommand.PrepareHelpCommand;
import seedu.duke.logic.preparecommand.PrepareListCommand;
import seedu.duke.logic.preparecommand.PrepareMoveIndexCommand;
import seedu.duke.logic.preparecommand.PrepareNewSetCommand;
import seedu.duke.logic.preparecommand.PrepareUserCommand;

import static seedu.duke.Trakcal.logging;
import static seedu.duke.ui.ExceptionMessages.displayInvalidInputErrorMessage;
import static seedu.duke.ui.ExceptionMessages.displayListNotFoundExceptionMessage;
import static seedu.duke.ui.ExceptionMessages.displayStringIndexOutOfBoundsExceptionMessage;

//@@author 1-Karthigeyan-1
/**
 * Initialises parser class.
 */
public class CommandParser extends Parser {
    public static final String SPACE = " ";
    public static final int SPLIT_LIMIT = 2;

    private static final String USER_COMMAND = "user";
    private static final String CREATESET_COMMAND = "createset";
    private static final String ADD_COMMAND = "add";
    private static final String ADDSET_COMMAND = "addset";
    private static final String FIND_COMMAND = "find";
    private static final String EDIT_COMMAND = "edit";
    private static final String DELETE_COMMAND = "delete";
    private static final String LIST_COMMAND = "list";
    private static final String HELP_COMMAND = "help";
    private static final String MOVE_COMMAND = "move";
    private static final String BYE_COMMAND = "bye";
    private static final String GRAPH_COMMAND = "graph";


    /**
     * Store details in the class.
     *
     * @param userInput user from the user
     */
    public CommandParser(String userInput) {
        super(userInput);
    }

    /**
     * Parses commands input by user.
     *
     * @return Command type
     */
    @Override
    public Command parseArgument() {
        this.userInput = checkExtraSpaces();
        logging.writeToLogInfo("UserInput about to be parsed.");
        String[] arguments = userInput.split(SPACE, SPLIT_LIMIT);
        PrepareCommand prepareCorrectCommand;
        Command command  = null;
        try {
            switch (arguments[0].toLowerCase()) {
            case USER_COMMAND:
                prepareCorrectCommand = new PrepareUserCommand(arguments);
                break;
            case CREATESET_COMMAND:
                prepareCorrectCommand = new PrepareNewSetCommand(arguments);
                break;
            case ADD_COMMAND:
                prepareCorrectCommand = new PrepareAddCommand(arguments);
                break;
            case ADDSET_COMMAND:
                prepareCorrectCommand = new PrepareAddSetCommand(arguments);
                break;
            case FIND_COMMAND:
                prepareCorrectCommand = new PrepareFindCommand(arguments);
                break;
            case EDIT_COMMAND:
                prepareCorrectCommand = new PrepareEditCommand(arguments);
                break;
            case DELETE_COMMAND:
                prepareCorrectCommand = new PrepareDeleteCommand(arguments);
                break;
            case LIST_COMMAND:
                prepareCorrectCommand = new PrepareListCommand(arguments);
                break;
            case HELP_COMMAND:
                prepareCorrectCommand = new PrepareHelpCommand(arguments);
                break;
            case MOVE_COMMAND:
                prepareCorrectCommand = new PrepareMoveIndexCommand(arguments);
                break;
            case BYE_COMMAND:
                prepareCorrectCommand = new PrepareByeCommand(arguments);
                break;
            case GRAPH_COMMAND:
                prepareCorrectCommand = new PrepareGraphCommand(arguments);
                break;
            default:
                return new InvalidCommand(displayInvalidInputErrorMessage());
            }
            command = prepareCorrectCommand.prepareCommand();
        } catch (StringIndexOutOfBoundsException e) {
            displayStringIndexOutOfBoundsExceptionMessage();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        } catch (ListNotFoundException e) {
            displayListNotFoundExceptionMessage();
        }
        return command;
    }

}
