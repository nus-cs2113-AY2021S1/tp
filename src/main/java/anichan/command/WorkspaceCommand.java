package anichan.command;

import anichan.anime.AnimeData;
import anichan.exception.AniException;
import anichan.human.User;
import anichan.human.Workspace;
import anichan.storage.StorageManager;
import anichan.watchlist.Watchlist;

import java.util.ArrayList;

public class WorkspaceCommand extends Command {
    private final String option;
    private final String optionInformation;
    private static final String CREATE_OPTION = "n";
    private static final String SWITCH_OPTION = "s";
    private static final String DELETE_OPTION = "d";

    public WorkspaceCommand(String option, String optionInformation) {
        this.option = option;
        this.optionInformation = optionInformation;
    }

    @Override
    public String execute(AnimeData animeData, StorageManager storageManager, User user) throws AniException {
        assert (option != null && optionInformation != null) : "option should not be null.";

        switch (option) {
        case CREATE_OPTION:
            return createWorkspace(user, storageManager);
        case SWITCH_OPTION:
            return switchWorkspace(user);
        case DELETE_OPTION:
            return deleteWorkspace(user, storageManager);
        default:
            throw new AniException("Watchlist command only accepts the options: -n, -l, -s, and -d.");
        }
    }

    public String createWorkspace(User user, StorageManager storageManager) throws AniException {
        Workspace newWorkspace = user.addWorkspace(optionInformation.trim());

        ArrayList<Watchlist> watchlistList = new ArrayList<>();
        watchlistList.add(new Watchlist("Default"));
        newWorkspace.setWatchlistList(watchlistList);
        storageManager.saveWorkspace(newWorkspace);

        return "Successfully added new workspace: " + newWorkspace;
    }

    public String switchWorkspace(User user) throws AniException {
        user.switchActiveWorkspace(optionInformation.trim());
        return "Workspace changed to " + optionInformation.trim();
    }

    public String deleteWorkspace(User user, StorageManager storageManager) {
        return "WIP";
    }
}
