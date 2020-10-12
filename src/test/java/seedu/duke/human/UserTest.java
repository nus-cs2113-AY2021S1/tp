package seedu.duke.human;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.AniException;
import seedu.duke.human.UserProfile;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserProfileTest {

    @Test
    public void testProfileCreation() throws ParseException, AniException {
        UserProfile testProfile = new UserProfile("Tim Apple", "1/11/1960", "Others");
        assertEquals("Name= Tim Apple, birthdate= 01/11/1960, gender= Other", testProfile.toString());
    }
}
