package seedu.financeit.parser;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.exceptions.EmptyCommandStringException;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.RegexMatcher;

import java.util.HashMap;
import java.util.regex.Matcher;

public class InputParser {
    protected Matcher matcher;

    public InputParser() {
    }

    public String getSeparator(String input) {
        //Matcher matches <space><separator><paramType><space>, so (matched index + 1) gives the separator
        int separatorIndex = matcher.start() + 1;
        return String.valueOf(input.charAt(separatorIndex));
    }

    /**
     * Example input: deadline /by tomorrow /note skip page 70.
     * commandString: "deadline"
     * CommandPacket created:
     * {
     *  commandType: ADD_DEADLINE
     *  params: HashMap< String, String >
     *  {
     *   "by": "tomorrow"
     *   "note": "skip page 70"
     *  }
     * }
     */
    public CommandPacket parseInput(String input) {
        String commandString = "";
        HashMap<String, String> params = new HashMap<>();
        String[] buffer;
        String separator = "";
        boolean paramsExist = false;

        //Split into [command, rest of input]
        //Check for existence of command title

        try {
            input = " " + input + " ";
            matcher = RegexMatcher.paramMatcher(input);
            separator = getSeparator(input);
        } catch (java.lang.IllegalStateException exception) {
            //No params provided
            commandString = input;
            return new CommandPacket(commandString, params);
        }

        try {
            //Split into [<command>, <params string>]
            buffer = input.split(separator, 2);
            if (buffer[0].equals(" ")) {
                throw new EmptyCommandStringException();
            }
            commandString = buffer[0];
            String paramSubstring = separator + buffer[1];
            params = new ParamsParser(paramSubstring).parseParams();
        } catch (EmptyCommandStringException e) {
            UiManager.printWithStatusIcon(Constants.PrintType.SYS_MSG, e.getMessage());
        }
        return new CommandPacket(commandString, params);
    }
}