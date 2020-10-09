package seedu.duke.data.type;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AirConditionerTest {

    @Test
    void getType() {
        assertEquals("AirConditioner",new AirConditioner("aircon1", "br1", "25").getType());
    }

    @Test
    void testToString() {
        AirConditioner aircon = new AirConditioner("aircon", "br1", "200");
        if (aircon.getStatus().equals("On")) {
            assertEquals("aircon: On", aircon.toString());
        } else {
            assertEquals("aircon: Off", aircon.toString());
        }
    }
}