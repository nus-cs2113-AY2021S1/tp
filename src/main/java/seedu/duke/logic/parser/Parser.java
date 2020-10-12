package seedu.duke.logic.parser;

import seedu.duke.exceptions.CustomException;
import seedu.duke.exceptions.ExceptionType;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.ExitCommand;
import seedu.duke.logic.commands.RouteMapCommand;
<<<<<<< HEAD
import seedu.duke.logic.commands.RouteCommand;
=======
>>>>>>> 637c5325eb4fb5eb8d7c8408ba4927f49fecf2b1

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
    String[] splitCommands(int length, String delimiter) {
        final String[] split = userInput.trim().split(delimiter, length);
        return split.length == length ? split : new String[]{split[0], " "};
    }

    public boolean extractType() throws CustomException {


<<<<<<< HEAD
        String[] parts = splitCommands(2, "\\s+");
        String command = parts[0];

=======
        String command = splitCommands(2)[0];
>>>>>>> 637c5325eb4fb5eb8d7c8408ba4927f49fecf2b1


        switch (command) {
<<<<<<< HEAD

        case "/routemap":
            com = new RouteMapCommand();
            break;
        case "/route":
            com = new RouteCommand(parts[1]);
            break;
=======
        //        case "/route":
        //
        //            break;
        case "/routemap":
            com = new RouteMapCommand();
            break;
>>>>>>> 637c5325eb4fb5eb8d7c8408ba4927f49fecf2b1
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
