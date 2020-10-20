package seedu.duke.parser;

import seedu.duke.command.AddWorkspaceCommand;
import seedu.duke.exception.AniException;



public class AddWorkspaceParser extends CommandParser {
    public static final String REGEX_CHARACTER_HYPHEN = "-";
    public static final String EXCEPTION_INVALID_PARAMETERS = "Invalid parameters detected!";
    public static final String REGEX_CHARACTER_SPACE = " ";
    public static final String PARAMETER_EMPTY = "";
    public static final String PARAMETER_NAME = "n";
    private final AddWorkspaceCommand addWorkspaceCommand;

    public AddWorkspaceParser() {
        addWorkspaceCommand = new AddWorkspaceCommand();
    }

    public AddWorkspaceCommand parse(String description) throws AniException {
        String[] paramGiven = parameterSplitter(description);
        paramIsSetCheck(paramGiven);
        parameterParser(paramGiven);
        return addWorkspaceCommand;
    }

    public void parameterParser(String[] paramGiven) throws AniException {
        for (String param : paramGiven) {
            String[] paramParts = param.split(REGEX_CHARACTER_SPACE, 2);

            if (paramParts.length == 0) {
                throw new AniException(EXCEPTION_INVALID_PARAMETERS);
            }

            if (PARAMETER_NAME.equals(paramParts[0].trim())) {
                paramFieldCheck(paramParts);
                addWorkspaceCommand.setWorkspaceName(paramParts[1]);
            }
        }

        if (addWorkspaceCommand.getNewWorkspaceName() == null) {
            throw new AniException(EXCEPTION_INVALID_PARAMETERS);
        }
    }
}
