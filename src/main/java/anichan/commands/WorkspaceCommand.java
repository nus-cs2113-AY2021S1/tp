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

/**
 * Represents the command to manage the workspace(s) in AniChan.
 */
public class WorkspaceCommand extends Command {
    private static final String CREATE_OPTION = "n";
    private static final String SWITCH_OPTION = "s";
    private static final String LIST_OPTION = "l";
    private static final String DELETE_OPTION = "d";
    private static final Logger LOGGER = AniLogger.getAniLogger(WatchlistCommand.class.getName());
    public static final String REGEX_ALPHANUMERIC = "^[a-zA-Z0-9\\s]*$";
    public static final String EXPECTED_PARAMETERS_MESSAGE = "Workspace command only accepts the "
            + "options: -n, -s, -l, and -d.";
    public static final String EXCEPTION_WORKSPACE_IN_USE = "Please switch workspace before trying to delete it.";

    private final String commandOption;
    private final String workspaceName;

    /**
     * Creates a new instance of WorkspaceCommand with the specified option and workspace name.
     *
     * @param commandOption command option parameter
     * @param workspaceName workspace name for command to operate on
     */
    public WorkspaceCommand(String commandOption, String workspaceName) {
        this.commandOption = commandOption;
        this.workspaceName = workspaceName;
    }

    /**
     * Depending on the option supplied, it can perform one of the following operations.
     * <ul>
     *     <li>Creates a new workspace</li>
     *     <li>Switches to workspace</li>
     *     <li>Lists all workspace</li>
     *     <li>Delete a workspace</li>
     * </ul>
     *
     * @param animeData      used to retrieve anime information
     * @param storageManager used to save or read AniChan data
     * @param user           used to modify user data
     * @return result after executing the command
     * @throws AniException when an error occurred while executing the command
     */
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
            throw new AniException(EXPECTED_PARAMETERS_MESSAGE);
        }
    }

    /**
     * Creates a workspace.
     *
     * @param user           used to modify user data
     * @param storageManager used to save or read AniChan data
     * @return result after executing the command
     * @throws AniException when an error occurred while executing the command
     */
    private String createWorkspace(User user, StorageManager storageManager) throws AniException {
        checkName(workspaceName);
        Workspace newWorkspace = user.addWorkspace(workspaceName);

        ArrayList<Watchlist> watchlistList = new ArrayList<>();
        watchlistList.add(new Watchlist("Default"));
        newWorkspace.setWatchlistList(watchlistList);
        storageManager.saveWorkspace(newWorkspace);

        LOGGER.log(Level.INFO, "Successfully added new workspace: " + newWorkspace);
        return "Successfully added new workspace: " + newWorkspace;
    }

    /**
     * Switches to specified workspace.
     *
     * @param user used to modify user data
     * @return result after executing the command
     * @throws AniException when an error occurred while executing the command
     */
    private String switchWorkspace(User user) throws AniException {
        checkName(workspaceName);
        String trimmedName = workspaceName;
        user.switchActiveWorkspace(trimmedName);

        LOGGER.log(Level.INFO, "Successfully switched to workspace: " + trimmedName);
        return "Workspace switched to " + trimmedName;
    }

    /**
     * Deletes specified workspace.
     *
     * @param user           used to modify user data
     * @param storageManager used to save or read AniChan data
     * @return result after executing the command
     * @throws AniException when an error occurred while executing the command
     */
    private String deleteWorkspace(User user, StorageManager storageManager) throws AniException {
        checkName(workspaceName);

        if (user.getActiveWorkspace().toString().equals(workspaceName)) {
            throw new AniException(EXCEPTION_WORKSPACE_IN_USE);
        }

        user.deleteWorkspace(workspaceName);
        storageManager.deleteWorkspace(workspaceName);

        LOGGER.log(Level.INFO, "Successfully deleted workspace: " + workspaceName);
        return "Successfully deleted workspace: " + workspaceName;
    }

    /**
     * Lists all existing workspace.
     *
     * @param user used to modify user data
     * @return result after executing the command
     * @throws AniException when an error occurred while executing the command
     */
    private String listWorkspace(User user) {
        StringBuilder workspacesString = new StringBuilder();
        ArrayList<Workspace> userWorkspaces = user.getWorkspaceList();

        workspacesString.append("Currently, you have " + userWorkspaces.size() + " workspace(s):");

        for (int i = 0; i < userWorkspaces.size(); i++) {
            int workspaceIndex = i + 1;
            workspacesString.append(System.lineSeparator() + workspaceIndex + ". " + userWorkspaces.get(i).toString());
        }

        return workspacesString.toString();
    }

    /**
     * Checks if workspace name is valid.
     *
     * @param workspaceName name of workspace
     * @throws AniException when name is not of valid format
     */
    private void checkName(String workspaceName) throws AniException {
        if (workspaceName != null) {
            boolean isValid = workspaceName.matches(REGEX_ALPHANUMERIC);

            if (!isValid) {
                LOGGER.log(Level.WARNING, "Workspace name provided does not meet standards.");
                throw new AniException("Invalid parameters detected!");
            }
        }
    }

}
