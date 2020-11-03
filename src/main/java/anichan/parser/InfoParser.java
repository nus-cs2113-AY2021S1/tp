package anichan.parser;

import anichan.exception.AniException;
import anichan.logger.AniLogger;
import anichan.commands.InfoCommand;

import java.util.logging.Level;
import java.util.logging.Logger;

//@@author michaeldinata
/**
 * Handles parsing for info command.
 */
public class InfoParser extends CommandParser {
    protected static final String TOO_MUCH_ARGUMENTS = "Info command" + TOO_MUCH_FIELDS;
    protected static final String ANIME_ID = "Anime ID!";
    protected static final String OUT_OF_BOUND_INDEX_ERROR = "Anime ID is invalid!";
    private static final Logger LOGGER = AniLogger.getAniLogger(InfoParser.class.getName());
    
    private InfoCommand infoCommand;

    /**
     * Creates a new instance of InfoParser.
     */
    public InfoParser() {
        infoCommand = new InfoCommand();
    }

    /**
     * Parses the specified command description.
     *
     * @param description the specified command description
     * @return initialised {@code InfoCommand} object
     * @throws AniException when an error occurred while parsing the command description
     */
    public InfoCommand parse(String description) throws AniException {
        description = description.trim();

        if (description == null || description.isBlank()) {
            throw new AniException(DESCRIPTION_CANNOT_BE_NULL);
        }

        parameterParser(description);
        LOGGER.log(Level.INFO, "Parameter parsed properly");
        
        return infoCommand;
    }

    /**
     * Parses the parameter provided in the command description.
     *
     * @param fieldGiven a String Array containing the value given
     * @throws AniException when an error occurred while parsing the parameters
     */
    private void parameterParser(String fieldGiven) throws AniException {
        String fieldValue = fieldGiven.trim();
        String[] fieldParts = fieldValue.split(WHITESPACE);

        if (fieldParts.length > 1) {
            throw new AniException(TOO_MUCH_ARGUMENTS);
        }
        isIntegerCheck(fieldValue, ANIME_ID);

        try {
            infoCommand.setAnimeIndex(Integer.parseInt(fieldValue));
        } catch (NumberFormatException e) {
            throw new AniException(OUT_OF_BOUND_INDEX_ERROR);
        }
    }
}
