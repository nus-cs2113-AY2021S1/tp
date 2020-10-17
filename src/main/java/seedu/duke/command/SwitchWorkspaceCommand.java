package seedu.duke.command;

import seedu.duke.anime.AnimeData;
import seedu.duke.exception.AniException;
import seedu.duke.human.Workspace;
import seedu.duke.human.User;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SwitchWorkspaceCommand extends Command {
    protected static final String PARAMETER_ERROR_HEADER = "Parameter : -";
    protected static final String REQUIRE_ADDITIONAL_FIELD = " requires an additional field";
    protected static final String NOT_RECOGNISED = " is not recognised!";

    private static final Logger LOGGER = Logger.getLogger(SwitchWorkspaceCommand.class.getName());
    protected static final String NO_PARAMETER_PROVIDED = "No Parameter provided";
    protected static final String SWITCH_SUCCESS_HEADER = "Welcome back, ";

    public SwitchWorkspaceCommand(String description) {
        LOGGER.setLevel(Level.WARNING);
        this.description = description;
    }

    @Override
    public String execute(AnimeData animeData, User user) throws AniException {
        String[] paramGiven = description.split("-");
        String result = "";
        if (description.length() < 2) {
            LOGGER.log(Level.WARNING, NO_PARAMETER_PROVIDED);
            throw new AniException(NO_PARAMETER_PROVIDED);
        }
        for (String param : paramGiven) {
            String[] paramParts = param.split(" ", 2);
            switch (paramParts[0].trim()) {
            case "": //skip the first empty param
                break;
            case "n": //Name of Workspace
                paramLengthCheck(paramParts);
                //Find the user and setActiveUser to it
                Workspace chgWorkspace = user.getWorkspace(paramParts[1]);
                user.setActiveWorkspace(chgWorkspace);
                result = SWITCH_SUCCESS_HEADER + user.getHonorificName();
                break;
            default:
                String invalidParameter = PARAMETER_ERROR_HEADER + param + NOT_RECOGNISED;
                throw new AniException(invalidParameter);
            }
        }
        return result;
    }

    private void paramLengthCheck(String[] paramParts) throws AniException {
        if (paramParts.length < 2) {
            String invalidParameter = PARAMETER_ERROR_HEADER + paramParts[0] + REQUIRE_ADDITIONAL_FIELD;
            throw new AniException(invalidParameter);
        }
    }
}
