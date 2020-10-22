package anichan.command;

import anichan.anime.AnimeData;
import anichan.exception.AniException;
import anichan.human.User;
import anichan.human.Workspace;
import anichan.logger.AniLogger;
import anichan.storage.StorageManager;
import anichan.watchlist.Watchlist;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WorkspaceCommand extends Command {
    private final String option;
    private final String optionInformation;
    private static final String CREATE_OPTION = "n";
    private static final String SWITCH_OPTION = "s";
    private static final String LIST_OPTION = "l";
    private static final String DELETE_OPTION = "d";
    private static final Logger LOGGER = AniLogger.getAniLogger(WatchlistCommand.class.getName());

    public WorkspaceCommand(String option, String optionInformation) {
        this.option = option;
        this.optionInformation = optionInformation;
    }

    @Override
    public String execute(AnimeData animeData, StorageManager storageManager, User user) throws AniException {
        assert (option != null) : "Option should not be null.";

        switch (option) {
        case CREATE_OPTION:
            return createWorkspace(user, storageManager);
        case SWITCH_OPTION:
            return switchWorkspace(user);
        case LIST_OPTION:
            return listWorkspace(user);
        case DELETE_OPTION:
            return deleteWorkspace(user, storageManager);
        default:
            LOGGER.log(Level.WARNING, "Invalid watchlist command provided.");
            throw new AniException("Watchlist command only accepts the options: -n, -s, -l, and -d.");
        }
    }

    public String createWorkspace(User user, StorageManager storageManager) throws AniException {
        Workspace newWorkspace = user.addWorkspace(optionInformation);

        ArrayList<Watchlist> watchlistList = new ArrayList<>();
        watchlistList.add(new Watchlist("Default"));
        newWorkspace.setWatchlistList(watchlistList);
        storageManager.saveWorkspace(newWorkspace);

        LOGGER.log(Level.INFO, "Successfully added new workspace: " + newWorkspace);
        return "Successfully added new workspace: " + newWorkspace;
    }

    public String switchWorkspace(User user) throws AniException {
        String trimmedName = optionInformation;
        user.switchActiveWorkspace(trimmedName);

        LOGGER.log(Level.INFO, "Successfully added new workspace: " + trimmedName);
        return "Workspace changed to " + trimmedName;
    }

    public String deleteWorkspace(User user, StorageManager storageManager) throws AniException {
        if (user.getActiveWorkspace().toString().equals(optionInformation)) {
            throw new AniException("Please switch workspace before trying to delete it.");
        }

        user.deleteWorkspace(optionInformation);
        storageManager.deleteWorkspace(optionInformation);

        LOGGER.log(Level.INFO, "Successfully deleted workspace: " + optionInformation);
        return "Successfully deleted workspace: " + optionInformation;
    }

    public String listWorkspace(User user) {
        StringBuilder workspacesString = new StringBuilder();
        ArrayList<Workspace> userWorkspaces = user.getWorkspaceList();

        workspacesString.append("Currently, you have " + userWorkspaces.size() + " workspace(s):");

        for (int i = 0; i < userWorkspaces.size(); i++) {
            int workspaceIndex = i + 1;
            workspacesString.append(System.lineSeparator() + workspaceIndex + ". " + userWorkspaces.get(i).toString());
        }

        return workspacesString.toString();
    }
}
