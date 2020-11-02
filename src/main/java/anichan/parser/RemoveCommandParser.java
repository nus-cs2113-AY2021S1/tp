package anichan.parser;

import anichan.commands.RemoveCommand;
import anichan.exception.AniException;
import anichan.logger.AniLogger;

import java.util.logging.Level;
import java.util.logging.Logger;

//@@author michaeldinata
/**
 * Handles parsing for remove command.
 */
public class RemoveCommandParser extends CommandParser {
    protected static final String REMOVE_PARAM = "d";
    protected static final String TOO_MUCH_ARGUMENTS = "Remove command" + TOO_MUCH_FIELDS;
    protected static final String ANIME_ID_IN_WATCHLIST = "Anime ID in Watchlist!";
    protected static final String OUT_OF_BOUND_INDEX_ERROR = "Anime ID in watchlist is invalid!";
    private static final Logger LOGGER = AniLogger.getAniLogger(RemoveCommandParser.class.getName());

    private RemoveCommand removeCommand;

    /**
     * Creates a new instance of RemoveCommandParser.
     */
    public RemoveCommandParser() {
        removeCommand = new RemoveCommand();
    }

    /**
     * Parses the specified command description.
     *
     * @param description the specified command description
     * @return initialised {@code RemoveCommand} object
     * @throws AniException when an error occurred while parsing the command description
     */
    public RemoveCommand parse(String description) throws AniException {
        String[] paramGiven = description.split(SPLIT_DASH, 2);

        paramIsSetCheck(paramGiven);
        if (paramGiven[1] == null || paramGiven[1].trim().isBlank()) {
            throw new AniException(NO_PARAMETER_PROVIDED);
        }
        
        parameterParser(paramGiven[1]);
        LOGGER.log(Level.INFO, "Parameter parsed properly");
        
        return removeCommand;
    }

    /**
     * Parses the parameter provided in the command description.
     *
     * @param paramGiven a String Array containing the parameters and the value
     * @throws AniException when an error occurred while parsing the parameters
     */
    private void parameterParser(String paramGiven) throws AniException {
        String[] paramParts = paramGiven.split(" ", 2);

        switch (paramParts[0].trim()) {
        case REMOVE_PARAM:
            paramFieldCheck(paramParts);
            paramExtraFieldCheck(paramParts);

            String fieldValue = paramParts[1].trim();
            String[] fieldParts = fieldValue.split(SPLIT_WHITESPACE);

            if (fieldParts.length > 1) {
                throw new AniException(TOO_MUCH_ARGUMENTS);
            }
            isIntegerCheck(fieldValue, ANIME_ID_IN_WATCHLIST);
            
            try {
                removeCommand.setWatchlistListIndex(Integer.parseInt(fieldValue));
            } catch (NumberFormatException e) {
                throw new AniException(OUT_OF_BOUND_INDEX_ERROR);
            }
            break;
        default:
            String invalidParameter = PARAMETER_ERROR_HEADER + paramGiven + NOT_RECOGNISED;
            throw new AniException(invalidParameter);
        }
    }
}
