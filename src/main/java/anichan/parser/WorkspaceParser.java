package anichan.parser;

import anichan.command.WorkspaceCommand;
import anichan.exception.AniException;
import anichan.logger.AniLogger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class WorkspaceParser extends CommandParser {
    private static final Logger LOGGER = AniLogger.getAniLogger(BookmarkParser.class.getName());
    public static final String EXCEPTION_INVALID_PARAMETERS = "Invalid parameters detected!";
    private String commandOption;
    private String commandDescription;

    public WorkspaceCommand parse(String description) throws AniException {
        assert description != null : "description should not be null.";

        String[] paramGiven = parameterSplitter(description);
        paramIsSetCheck(paramGiven);
        paramFieldCheck(paramGiven);

        parameterParser(paramGiven);

        LOGGER.log(Level.INFO, "Returning WorkspaceCommand object with option: "
                + commandOption + ", and information: " + commandDescription);

        return new WorkspaceCommand(commandOption, commandDescription);
    }

    public void parameterParser(String[] paramGiven) throws AniException {
        if (paramGiven.length != 2) {
            LOGGER.log(Level.WARNING, "Invalid number of parameters given");
            throw new AniException(EXCEPTION_INVALID_PARAMETERS);
        }

        try {
            String cleanedCommand = paramGiven[1].trim();

            String[] givenOption = cleanedCommand.split(" ", 2);

            switch (givenOption[0]) {
            case "n":
                commandOption = "n";
                break;
            case "s":
                commandOption = "s";
                break;
            case "d":
                commandOption = "d";
                break;
            default:
                LOGGER.log(Level.WARNING, "Invalid parameters provided");
                throw new AniException(EXCEPTION_INVALID_PARAMETERS);
            }

            commandDescription = givenOption[1].trim();
        } catch (IndexOutOfBoundsException exception) {
            LOGGER.log(Level.WARNING, EXCEPTION_INVALID_PARAMETERS);
            throw new AniException(EXCEPTION_INVALID_PARAMETERS);
        }
    }
}
