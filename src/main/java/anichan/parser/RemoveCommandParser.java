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
    protected static final String TOO_MUCH_ARGUMENTS = "Remove command " + TOO_MUCH_FIELDS;
    protected static final String NON_INTEGER_PROVIDED = "Please specify an Int value for Anime ID!";
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
        String[] paramGiven = parameterSplitter(description);

        paramIsSetCheck(paramGiven);
        if (paramGiven.length > 2) {
            throw new AniException(TOO_MUCH_ARGUMENTS);
        } else {
            parameterParser(paramGiven);
            LOGGER.log(Level.INFO, "Parameter parsed properly");
        }
        return removeCommand;
    }

    /**
     * Parses the parameter provided in the command description.
     *
     * @param paramGiven a String Array containing the parameters and the value
     * @throws AniException when an error occurred while parsing the parameters
     */
    private void parameterParser(String[] paramGiven) throws AniException {
        for (String param : paramGiven) {
            String[] paramParts = param.split(" ");
            if (paramParts.length == 0) {
                break;
            }

            switch (paramParts[0].trim()) {
            case "": // skip empty param
                break;
            case REMOVE_PARAM:
                paramFieldCheck(paramParts);
                paramExtraFieldCheck(paramParts);
                if (!isInt(paramParts[1].trim())) {
                    throw new AniException(NON_INTEGER_PROVIDED);
                }
                removeCommand.setWatchlistListIndex(Integer.parseInt(paramParts[1].trim()));
                break;
            default:
                String invalidParameter = PARAMETER_ERROR_HEADER + param + NOT_RECOGNISED;
                throw new AniException(invalidParameter);
            }
        }
    }
}
