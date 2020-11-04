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
    private static final String VIEW_PARAM = "v";
    private static final String TOO_MUCH_ARGUMENTS = "View command" + TOO_MUCH_FIELDS;
    private static final String WATCHLIST_ID = "Watchlist ID!";
    private static final Logger LOGGER = AniLogger.getAniLogger(AddToWatchlistParser.class.getName());

    /**
     * Parses the specified command description.
     *
     * @param description the specified command description
     * @return initialised {@code ViewCommand} object
     * @throws AniException when an error occurred while parsing the command description
     */
    public ViewWatchlistCommand parse(String description) throws AniException {
        description = description.trim();

        if (description != null && !description.isBlank()) {
            String[] paramGiven = description.split(DASH, 2);
            paramIsSetCheck(paramGiven);
            Integer watchlistIndex = parameterParser(paramGiven[1]);
            return new ViewWatchlistCommand(watchlistIndex);
        }
  
        LOGGER.log(Level.INFO, PARAMETER_PARSED);

        return new ViewWatchlistCommand();
    }

    /**
     * Parses the parameter provided in the command description.
     *
     * @param paramGiven a String Array containing the parameters and the value
     * @throws AniException when an error occurred while parsing the parameters
     */
    private Integer parameterParser(String paramGiven) throws AniException {
        String[] paramParts = paramGiven.split(WHITESPACE, FIELD_SPLIT_LIMIT);

        switch (paramParts[0].trim()) {
        case VIEW_PARAM:
            paramFieldCheck(paramParts);
            String fieldValue = paramParts[1].trim();
            String[] fieldParts = fieldValue.split(WHITESPACE);
            
            if (fieldParts.length > 1) {
                throw new AniException(TOO_MUCH_ARGUMENTS);
            }
            isIntegerCheck(fieldValue, WATCHLIST_ID);
            return parseStringToInteger(fieldValue);
        default:
            String invalidParameter = PARAMETER_ERROR_HEADER + paramGiven + NOT_RECOGNISED;
            throw new AniException(invalidParameter);
        }
    }
}
