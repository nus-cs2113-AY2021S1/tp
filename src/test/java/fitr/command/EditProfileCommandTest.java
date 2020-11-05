package fitr.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class EditProfileCommandTest {
    @Test
    public void editProfileCommandExitTest() {
        Command editProfile = new EditProfileCommand("name", "James");
        assertFalse(editProfile.isExit());
    }
}
