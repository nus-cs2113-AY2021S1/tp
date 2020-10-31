package anichan.human;

import anichan.exception.AniException;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTest {

    @Test
    void testUserCreation() throws AniException {
        User user = new User("Jessica Williams", "feMale");

        assertEquals(" Name: Jessica Williams-chan | Gender: Female", user.toString());
    }

    @Test
    void testCreateUser_emptyName_throwsAniException() {
        assertThrows(AniException.class, () -> new User("", "Male"));
    }

    @Test
    void changeName_TommytoJohnny_Johnny() throws AniException {
        User user = new User("Tommy", "Male");
        user.setName("Johnny");

        assertEquals("Johnny", user.getName());
    }

    @Test
    void setGender_Female_returnFemale() throws AniException {
        User user = new User("A changeGender", "MaLE");

        // Notice how case is not sensitive
        user.setGender("FEMAle");

        assertEquals("Female", user.getGender().toString());
    }

    @Test
    void setGender_Monster_throwsAniException() throws AniException {
        User user = new User("Monster", "MaLE");

        assertThrows(AniException.class, () -> user.setGender("Monster"));
    }

    @Test
    void getHonorificName_testGender_nameWithHonorific() throws AniException {
        User maleUser = new User("Robinson", "MaLE");
        assertEquals("Robinson-san", maleUser.getHonorificName());

        User femaleUser = new User("Bella", "Female");
        assertEquals("Bella-chan", femaleUser.getHonorificName());

        User otherUser = new User("Thomas", "Other");
        assertEquals("Thomas-san", otherUser.getHonorificName());
    }

    @Test
    void getTotalWorkspace_noWorkspace_sizeZero() throws AniException {
        User user = new User("User getTotalWorkspace", "Male");
        assertEquals(0, user.getTotalWorkspaces());
    }

    @Test
    void getTotalWorkspace_twoWorkspaces() throws AniException {
        User user = new User("A test workspace", "Male");

        user.addWorkspace("Test Workspace");
        user.addWorkspace("Test Workspace 2");

        assertEquals(2, user.getTotalWorkspaces());
    }

    @Test
    void setWorkspace_emptyWorkspaceList_setEmpty() throws AniException {
        User user = new User("new User", "femaLe");

        ArrayList<Workspace> workspaceList = new ArrayList<>();

        assertDoesNotThrow(() -> user.setWorkspaceList(workspaceList));
    }

    @Test
    void setWorkspace_filledWorkspaceList_useFirst() throws AniException {
        User user = new User("new User", "femaLe");

        ArrayList<Workspace> workspaceList = new ArrayList<>();
        Workspace newWorkspace = user.addWorkspace("Default");

        workspaceList.add(newWorkspace);
        assertDoesNotThrow(() -> user.setWorkspaceList(workspaceList));
    }

    @Test
    void setActiveWorkspace_empty_useFirst() throws AniException {
        User user = new User("new User", "femaLe");
        Workspace newWorkspace = user.addWorkspace("Default");

        assertThrows(AniException.class, () -> user.setActiveWorkspace(newWorkspace));
    }

    @Test
    void switchActiveWorkspace_emptyWorkspaceList_throwsAniException() throws AniException {
        User user = new User("new User", "femaLe");

        assertThrows(AniException.class, () -> user.switchActiveWorkspace("I want to switch!"));
    }

    @Test
    void addWorkspace_nameExist_throwsAniException() throws AniException {
        User user = new User("new User", "femaLe");
        user.addWorkspace("Default");

        assertThrows(AniException.class, () -> user.addWorkspace("Default"));
    }

    @Test
    void addWorkspace_similarNameExist_throwsAniException() throws AniException {
        User user = new User("new User", "femaLe");
        user.addWorkspace("Default");

        assertThrows(AniException.class, () -> user.addWorkspace("default"));
    }

    @Test
    void deleteWorkspace_invalidNames_throwsAniException() throws AniException {
        User user = new User("new User", "femaLe");
        user.addWorkspace("Default");

        assertThrows(AniException.class, () -> {
            user.deleteWorkspace("");
            user.deleteWorkspace("abc");
        });
    }


}
