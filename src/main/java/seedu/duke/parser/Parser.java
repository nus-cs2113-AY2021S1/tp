package seedu.duke.parser;

import seedu.duke.Duke;
import seedu.duke.command.*;
import seedu.duke.data.exception.DukeException;

import javax.swing.text.View;

public class Parser {

    public static Command parseCommand(String userInput) {
        String[] userInputSplit = userInput.trim().split(" ");
        String commandString = userInputSplit[0].toLowerCase();

        switch (commandString) {
        case "search":
            try {
                return parseSearch(userInputSplit);
            } catch (DukeException e) {
                return new InvalidCommand(e.getMessage());
            }
        case "buy":
            try {
                return parseBuy(userInputSplit);
            } catch (DukeException e) {
                return new InvalidCommand(e.getMessage());
            }
        case "bye":
            return new ByeCommand();
        case "view":
            return new ViewCommand();
        default:
            return new InvalidCommand("Invalid command! Please try again.");
        }
    }

    public static Command parseBuy(String[] userInputSplit) throws DukeException {
        try {
            if (!userInputSplit[1].startsWith("/")) {
                throw new DukeException("Please enter the ticker symbol of the company that you want to buy!");
            }
            int quantity = Integer.parseInt(userInputSplit[2]);
            String symbol = userInputSplit[1].substring(1);
            BuyCommand buyCommand = new BuyCommand(symbol, quantity);
            return buyCommand;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(("Please enter a valid integer for the quantity of stocks that you want to buy!"));
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid integer for the quantity of stocks that you want to buy!");
        }
    }

    public static Command parseSearch(String[] userInputSplit) throws DukeException {
        try {
            if (!userInputSplit[1].startsWith("/")) {
                throw new DukeException("Please enter the ticker symbol of the company!");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(("Please enter the ticker symbol of the company you would like to search for!"));
        }

        SearchCommand searchCommand = new SearchCommand(userInputSplit[1].substring(1));

        return searchCommand;
    }

}
