package seedu.duke.human;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.AniException;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WorkspaceTest {

    @Test
    public void testProfileCreation() throws ParseException, AniException {
        Workspace testProfile = new Workspace("Tim", "Other");

        assertEquals("Tim-san", testProfile.getHonorificName());
        assertEquals("Other", testProfile.getGender().toString());
    }
}
