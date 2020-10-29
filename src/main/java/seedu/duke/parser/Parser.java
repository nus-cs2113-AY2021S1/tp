package seedu.duke.parser;

import seedu.duke.command.AddBookmarkCommand;
import seedu.duke.command.BuyCommand;
import seedu.duke.command.ByeCommand;
import seedu.duke.command.Command;
import seedu.duke.command.InvalidCommand;
import seedu.duke.command.RemoveBookmarkCommand;
import seedu.duke.command.SearchCommand;
import seedu.duke.command.SellCommand;
import seedu.duke.command.ViewBookmarkedStocksCommand;
import seedu.duke.command.ViewCommand;
import seedu.duke.command.WalletCommand;
import seedu.duke.exception.DukeException;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Parser {
    private static Logger logger = Logger.getLogger("tp");

    public static Command parseCommand(String userInput) {
        logger.log(Level.INFO, "going to start processing command");

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
        case "sell":
            try {
                return parseSell(userInputSplit);
            } catch (DukeException e) {
                return new InvalidCommand(e.getMessage());
            }
        case "bye":
            return new ByeCommand();
        case "view":
            return new ViewCommand();
        case "wallet":
            return new WalletCommand();
        case "mark":
            try {
                return parseMark(userInputSplit);
            } catch (DukeException e) {
                return new InvalidCommand(e.getMessage());
            }
        case "unmark":
            try {
                return parseUnmark(userInputSplit);
            } catch (DukeException e) {
                return new InvalidCommand(e.getMessage());
            }
        case "bookmarks":
            return new ViewBookmarkedStocksCommand();
        default:
            logger.log(Level.WARNING, "processing error when parsing command");
            return new InvalidCommand("Invalid command! Please try again.");
        }
    }

    public static Command parseMark(String[] userInputSplit) throws DukeException {
        try {
            if (!userInputSplit[1].startsWith("/")) {
                throw new DukeException("Please enter the ticker symbol of the company that you want to mark!");
            }
            String symbol = userInputSplit[1].substring(1);
            return new AddBookmarkCommand(symbol);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(("Wrong input format! E.g. mark /MSFT"));
        }
    }

    public static Command parseUnmark(String[] userInputSplit) throws DukeException {
        try {
            if (!userInputSplit[1].startsWith("/")) {
                throw new DukeException("Please enter the ticker symbol of the company that you want to unmark!");
            }
            String symbol = userInputSplit[1].substring(1);
            return new RemoveBookmarkCommand(symbol);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(("Wrong input format! E.g. unmark /MSFT"));
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
            throw new DukeException(("Wrong input format! E.g. buy /MSFT 11"));
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid integer for the quantity of stocks that you want to buy!");
        }
    }

    public static Command parseSell(String[] userInputSplit) throws DukeException {
        try {
            if (!userInputSplit[1].startsWith("/")) {
                throw new DukeException("Please enter the ticker symbol of the company that you want to buy!");
            }
            int quantity = Integer.parseInt(userInputSplit[2]);
            String symbol = userInputSplit[1].substring(1);
            SellCommand sellCommand = new SellCommand(symbol, quantity);
            return sellCommand;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Wrong input format! E.g. sell /MSFT 11");
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid integer for the quantity of stocks that you want to sell!");
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

    public static double parsePrice(double price) {
        String strPrice = String.format("%.2f", price);
        double formattedPrice = Double.parseDouble(strPrice);
        return formattedPrice;
    }

}
