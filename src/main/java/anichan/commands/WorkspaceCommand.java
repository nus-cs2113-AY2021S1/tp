package anichan.commands;

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
    private static final String CREATE_OPTION = "n";
    private static final String SWITCH_OPTION = "s";
    private static final String LIST_OPTION = "l";
    private static final String DELETE_OPTION = "d";
    private static final Logger LOGGER = AniLogger.getAniLogger(WatchlistCommand.class.getName());

    private final String commandOption;
    private final String commandDescription;

    public WorkspaceCommand(String commandOption, String commandDescription) {
        this.commandOption = commandOption;
        this.commandDescription = commandDescription;
    }

    @Override
    public String execute(AnimeData animeData, StorageManager storageManager, User user) throws AniException {
        assert (commandOption != null) : "Option should not be null.";

        switch (commandOption) {
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
        Workspace newWorkspace = user.addWorkspace(commandDescription);

        ArrayList<Watchlist> watchlistList = new ArrayList<>();
        watchlistList.add(new Watchlist("Default"));
        newWorkspace.setWatchlistList(watchlistList);
        storageManager.saveWorkspace(newWorkspace);

        LOGGER.log(Level.INFO, "Successfully added new workspace: " + newWorkspace);
        return "Successfully added new workspace: " + newWorkspace;
    }

    public String switchWorkspace(User user) throws AniException {
        String trimmedName = commandDescription;
        user.switchActiveWorkspace(trimmedName);

        LOGGER.log(Level.INFO, "Successfully switched to workspace: " + trimmedName);
        return "Workspace switched to " + trimmedName;
    }

    public String deleteWorkspace(User user, StorageManager storageManager) throws AniException {
        if (user.getActiveWorkspace().toString().equals(commandDescription)) {
            throw new AniException("Please switch workspace before trying to delete it.");
        }

        user.deleteWorkspace(commandDescription);
        storageManager.deleteWorkspace(commandDescription);

        LOGGER.log(Level.INFO, "Successfully deleted workspace: " + commandDescription);
        return "Successfully deleted workspace: " + commandDescription;
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
