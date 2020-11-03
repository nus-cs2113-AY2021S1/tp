package anichan.parser;

import anichan.commands.ViewWatchlistCommand;
import anichan.exception.AniException;
import anichan.logger.AniLogger;

import java.util.logging.Level;
import java.util.logging.Logger;

//@@author michaeldinata
/**
 * Handles parsing for view watchlist command.
 */
public class ViewWatchlistParser extends CommandParser {
    protected static final String TOO_MUCH_ARGUMENTS = "View command" + TOO_MUCH_FIELDS;
    protected static final String WATCHLIST_ID = "Watchlist ID!";
    protected static final String OUT_OF_BOUND_INDEX_ERROR = "Watchlist ID is invalid!";
    private static final Logger LOGGER = AniLogger.getAniLogger(AddToWatchlistParser.class.getName());

    private ViewWatchlistCommand viewWatchlistCommand;

    /**
     * Creates a new instance of ViewWatchlistParser.
     */
    public ViewWatchlistParser() {
        viewWatchlistCommand = new ViewWatchlistCommand();
    }

    /**
     * Parses the specified command description.
     *
     * @param description the specified command description
     * @return initialised {@code RemoveCommand} object
     * @throws AniException when an error occurred while parsing the command description
     */
    public ViewWatchlistCommand parse(String description) throws AniException {
        description = description.trim();

        if (description != null && !description.isBlank()) {
            parameterParser(description);
        }
  
        LOGGER.log(Level.INFO, "Parameter parsed properly");

        return viewWatchlistCommand;
    }

    /**
     * Parses the parameter provided in the command description.
     *
     * @param fieldGiven a String Array containing the parameters and the value
     * @throws AniException when an error occurred while parsing the parameters
     */
    private void parameterParser(String fieldGiven) throws AniException {
        String fieldValue = fieldGiven.trim();
        String[] fieldParts = fieldValue.split(SPLIT_WHITESPACE);

        if (fieldParts.length > 1) {
            throw new AniException(TOO_MUCH_ARGUMENTS);
        }
        isIntegerCheck(fieldValue, WATCHLIST_ID);

        try {
            viewWatchlistCommand.setWatchlistIndex(Integer.parseInt(fieldValue));
        } catch (NumberFormatException e) {
            throw new AniException(OUT_OF_BOUND_INDEX_ERROR);
        }
    }
}
