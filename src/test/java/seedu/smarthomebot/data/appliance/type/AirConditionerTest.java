package seedu.smarthomebot.data.appliance.type;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.smarthomebot.commons.exceptions.DuplicateDataException;
import seedu.smarthomebot.data.location.LocationList;
import seedu.smarthomebot.logic.commands.exceptions.InvalidApplianceNameException;
import seedu.smarthomebot.logic.commands.exceptions.LocationNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AirConditionerTest {

    private AirConditioner ac;

    @BeforeEach
    void setUp() throws InvalidApplianceNameException, LocationNotFoundException, DuplicateDataException {
        String bedroom = "bedroom";
        LocationList locationList = new LocationList();
        locationList.addLocation(bedroom);
        ac = new AirConditioner("ac1", bedroom, "2000", locationList);
    }

    @Test
    void getParameterTest_returnsTemperature() {
        assertEquals("25 Degrees", ac.getParameter(true));
    }

    @Test
    void setSpeedTest_returnTrue() {
        assertEquals(true, ac.setTemperature("25"));
    }

    @Test
    void getTypeTest_returnsAircon() {
        assertEquals("aircon", ac.getType());
    }

    @Test
    void testToString_returnsNameLocationPower() {
        assertEquals("ac1(2000W), located at bedroom ", ac.toString());
    }
}