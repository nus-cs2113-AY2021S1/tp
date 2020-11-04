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
    private static final String ANIME_ID = "Anime ID!";
    private static final String TOO_MUCH_ARGUMENTS = "Add To Watchlist command" + TOO_MUCH_FIELDS;
    private static final Logger LOGGER = AniLogger.getAniLogger(AddToWatchlistParser.class.getName());

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

        Integer animeIndex = parameterParser(description);
        LOGGER.log(Level.INFO, PARAMETER_PARSED);

        return new AddToWatchlistCommand(animeIndex);
    }

    /**
     * Parses the parameter provided in the command description and returns the field value.
     * 
     * @param fieldGiven a String Array containing the value given
     * @return the anime index to be added 
     * @throws AniException when an error occurred while parsing the parameteres
     */
    private Integer parameterParser(String fieldGiven) throws AniException {
        String fieldValue = fieldGiven.trim();
        String[] fieldParts = fieldValue.split(WHITESPACE);

        if (fieldParts.length > 1) {
            throw new AniException(TOO_MUCH_ARGUMENTS);
        }
        isIntegerCheck(fieldValue, ANIME_ID);

        return parseStringToInteger(fieldValue);
    }
}
