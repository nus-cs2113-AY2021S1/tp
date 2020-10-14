package seedu.duke.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LightsTest {

    @Test
    void getType() {
        assertEquals("Light", new Lights("light 1", "bedroom 1", "200").getType());
    }

    @Test
    void testToString() {
        assertEquals("light 1: Off", new Lights("light 1", "bedroom 1", "200").toString());
    }
}