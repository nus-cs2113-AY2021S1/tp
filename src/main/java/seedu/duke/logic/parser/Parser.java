package seedu.duke.logic.parser;

import seedu.duke.exceptions.CustomException;
import seedu.duke.exceptions.ExceptionType;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.ExitCommand;
import seedu.duke.logic.commands.RouteMapCommand;

public class Parser {

    private String userInput;
    private Command com;

    public Parser(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Split sentence to derive command and rest of the user input.
     *
     * @return String Array with two element: Command and the rest of the user input
     */
    private String[] splitCommands(int length) {
        final String[] split = userInput.trim().split("\\s+", length);
        return split.length == length ? split : new String[]{split[0], " "};
    }

    public boolean extractType() throws CustomException {


        String command = splitCommands(2)[0];


        switch (command) {
        //        case "/route":
        //
        //            break;
                case "/routemap":
                    com = new RouteMapCommand();

                    break;
        //        case "/bus":
        //
        //            break;
        //        case "/allbus":
        //
        //            break;
        //        case "/liststops":
        //
        //            break;
        //        case "/help":
        //
        //            break;
        case "/exit":
            com = new ExitCommand();
            break;
        default:
            throw new CustomException(ExceptionType.INVALID_COMMAND);
        }
        com.executeCommand();
        return com.isOngoing();

    }

}
