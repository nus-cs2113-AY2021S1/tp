package anichan.parser;

import anichan.commands.AddToWatchlistCommand;
import anichan.exception.AniException;
import anichan.logger.AniLogger;

import java.util.logging.Level;
import java.util.logging.Logger;

//@@author michaeldinata
/**
 * Handles parsing for add to watchlist command.
 */
public class AddToWatchlistParser extends CommandParser {
    protected static final String ANIME_ID = "Anime ID!";
    protected static final String TOO_MUCH_ARGUMENTS = "Add To Watchlist command" + TOO_MUCH_FIELDS;
    protected static final String OUT_OF_BOUND_INDEX_ERROR = "Anime ID is invalid!";
    private static final Logger LOGGER = AniLogger.getAniLogger(AddToWatchlistParser.class.getName());
    
    private AddToWatchlistCommand addToWatchlistCommand;

    /**
     * Creates a new instance of AddToWatchlistParser.
     */
    public AddToWatchlistParser() {
        addToWatchlistCommand = new AddToWatchlistCommand();
    }

    /**
     * Parses the specified command description.
     * 
     * @param description the specified command description
     * @return initialised {@code AddToWatchlistCommand} object
     * @throws AniException when an error occurred while parsing the command description
     */
    public AddToWatchlistCommand parse(String description) throws AniException {
        description = description.trim();
        
        if (description == null || description.isBlank()) {
            throw new AniException(DESCRIPTION_CANNOT_BE_NULL);
        }

        parameterParser(description);
        LOGGER.log(Level.INFO, "Parameter parsed properly");

        return addToWatchlistCommand;
    }

    /**
     * Parses the parameter provided in the command description.
     *
     * @param fieldGiven a String Array containing the value given
     * @throws AniException when an error occurred while parsing the parameters
     */
    private void parameterParser(String fieldGiven) throws AniException {
        String fieldValue = fieldGiven.trim();
        String[] fieldParts = fieldValue.split(SPLIT_WHITESPACE);

        if (fieldParts.length > 1) {
            throw new AniException(TOO_MUCH_ARGUMENTS);
        }
        isIntegerCheck(fieldValue, ANIME_ID);

        try {
            addToWatchlistCommand.setAnimeIndex(Integer.parseInt(fieldValue));
        } catch (NumberFormatException e) {
            throw new AniException(OUT_OF_BOUND_INDEX_ERROR);
        }
    }
}
