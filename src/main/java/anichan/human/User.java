package anichan.human;

import anichan.Main;
import anichan.exception.AniException;
import static anichan.logger.AniLogger.getAniLogger;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class User extends Human {

    public static final String GENDER_MALE = "male";
    public static final String GENDER_FEMALE = "female";
    public static final String GENDER_OTHER = "other";
    private static final Logger LOGGER = getAniLogger(Main.class.getName());
    public static final String EXCEPTION_WORKPLACE_NOT_FOUND = "Workspace does not exist!";
    public static final String HONORIFIC_FEMALE = "-chan";
    public static final String HONORIFIC_NEUTRAL = "-san";
    public static final String ASSERTION_INVALID_MESSAGE = "Input invalid.";

    protected Gender gender;
    protected Workspace activeWorkspace;
    protected ArrayList<Workspace> workspaceList = new ArrayList<>();

    // ========================== User related methods ==========================

    public User(String name, String gender) throws AniException {
        super(name);

        setGender(gender);
        activeWorkspace = null;
    }

    public void setGender(String genderString) throws AniException {
        assert (genderString != null) : ASSERTION_INVALID_MESSAGE;
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
            return name + HONORIFIC_FEMALE;
        } else {
            return name + HONORIFIC_NEUTRAL;
        }
    }

    @Override
    public String toString() {
        return " Name: " + getHonorificName() + " | Gender: " + getGender();
    }

    // ========================== User's workspace related methods ==========================

    public void setWorkspaceList(ArrayList<Workspace> workspaceList) {
        this.workspaceList = workspaceList;
        if (workspaceList.size() != 0) {
            activeWorkspace = workspaceList.get(0);
        }
    }

    public ArrayList<Workspace> getWorkspaceList() {
        return workspaceList;
    }

    public void setActiveWorkspace(Workspace inputWorkspace) throws AniException {
        assert (inputWorkspace != null) : ASSERTION_INVALID_MESSAGE;
        activeWorkspace = inputWorkspace;

        try {
            //Set the first watchlist to be the active watchlist
            inputWorkspace.setActiveWatchlist(inputWorkspace.getWatchlistList().get(0));
            LOGGER.log(Level.INFO, "Workspace switched: " + inputWorkspace.getName());
        } catch (Exception e) {
            throw new AniException(EXCEPTION_WORKPLACE_NOT_FOUND);
        }
    }

    public Workspace getActiveWorkspace() {
        return activeWorkspace;
    }

    /**
     * Finds the workplace that matches the string parameter to switch to.
     *
     * @param switchToThisWorkspace the requested workplace to switch to
     * @throws AniException if the workplace is not found
     */
    public void switchActiveWorkspace(String switchToThisWorkspace) throws AniException {
        assert (switchToThisWorkspace != null) : ASSERTION_INVALID_MESSAGE;

        for (Workspace existingWorkspace : workspaceList) {
            if (existingWorkspace.getName().equals(switchToThisWorkspace)) {
                setActiveWorkspace(existingWorkspace);
                return;
            }
        }

        LOGGER.log(Level.WARNING, "Workspace " + switchToThisWorkspace + " does not exist!");
        throw new AniException("Workspace " + switchToThisWorkspace + " does not exist!");
    }


    public int getTotalWorkspaces() {
        return workspaceList.size();
    }

    public Workspace addWorkspace(String name) throws AniException {
        assert (name != null) : ASSERTION_INVALID_MESSAGE;

        if (doesWorkplaceExist(name)) {
            throw new AniException("Workspace already exist!");
        } else {
            Workspace newWorkspace = new Workspace(name);

            workspaceList.add(newWorkspace);
            LOGGER.log(Level.INFO, "Workspace created: " + name);
            return newWorkspace;

        }
    }

    public void deleteWorkspace(String toDeleteWorkspace) throws AniException {
        assert (toDeleteWorkspace != null) : "Workspace details should not have any null.";

        Workspace targetWorkspace = findWorkspace(toDeleteWorkspace);

        if (targetWorkspace != null) {
            workspaceList.remove(targetWorkspace);
        } else {
            LOGGER.log(Level.WARNING, EXCEPTION_WORKPLACE_NOT_FOUND);
            throw new AniException(EXCEPTION_WORKPLACE_NOT_FOUND);
        }
    }

    public Workspace findWorkspace(String findString) {
        assert (findString != null) : ASSERTION_INVALID_MESSAGE;

        for (Workspace tempWorkspace : workspaceList) {
            if (tempWorkspace.getName().equals(findString)) {
                return tempWorkspace;
            }
        }

        return null;
    }

    public boolean doesWorkplaceExist(String checkWorkspace) {
        assert (checkWorkspace != null) : ASSERTION_INVALID_MESSAGE;

        for (Workspace existingWorkspace : workspaceList) {
            if (existingWorkspace.getName().equals(checkWorkspace)) {
                return true;
            }
        }

        return false;
    }

}
