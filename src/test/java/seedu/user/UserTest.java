package seedu.user;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @Test
    void getEncipheredPassword() {
        User test = new User("John","123456");
        
        assertEquals("561278", test.getEncipheredPassword());
    }
}