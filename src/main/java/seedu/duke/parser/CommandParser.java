package seedu.duke.parser;

public class CommandParser {
    private static final String COMMAND_WORD_BYE = "bye";
    private static final String COMMAND_WORD_LIST = "list";
    private static final String COMMAND_WORD_ADD_TEST = "add test";
    private static final String COMMAND_WORD_ADD_CLASS = "add class";
    private static final String COMMAND_WORD_ADD_CCA = "add cca";
    private static final String COMMAND_WORD_DELETE = "delete";
    private static final String COMMAND_WORD_HELP = "help";
    private final String userInput;
    private CommandType commandType;

    public CommandParser(String userInput) {
        this.userInput = userInput;
        commandType = null;
    }

    public CommandType parseCommand() {
        extractCommand();
        return commandType;
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
}
