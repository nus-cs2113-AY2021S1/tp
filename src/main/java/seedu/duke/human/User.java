package seedu.duke.human;

import seedu.duke.exception.AniException;
import seedu.duke.watchlist.Watchlist;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User extends Human {
    public static final String GENDER_MALE = "male";
    public static final String GENDER_FEMALE = "female";
    public static final String GENDER_OTHER = "other";
    private static final Logger LOGGER = Logger.getLogger(User.class.getName());

    protected Gender gender;
    protected Workspace activeWorkspace;
    protected ArrayList<Workspace> workspaceList = new ArrayList<>();

    public User(String name, String gender) throws AniException {
        super(name);
        LOGGER.setLevel(Level.WARNING);

        setGender(gender);
        activeWorkspace = null;
    }

    public void setGender(String genderString) throws AniException {
        genderString = genderString.toLowerCase();

        switch (genderString) {
        case GENDER_MALE:
            gender = Gender.Male;
            break;
        case GENDER_FEMALE:
            gender = Gender.Female;
            break;
        case GENDER_OTHER:
            gender = Gender.Other;
            break;
        default:
            throw new AniException("Unexpected gender: " + genderString);
        }
    }

    public Gender getGender() {
        return gender;
    }

    /**
     * Provides the name of the user with Japanese honorifics depending on his gender.
     *
     * @return name of user with honorifics.
     */
    public String getHonorificName() {
        if (gender == Gender.Female) {
            return name + "-chan";
        } else {
            return name + "-san";
        }
    }

    public void setWorkspaceList(ArrayList<Workspace> workspaceList) {
        this.workspaceList = workspaceList;
        if (workspaceList.size() != 0) {
            activeWorkspace = workspaceList.get(0);

        }
    }

    public Workspace getActiveWorkspace() {
        return activeWorkspace;
    }

    public void setActiveWorkspace(Workspace inputWorkspace) {
        activeWorkspace = inputWorkspace;

        if (activeWorkspace.getWatchlistList().size() == 0) {
            //Loading of changed active user should be done here. For now set to empty
            ArrayList<Watchlist> watchlistLists = new ArrayList<>();
            Watchlist watchlist = new Watchlist("Default");
            watchlistLists.add(watchlist);
            inputWorkspace.setActiveWatchlist(watchlist);
            inputWorkspace.setWatchlistList(watchlistLists);
            LOGGER.log(Level.INFO, "Workspace switched: " + inputWorkspace.getName());
        }
    }

    public int getTotalWorkspaces() {
        return workspaceList.size();
    }

    public Workspace addWorkspace(String name) {
        Workspace newWorkspace = new Workspace(name);
        assert (name != null) : "Workspace details should not have any null.";

        workspaceList.add(newWorkspace);
        LOGGER.log(Level.INFO, "Workspace created: " + name + " | " + gender);
        return newWorkspace;
    }

    // Suggest to check internally in the workspace command.
    private void checkIfWorkspaceExist(String name) throws AniException {
        for (Workspace existingWorkspace : workspaceList) {
            if (existingWorkspace.getName().equals(name)) {
                throw new AniException("A workspace with " + name + " already exist. Choose a different name!");
            }
        }
    }

    // NOTE: return null and check.
    public Workspace getWorkspace(String name) throws AniException {
        for (Workspace existingWorkspace : workspaceList) {
            if (existingWorkspace.getName().equals(name)) {
                return existingWorkspace;
            }
        }
        throw new AniException("Workspace " + name + " does not exist!");
    }


    @Override
    public String toString() {
        return "\n Name: " + getHonorificName() + "\n Gender:" + getGender();
    }
}
