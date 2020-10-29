package seedu.duke.logic.parser;

import seedu.duke.exceptions.CustomException;
import seedu.duke.exceptions.ExceptionType;

import seedu.duke.logic.commands.buscommand.AllBusCommand;
import seedu.duke.logic.commands.buscommand.BusCommand;
import seedu.duke.logic.commands.dinecommand.DineCommand;
import seedu.duke.logic.commands.dinecommand.DineInfoCommand;
import seedu.duke.logic.commands.commons.Command;
import seedu.duke.logic.commands.commons.ExitCommand;
import seedu.duke.logic.commands.commons.HelpCommand;
import seedu.duke.logic.commands.buscommand.ListStopsCommand;
import seedu.duke.logic.commands.buscommand.RouteCommand;
import seedu.duke.logic.commands.buscommand.RouteMapCommand;
import seedu.duke.logic.commands.favcommand.AddFavCommand;
import seedu.duke.logic.commands.favcommand.ClearFavCommand;
import seedu.duke.logic.commands.favcommand.DeleteFavCommand;
import seedu.duke.logic.commands.favcommand.DescFavCommand;
import seedu.duke.logic.commands.favcommand.ExecFavCommand;
import seedu.duke.logic.commands.favcommand.ListFavCommand;
import seedu.duke.logic.commands.buscommand.ResetSearchFreqCommand;


public class Parser {

    private String userInput;
    private String previousInput = null;

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
        String command = parts[0].toLowerCase();
        Command com;
        boolean isVoidFunction = false;
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
            isVoidFunction = true;
            com = new AllBusCommand();
            break;
        case "/liststops":
            isVoidFunction = true;
            com = new ListStopsCommand();
            break;
        case "/dine":
            com = new DineCommand(parts[1]);
            break;
        case "/dineinfo":
            com = new DineInfoCommand(parts[1]);
            break;
        case "/help":
            isVoidFunction = true;
            com = new HelpCommand();
            break;
        case "/reset":
            isVoidFunction = true;
            com = new ResetSearchFreqCommand();
            break;
        case "/exit":
            isVoidFunction = true;
            com = new ExitCommand();
            break;
        case "/addfav":
            com = new AddFavCommand(previousInput, parts[1]);
            break;
        case "/listfav":
            isVoidFunction = true;
            com = new ListFavCommand();
            break;
        case "/deletefav":
            com = new DeleteFavCommand(parts[1]);
            break;
        case "/descfav":
            com = new DescFavCommand(parts[1]);
            break;
        case "/execfav":
            com = new ExecFavCommand(parts[1]);
            break;
        case "/clearfav":
            isVoidFunction = true;
            com = new ClearFavCommand();
            break;
        default:
            throw new CustomException(ExceptionType.INVALID_COMMAND);
        }

        if (isVoidFunction && !parts[1].equals(" ")) {
            throw new CustomException(ExceptionType.EXTRA_PARAMETERS);
        }

        com.executeCommand();

        if (!command.equals("/addfav") && !command.equals("/execfav")) {
            previousInput = userInput;
        } else {
            previousInput = null;
        }

        return com.isOngoing();

    }

    public String getUserInput() {
        return userInput;
    }
}
