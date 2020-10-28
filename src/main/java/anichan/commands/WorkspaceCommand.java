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
    public static final String REGEX_ALPHANUMERIC = "^[a-zA-Z0-9]*$";

    private final String commandOption;
    private final String workspaceName;

    public WorkspaceCommand(String commandOption, String workspaceName) {
        this.commandOption = commandOption;
        this.workspaceName = workspaceName;
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
            LOGGER.log(Level.WARNING, "Invalid workspace command provided.");
            throw new AniException("Workspace command only accepts the options: -n, -s, -l, and -d.");
        }
    }

    public String createWorkspace(User user, StorageManager storageManager) throws AniException {
        checkName(workspaceName);
        Workspace newWorkspace = user.addWorkspace(workspaceName);

        ArrayList<Watchlist> watchlistList = new ArrayList<>();
        watchlistList.add(new Watchlist("Default"));
        newWorkspace.setWatchlistList(watchlistList);
        storageManager.saveWorkspace(newWorkspace);

        LOGGER.log(Level.INFO, "Successfully added new workspace: " + newWorkspace);
        return "Successfully added new workspace: " + newWorkspace;
    }

    public String switchWorkspace(User user) throws AniException {
        checkName(workspaceName);
        String trimmedName = workspaceName;
        user.switchActiveWorkspace(trimmedName);

        LOGGER.log(Level.INFO, "Successfully switched to workspace: " + trimmedName);
        return "Workspace switched to " + trimmedName;
    }

    public String deleteWorkspace(User user, StorageManager storageManager) throws AniException {
        checkName(workspaceName);

        if (user.getActiveWorkspace().toString().equals(workspaceName)) {
            throw new AniException("Please switch workspace before trying to delete it.");
        }

        user.deleteWorkspace(workspaceName);
        storageManager.deleteWorkspace(workspaceName);

        LOGGER.log(Level.INFO, "Successfully deleted workspace: " + workspaceName);
        return "Successfully deleted workspace: " + workspaceName;
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

    public void checkName(String workspaceName) throws AniException {
        if (workspaceName != null) {
            boolean isValid = workspaceName.matches(REGEX_ALPHANUMERIC);

            if (!isValid) {
                LOGGER.log(Level.WARNING, "Workspace name provided does not meet standards.");
                throw new AniException("Invalid parameters detected!");
            }
        }
    }
}
