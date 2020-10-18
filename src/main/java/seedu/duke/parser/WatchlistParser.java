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

    private static final Logger LOGGER = Logger.getLogger(WatchlistParser.class.getName());

    public WatchlistCommand parse(String description) throws AniException {
        String[] paramGiven = parameterSplitter(description);
        paramIsSetCheck(paramGiven);
        if (paramGiven.length > 2) {
            throw new AniException("Watchlist command" + TOO_MUCH_FIELDS);
        } else if (!paramGiven[0].isBlank()) {
            throw new AniException(paramGiven[0] + NOT_RECOGNISED);
        }

        String[] parsedParts = parameterParser(paramGiven[1]);
        if (parsedParts.length == 1) {
            return new WatchlistCommand(parsedParts[0], BLANK);
        } else {
            return new WatchlistCommand(parsedParts[0], parsedParts[1]);
        }
    }

    private String[] parameterParser(String parameter) throws AniException {
        String[] parsedParts = parameter.split(" ", 2);
        String option = parsedParts[0];
        switch (option) {
        case CREATE_OPTION:
            checkCreationParameters(parsedParts[1]);
            break;
        case LIST_OPTION:
            checkListingParameters(parsedParts);
            break;
        case SELECT_OPTION:
            // Fallthrough because SELECT will call checkModificationParameters method too.
        case DELETE_OPTION:
            checkModificationParameters(parsedParts[1]);
            break;
        default:
            throw new AniException("Watchlist command only accepts the options: -n, -l, -s, and -d.");
        }

        return parsedParts;
    }

    private void checkCreationParameters(String watchlistName) throws AniException {
        if (watchlistName.isBlank()) {
            LOGGER.log(Level.WARNING, "Watchlist name is empty.");
            throw new AniException("Watchlist name cannot be empty!");
        }
    }

    private void checkListingParameters(String[] parsedParts) throws AniException {
        if (parsedParts.length != 1) {
            LOGGER.log(Level.WARNING, "Watchlist list option" + TOO_MUCH_FIELDS);
            throw new AniException("Watchlist list option" + TOO_MUCH_FIELDS);
        }
    }

    private void checkModificationParameters(String watchlistIndex) throws AniException {
        if (watchlistIndex.isBlank()) {
            LOGGER.log(Level.WARNING, "Watchlist index is empty.");
            throw new AniException("Watchlist index cannot be empty!");
        }

        if (!isInt(watchlistIndex)) {
            throw new AniException("\"" + watchlistIndex + "\" is not a number!");
        }
    }
}
