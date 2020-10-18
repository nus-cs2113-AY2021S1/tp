package seedu.duke.command;

import seedu.duke.anime.AnimeData;
import seedu.duke.exception.AniException;
import seedu.duke.human.User;
import seedu.duke.human.Workspace;
import seedu.duke.storage.StorageManager;
import seedu.duke.watchlist.Watchlist;

import java.util.ArrayList;

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
    public String execute(AnimeData animeData, StorageManager storageManager, User user) throws AniException {
        Workspace newWorkspace = user.addWorkspace(name.trim());
        ArrayList<Watchlist> watchlistList = new ArrayList<>();
        watchlistList.add(new Watchlist("Default"));
        newWorkspace.setWatchlistList(watchlistList);
        storageManager.saveWorkspace(newWorkspace);
        return "Successfully added new workspace:" + newWorkspace;
    }
}
