package anichan.parser;

import anichan.commands.WorkspaceCommand;
import anichan.exception.AniException;
import anichan.logger.AniLogger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class WorkspaceParser extends CommandParser {
    private static final Logger LOGGER = AniLogger.getAniLogger(BookmarkParser.class.getName());
    public static final String EXCEPTION_INVALID_PARAMETERS = "Invalid parameters detected!";
    public static final String COMMAND_NEW = "n";
    public static final String COMMAND_SWITCH = "s";
    public static final String COMMAND_list = "l";
    public static final String COMMAND_LIST = COMMAND_list;
    public static final String COMMAND_DELETE = "d";
    public static final String REGEX_SPACE_CHARACTER = " ";

    private String commandOption;
    private String commandDescription;

    public WorkspaceCommand parse(String description) throws AniException {
        assert description != null : "Description should not be null.";

        String[] paramGiven = parameterSplitter(description);
        paramIsSetCheck(paramGiven);
        paramFieldCheck(paramGiven);

        parameterParser(paramGiven);

        LOGGER.log(Level.INFO, "Returning WorkspaceCommand object with option: "
                + commandOption + ", and description: " + commandDescription);

        return new WorkspaceCommand(commandOption, commandDescription);
    }

    public void parameterParser(String[] paramGiven) throws AniException {
        if (paramGiven.length != 2) {
            LOGGER.log(Level.WARNING, "Invalid number of parameters given");
            throw new AniException(EXCEPTION_INVALID_PARAMETERS);
        }

        try {
            String cleanedCommand = paramGiven[1].trim();

            String[] givenOption = cleanedCommand.split(REGEX_SPACE_CHARACTER, 2);

            switch (givenOption[0]) {
            case COMMAND_NEW:
                commandOption = COMMAND_NEW;
                break;
            case COMMAND_SWITCH:
                commandOption = COMMAND_SWITCH;
                break;
            case COMMAND_LIST:
                commandOption = COMMAND_LIST;
                break;
            case COMMAND_DELETE:
                commandOption = COMMAND_DELETE;
                break;
            default:
                LOGGER.log(Level.WARNING, EXCEPTION_INVALID_PARAMETERS);
                throw new AniException(EXCEPTION_INVALID_PARAMETERS);
            }

            if (!commandOption.equals(COMMAND_LIST)) {
                commandDescription = givenOption[1].trim();
            }
        } catch (IndexOutOfBoundsException exception) {
            LOGGER.log(Level.WARNING, EXCEPTION_INVALID_PARAMETERS);
            throw new AniException(EXCEPTION_INVALID_PARAMETERS);
        }
    }
}
