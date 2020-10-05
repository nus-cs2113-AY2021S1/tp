package seedu.duke.parser;

/**
 * Represents the process of extracting out the commands, parameters and executing them.
 */
public class CommandParser {
    private static final String COMMAND_WORD_BYE = "bye";
    private static final String COMMAND_WORD_LIST = "list";
    private static final String COMMAND_WORD_ADD_TEST = "add test";
    private static final String COMMAND_WORD_ADD_CLASS = "add class";
    private static final String COMMAND_WORD_ADD_CCA = "add cca";
    private static final String COMMAND_WORD_DELETE = "delete";
    private static final String COMMAND_WORD_HELP = "help";
    private static final int INDEX_AFTER_ADD_CLASS = 10;
    private static final int INDEX_AFTER_ADD_CCA = 8;
    private static final int INDEX_AFTER_ADD_TEST = 9;
    private static final int INDEX_AFTER_DELETE = 7;

    private final String userInput;
    private CommandType commandType;
    private String parameters;

    public CommandParser(String userInput) {
        this.userInput = userInput;
        commandType = null;
        parameters = null;
    }

    public CommandType parseCommand() {
        try {
            extractCommand();
            extractParameters();
            executeCommand();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid parameters");
        }
        return commandType;
    }

    // Todo: connect with the event manager.
    private void executeCommand() {
        switch (commandType) {
        case LIST:
            break;
        case ADD_CLASS:
            break;
        case ADD_CCA:
            break;
        case ADD_TEST:
            break;
        case DELETE:
            break;
        case BYE:
            break;
        case HELP:
            break;
        default:
            System.out.println("No command ran :(");
        }
    }

    /**
     * Sets the commandType enum for the command based of the string input provided by user.
     */
    private void extractCommand() {
        if (userInput.contentEquals(COMMAND_WORD_BYE)) {
            commandType = CommandType.BYE;
        } else if (userInput.startsWith(COMMAND_WORD_LIST)) {
            commandType = CommandType.LIST;
        } else if (userInput.startsWith(COMMAND_WORD_ADD_TEST)) {
            commandType = CommandType.ADD_TEST;
        } else if (userInput.startsWith(COMMAND_WORD_ADD_CLASS)) {
            commandType = CommandType.ADD_CLASS;
        } else if (userInput.startsWith(COMMAND_WORD_ADD_CCA)) {
            commandType = CommandType.ADD_CCA;
        } else if (userInput.startsWith(COMMAND_WORD_DELETE)) {
            commandType = CommandType.DELETE;
        } else if (userInput.contentEquals(COMMAND_WORD_HELP)) {
            commandType = CommandType.HELP;
        } else {
            System.out.println("Invalid Command");
        }
    }

    /**
     * Sets the parameters by discarding the command text in the user input.
     *
     * @throws IndexOutOfBoundsException If there are no more string after the command text.
     */
    private void extractParameters() throws IndexOutOfBoundsException {
        switch (commandType) {
        case ADD_CLASS:
            parameters = userInput.substring(INDEX_AFTER_ADD_CLASS);
            break;
        case ADD_CCA:
            parameters = userInput.substring(INDEX_AFTER_ADD_CCA);
            break;
        case ADD_TEST:
            parameters = userInput.substring(INDEX_AFTER_ADD_TEST);
            break;
        case DELETE:
            parameters = userInput.substring(INDEX_AFTER_DELETE);
            break;
        case LIST:
            // List does not take any parameters
        case HELP:
            // Help does not take any parameters
        case BYE:
            // Bye does not take any parameters
        default:
            parameters = null;
        }
    }
}
