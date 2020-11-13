package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.userprofile.InitialiseUserProfile;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewUserAddTest {
    @Test
    void addNewUserTest_success() {
        InitialiseUserProfile userinfo =
                new InitialiseUserProfile("Sam","Female","50","130","20","2","maintain");
        String output = userinfo.toString();
        assertEquals(output, "Sam,Female,50,130,20,2,maintain");
    }
}