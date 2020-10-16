package seedu.duke.command;

import seedu.duke.anime.AnimeData;
import seedu.duke.exception.AniException;
import seedu.duke.human.User;


public class AddWorkspaceCommand extends Command {
    public static final String EXCEPTION_INVALID_PARAMETERS = "Invalid parameters detected!";
    public static final String REGEX_CHARACTER_HYPHEN = "-";
    public static final String REGEX_CHARACTER_SPACE = " ";
    public static final String PARAMETER_NAME = "n";

    String name;

    public AddWorkspaceCommand(String userInput) throws AniException {
        try {
            String[] parametersSplit = userInput.split(REGEX_CHARACTER_HYPHEN);

            for (String s : parametersSplit) {
                String[] parameterTextSplit = s.split(REGEX_CHARACTER_SPACE, 2);

                if (parameterTextSplit.length == 2 && !parameterTextSplit[0].isEmpty()) {
                    if (PARAMETER_NAME.equals(parameterTextSplit[0])) {
                        name = parameterTextSplit[1];
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new AniException(EXCEPTION_INVALID_PARAMETERS);
        }
    }

    @Override
    public String execute(AnimeData animeData, User user) throws AniException {
        return "Successfully added new workspace: " + user.addWorkspace(name.trim());
    }
}
