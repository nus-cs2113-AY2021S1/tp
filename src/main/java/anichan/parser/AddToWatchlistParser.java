package anichan.parser;

import anichan.commands.AddToWatchlistCommand;
import anichan.exception.AniException;
import anichan.logger.AniLogger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AddToWatchlistParser extends CommandParser {
    protected static final String ADD_PARAM = "a";
    protected static final String NON_INTEGER_PROVIDED = "Please specify an Int value for Anime ID!";
    protected static final String TOO_MUCH_ARGUMENTS = "Add To Watchlist command " + TOO_MUCH_FIELDS;
    private static final Logger LOGGER = AniLogger.getAniLogger(AddToWatchlistParser.class.getName());
    
    private AddToWatchlistCommand addToWatchlistCommand;
    
    public AddToWatchlistParser() {
        addToWatchlistCommand = new AddToWatchlistCommand();
        // LOGGER.setLevel(Level.WARNING);
    }
    
    public AddToWatchlistCommand parse(String description) throws AniException {
        String[] paramGiven = parameterSplitter(description);

        paramIsSetCheck(paramGiven);
        if (paramGiven.length > 2) {
            throw new AniException(TOO_MUCH_ARGUMENTS);
        } else {
            parameterParser(paramGiven);
            LOGGER.log(Level.INFO, "Parameter parsed properly");
        }
        return addToWatchlistCommand;
    }

    private void parameterParser(String[] paramGiven) throws AniException {
        for (String param : paramGiven) {
            String[] paramParts = param.split(" ");
            if (paramParts.length == 0) {
                break;
            }
            
            switch (paramParts[0].trim()) {
            case "": // skip empty param
                break;
            case ADD_PARAM:
                paramFieldCheck(paramParts);
                paramExtraFieldCheck(paramParts);
                if (!isInt(paramParts[1].trim())) {
                    throw new AniException(NON_INTEGER_PROVIDED);
                }
                addToWatchlistCommand.setAnimeIndex(Integer.parseInt(paramParts[1].trim()));
                break;
            default:
                String invalidParameter = PARAMETER_ERROR_HEADER + param + NOT_RECOGNISED;
                throw new AniException(invalidParameter);
            }
        }
    }
}
