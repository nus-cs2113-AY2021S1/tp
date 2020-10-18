package seedu.duke.parser;

import seedu.duke.command.WatchlistCommand;
import seedu.duke.exception.AniException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class WatchlistParser extends CommandParser {
    private static final String CREATE_OPTION = "n";
    private static final String LIST_OPTION = "l";
    private static final String SELECT_OPTION = "s";
    private static final String DELETE_OPTION = "d";
    private static final String BLANK = "";

    private static final int CREATION_REQUIRED_PARAMETER_COUNT = 2;
    private static final int LIST_REQUIRED_PARAMETER_COUNT = 1;
    private static final int MODIFICATION_REQUIRED_PARAMETER_COUNT = 2;

    private static final Logger LOGGER = Logger.getLogger(WatchlistParser.class.getName());

    public WatchlistParser() {
        LOGGER.setLevel(Level.WARNING);
    }

    public WatchlistCommand parse(String description) throws AniException {
        assert description != null : "description should not be null.";
        String[] paramGiven = parameterSplitter(description);
        paramIsSetCheck(paramGiven);
        if (paramGiven.length > 2) {
            LOGGER.log(Level.WARNING, "Too many arguments.");
            throw new AniException("Watchlist command" + TOO_MUCH_FIELDS);
        } else if (!paramGiven[0].isBlank()) {
            LOGGER.log(Level.WARNING, "Not recognized command parameter: " + paramGiven[0] + "\".");
            throw new AniException(paramGiven[0] + NOT_RECOGNISED);
        }

        String[] parsedParts = parameterParser(paramGiven[1]);
        return new WatchlistCommand(parsedParts[0], parsedParts[1]);
    }

    private String[] parameterParser(String parameter) throws AniException {
        String[] parsedParts = parameter.split(" ", 2);
        String option = parsedParts[0];
        switch (option) {
        case CREATE_OPTION:
            checkCreationParameters(parsedParts);
            return parsedParts;
        case LIST_OPTION:
            checkListParameters(parsedParts);
            String[] paddedParsedParts = {option, BLANK};
            return paddedParsedParts;
        case SELECT_OPTION:
            // Fallthrough because SELECT will call checkModificationParameters method too.
        case DELETE_OPTION:
            checkModificationParameters(parsedParts);
            return parsedParts;
        default:
            LOGGER.log(Level.WARNING, "Invalid option received: \"" + option + "\".");
            throw new AniException("Watchlist command only accepts the options: -n, -l, -s, and -d.");
        }
    }

    private void checkCreationParameters(String[] parsedParts) throws AniException {
        if (parsedParts.length != CREATION_REQUIRED_PARAMETER_COUNT) {
            LOGGER.log(Level.WARNING, "Watchlist name is empty.");
            throw new AniException("Watchlist name cannot be empty!");
        }
    }

    private void checkListParameters(String[] parsedParts) throws AniException {
        if (parsedParts.length != LIST_REQUIRED_PARAMETER_COUNT) {
            LOGGER.log(Level.WARNING, "Watchlist list option" + TOO_MUCH_FIELDS);
            throw new AniException("Watchlist list option" + TOO_MUCH_FIELDS);
        }
    }

    private void checkModificationParameters(String[] parsedParts) throws AniException {
        if (parsedParts.length != MODIFICATION_REQUIRED_PARAMETER_COUNT) {
            LOGGER.log(Level.WARNING, "Watchlist index is empty.");
            throw new AniException("Watchlist index cannot be empty!");
        }

        String watchlistIndex = parsedParts[1];
        if (!isInt(watchlistIndex)) {
            LOGGER.log(Level.WARNING, "Provided a non-integer: \"" + watchlistIndex + "\".");
            throw new AniException("\"" + watchlistIndex + "\" is not a number!");
        }
    }
}
