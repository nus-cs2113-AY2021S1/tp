package seedu.duke.command;

import seedu.duke.anime.AnimeData;
import seedu.duke.exception.AniException;
import seedu.duke.human.User;
import seedu.duke.human.UserManagement;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SwitchUserCommand extends Command {
    protected static final String PARAMETER_ERROR_HEADER = "Parameter : -";
    protected static final String REQUIRE_ADDITIONAL_FIELD = " requires an additional field";
    protected static final String NOT_RECOGNISED = " is not recognised!";

    private static final Logger LOGGER = Logger.getLogger(SwitchUserCommand.class.getName());
    protected static final String NO_PARAMETER_PROVIDED = "No Parameter provided";
    protected static final String SWITCH_SUCCESS_HEADER = "Welcome back, ";

    public SwitchUserCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(AnimeData animeData, UserManagement userManagement) throws AniException {
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
            case "n": //Name of User
                paramLengthCheck(paramParts);
                //Find the user and setActiveUser to it
                User chgUser = userManagement.getUser(paramParts[1]);
                userManagement.setActiveUser(chgUser);
                result = SWITCH_SUCCESS_HEADER + chgUser.getHonorificName();
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
