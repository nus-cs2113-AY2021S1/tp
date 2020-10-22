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

        return new WorkspaceCommand("parsedParts[0]", "parsedParts[1]");
    }

    public void parameterParser(String[] paramGiven) throws AniException {
        if (paramGiven.length != 2) {
            throw new AniException(EXCEPTION_INVALID_PARAMETERS);
        }

        String cleanedCommand = paramGiven[1].trim();

        String[] givenOption = cleanedCommand.split(" ", 2);
        
        switch (givenOption[0]) {
        case "n":
            commandOption = "N";
            break;
        case "s":
            commandOption = "S";
            break;
        case "d":
            commandOption = "D";
            break;
        default:
            throw new AniException("Unexpected value: " + givenOption[0]);
        }

        commandDescription = givenOption[1];
    }
}
