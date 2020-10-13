package seedu.duke.parser;

import seedu.duke.Duke;
import seedu.duke.command.ByeCommand;
import seedu.duke.command.Command;
import seedu.duke.command.InvalidCommand;
import seedu.duke.command.SearchCommand;
import seedu.duke.data.exception.DukeException;

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
        case "bye":
            return new ByeCommand();
        default:
            return new InvalidCommand("Invalid command! Please try again.");
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
