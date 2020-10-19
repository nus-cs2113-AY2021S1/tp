package seedu.duke.parser;


import seedu.duke.command.AddToWatchlistCommand;
import seedu.duke.command.AddWorkspaceCommand;
import seedu.duke.command.BookmarkAnimeCommand;
import seedu.duke.command.Command;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.WatchlistCommand;
import seedu.duke.command.RemoveCommand;
import seedu.duke.exception.AniException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Parser {
    private static final Logger LOGGER = Logger.getLogger(Parser.class.getName());

    /**
     * Prints the main menu of the application
     * and requests for command.
     */
    public Command getCommand(String fullCommand) throws AniException {
        LOGGER.setLevel(Level.WARNING);
        String[] fullCommandSplit = parseUserInput(fullCommand);
        String description = "";
        String command = fullCommandSplit[0];
        if (fullCommandSplit.length > 1) {
            description = fullCommandSplit[1];
        }

        switch (command) {
        case "adduser":
            return new AddWorkspaceCommand(description);

        case "switchuser":
            return new SwitchWorkspaceParser().parse(description);

        case "browse":
            return new BrowseParser().parse(description);

        case "watchlist":
            return new WatchlistParser().parse(description);

        case "add":
            return new AddToWatchlistParser().parse(description);
        
        case "remove":
            return new RemoveCommandParser().parse(description);

        case "bookmark":
            return new BookmarkParser().parse(description);
        
        case "info":
            return new InfoParser().parse(description);

        case "help":
            return new HelpCommand();

        case "exit":
            return new ExitCommand();

        default:
            throw new AniException("Unknown command");
        }
    }

    public static String[] parseUserInput(String input) throws AniException {
        if (input == null || input.isEmpty()) {
            LOGGER.log(Level.WARNING, "Exception occurred");
            throw new AniException("Input is empty");
        }
        LOGGER.log(Level.INFO, "Parser processing succeeded");

        String[] inputSplit = input.split(" ", 2);
        return inputSplit;
    }
}
