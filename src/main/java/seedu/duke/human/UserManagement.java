package seedu.duke.human;

import seedu.duke.exception.AniException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;
import seedu.duke.watchlist.Watchlist;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserManagement {
    Storage storage;
    private static final Logger LOGGER = Logger.getLogger(UserManagement.class.getName());
    protected ArrayList<Workspace> workspaceList = new ArrayList<>();
    protected Workspace activeWorkspace;

    public UserManagement(Storage storage) {
        LOGGER.setLevel(Level.WARNING);
        this.storage = storage;
        activeWorkspace = null;
    }

    public Workspace getActiveUser() {
        return activeWorkspace;
    }


    public void setActiveUser(Workspace inputWorkspace) {
        activeWorkspace = inputWorkspace;

        if (activeWorkspace != null) {
            //Loading of changed active user should be done here. For now set to empty
            ArrayList<Watchlist> watchlistLists = new ArrayList<>();
            Watchlist watchlist = new Watchlist("Default");
            watchlistLists.add(watchlist);
            inputWorkspace.setActiveWatchlist(watchlist);
            inputWorkspace.setWatchlistList(watchlistLists);
            LOGGER.log(Level.INFO, "Workspace switched: " + inputWorkspace.getName());
        }
    }

    public int getTotalUsers() {
        return workspaceList.size();
    }

    public Storage getStorage() {
        return storage;
    }

    public Workspace addUser(String name, String gender) throws ParseException, AniException {
        Workspace newWorkspace = new Workspace(name, gender);
        checkIfUserExist(name);

        assert (name != null && gender != null) : "Workspace details should not have any null.";

        workspaceList.add(newWorkspace);
        storage.saveUser(newWorkspace);

        LOGGER.log(Level.INFO, "Workspace created: " + name + " | " + gender);
        return newWorkspace;
    }

    private void checkIfUserExist(String name) throws AniException {
        for (Workspace existingWorkspace : workspaceList) {
            if (existingWorkspace.getName().equals(name)) {
                throw new AniException("A user with " + name + " already exist. Choose a different name!");
            }
        }
    }

    public Workspace getUser(String name) throws AniException {
        for (Workspace existingWorkspace : workspaceList) {
            if (existingWorkspace.getName().equals(name)) {
                return existingWorkspace;
            }
        }

        throw new AniException("No such user!");
    }

    public void addUserDialogue(Ui ui) {
        boolean userCreated = false;
        LOGGER.log(Level.INFO, "No existing user found, prompting user to create one!");

        while (!userCreated) {
            try {
                ui.printMessage("What's your name?");
                String name = ui.readInput();
                ui.printMessage("Hello " + name + "! What might your gender be? (Male/Female/Other)");
                String gender = ui.readInput();

                activeWorkspace = addUser(name.trim(), gender);
                ui.printMessage("Successfully added new user:");
                ui.printMessage(activeWorkspace.toString());
                userCreated = true;
            } catch (ParseException | AniException exception) {
                ui.printErrorMessage(exception.getMessage());
            }
        }
    }
}
