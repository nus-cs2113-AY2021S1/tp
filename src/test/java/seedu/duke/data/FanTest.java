package seedu.duke.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FanTest {

    @Test
    void getType() {
        assertEquals("Fan", new Fan("Fan 1", "bedroom 1", "200").getType());
    }

}