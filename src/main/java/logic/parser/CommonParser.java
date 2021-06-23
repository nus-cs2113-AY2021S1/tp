package logic.parser;

import static ui.CommonUi.EMPTY_STRING;

//@@author tienkhoa16

/**
 * A base class for dealing with making sense of user command.
 */
public class CommonParser {

    public static final int COMMAND_ARGS_INDEX = 1;
    public static final int COMMAND_SPLIT_LIMIT = 2;
    public static final int COMMAND_TYPE_INDEX = 0;
    public static final String GREEDY_WHITE_SPACE = "\\s+";

    /**
     * Parses and returns the Command associated with the user input.
     *
     * @param userInputString User's raw input string.
     * @return Size 2 array; first element is the command type and second element is the arguments string.
     */
    public String[] parseCommand(String userInputString) {
        assert userInputString != null : "user input cannot be null";
        assert !userInputString.isEmpty() : "user input cannot be empty";

        String[] split = userInputString.trim().split(GREEDY_WHITE_SPACE, COMMAND_SPLIT_LIMIT);
        String commandType = split[COMMAND_TYPE_INDEX].toLowerCase();
        String commandArgs = (split.length == COMMAND_SPLIT_LIMIT ? split[COMMAND_ARGS_INDEX] : EMPTY_STRING);

        return new String[]{commandType, commandArgs};
    }
}
