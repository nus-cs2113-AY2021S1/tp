package seedu.smarthomebot.data.appliance.type;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.smarthomebot.commons.exceptions.DuplicateDataException;
import seedu.smarthomebot.data.location.LocationList;
import seedu.smarthomebot.logic.commands.exceptions.InvalidApplianceNameException;
import seedu.smarthomebot.logic.commands.exceptions.LocationNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FanTest {

    private Fan fan;

    @BeforeEach
    void setUp() throws InvalidApplianceNameException, LocationNotFoundException, DuplicateDataException {
        String bedroom = "bedroom";
        LocationList locationList = new LocationList();
        locationList.addLocation(bedroom);
        fan = new Fan("fanny", bedroom, "150", locationList);
    }

    @Test
    void getParameterTest_returnsNone() {
        assertEquals("Speed 1", fan.getParameter(true));
    }

    @Test
    void setSpeedTest_returnTrue() {
        assertEquals(true, fan.setSpeed("2"));
    }

    @Test
    void getTypeTest_returnsfan() {
        assertEquals("fan", fan.getType());
    }

    @Test
    void testToString() {
        assertEquals("fanny(150W), located at bedroom ",fan.toString());
    }
}