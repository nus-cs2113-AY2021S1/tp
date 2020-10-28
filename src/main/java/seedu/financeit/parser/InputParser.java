package seedu.financeit.parser;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.exceptions.EmptyCommandStringException;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.LoggerCentre;
import seedu.financeit.utils.RegexMatcher;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.regex.Matcher;

//@@author Artemis-Hunt
public class InputParser {
    private Matcher matcher;
    private static InputParser inputParser = null;

    private InputParser() {
    }

    public static InputParser getInstance() {
        if (inputParser == null) {
            inputParser = new InputParser();
        }
        return inputParser;
    }

    /**
     * This will parse the command into 4 small parts.
     * Example: expense 5000 for 08
     * Output: expense;5000;for;08
     * @param userCommand the command user enter
     * @return
     */
    public String[] parseGoalCommand(String userCommand) {
        String[] newCommand = userCommand.split(" ", 4);
        return newCommand;
    }

    /**
     * This will parse the edit command into 5 small parts.
     * Example: edit expense 2000 for 08
     * Output: edit;expense;2000;for;08
     * @param userCommand command user entered
     * @return
     */
    public String[] parseEditCommand(String userCommand) {
        String[] newCommand = userCommand.split(" ", 5);
        return newCommand;
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
            commandString = input.toLowerCase();
            return new CommandPacket(commandString, params);
        }

        try {
            //Split into [<command>, <params string>]
            buffer = input.split(separator, 2);
            if (buffer[0].equals(" ")) {
                throw new EmptyCommandStringException();
            }
            commandString = buffer[0].toLowerCase();
            String paramSubstring = separator + buffer[1];
            params = ParamsParser.getInstance().parseParams(paramSubstring);
        } catch (EmptyCommandStringException e) {
            UiManager.printWithStatusIcon(Constants.PrintType.SYS_MSG, e.getMessage());
        }
        CommandPacket packet = new CommandPacket(commandString, params);
        LoggerCentre.loggerInputParser.log(Level.INFO, packet.toString());
        return packet;
    }

    public String getSeparator(String input) {
        //Matcher matches <space><separator><paramType><space>, so (matched index + 1) gives the separator
        int separatorIndex = matcher.start() + 1;
        return String.valueOf(input.charAt(separatorIndex));
    }
}