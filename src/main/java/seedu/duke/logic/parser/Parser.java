package seedu.duke.logic.parser;

import seedu.duke.exceptions.CustomException;
import seedu.duke.exceptions.ExceptionType;
import seedu.duke.logic.commands.AddFavCommand;
import seedu.duke.logic.commands.AllBusCommand;
import seedu.duke.logic.commands.BusCommand;
import seedu.duke.logic.commands.DineCommand;
import seedu.duke.logic.commands.DineInfoCommand;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.ExitCommand;
import seedu.duke.logic.commands.HelpCommand;
import seedu.duke.logic.commands.ListFavCommand;
import seedu.duke.logic.commands.ListStopsCommand;
import seedu.duke.logic.commands.RouteCommand;
import seedu.duke.logic.commands.RouteMapCommand;
import seedu.duke.logic.commands.favcommand.DeleteFavCommand;
import seedu.duke.logic.commands.favcommand.DescFavCommand;
import seedu.duke.logic.commands.favcommand.ExecFavCommand;
import seedu.duke.logic.commands.ResetSearchFreqCommand;

public class Parser {

    private String userInput;
    private String previousInput;

    public Parser(String userInput) {
        this.userInput = userInput;
    }

    public void setUserInput(String userInput) {
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

        String[] parts = splitCommands(2, "\\s+");
        String command = parts[0];
        Boolean isValidCommand = true;
        Command com;
        switch (command) {
        case "/route":
            com = new RouteCommand(parts[1]);
            break;
        case "/routemap":
            com = new RouteMapCommand(parts[1]);
            break;
        case "/bus":
            com = new BusCommand(parts[1]);
            break;
        case "/allbus":
            com = new AllBusCommand();
            break;
        case "/liststops":
            com = new ListStopsCommand();
            break;
        case "/dine":
            com = new DineCommand(parts[1]);
            break;
        case "/dineinfo":
            com = new DineInfoCommand(parts[1]);
            break;
        case "/help":
            com = new HelpCommand();
            break;
        case "/reset":
            com = new ResetSearchFreqCommand();
            break;
        case "/exit":
            com = new ExitCommand();
            break;
        case "/addfav":
            isValidCommand = false;
            com = new AddFavCommand(previousInput, parts[1]);
            break;
        case "/listfav":
            com = new ListFavCommand();
            break;
        case "/deletefav":
            com = new DeleteFavCommand();
            break;
        case "/descfav":
            com = new DescFavCommand();
            break;
        case "/execfav":
            com = new ExecFavCommand();
            break;
        default:
            isValidCommand = false;
            throw new CustomException(ExceptionType.INVALID_COMMAND);
        }

        if (isValidCommand) {
            previousInput = userInput;
            System.out.println(previousInput);
        }

        com.executeCommand();
        return com.isOngoing();

    }

    public String getUserInput() {
        return userInput;
    }
}
