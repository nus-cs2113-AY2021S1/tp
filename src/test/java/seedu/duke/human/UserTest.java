package seedu.duke.human;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.AniException;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {
    private static final String USER_PROFILE_FILE_NAME = "userprofileTEST.txt";
    private static final String WATCHLIST_FILE_NAME = "watchlistTEST.txt";

    @Test
    void getTotalWorkspace_noWorkspace_sizeZero() throws AniException {
        User user = new User("A", "Male");
        assertEquals(0, user.getTotalWorkspaces());
    }

    @Test
    void testAddUser() throws ParseException, AniException {
        User user = new User("A", "Male");

        Workspace newWorkspace = user.addWorkspace("Studio A");
        assertEquals(1, user.getTotalWorkspaces());
    }
}
