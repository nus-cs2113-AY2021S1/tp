package seedu.smarthomebot.data.appliance.type;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.smarthomebot.commons.exceptions.DuplicateDataException;
import seedu.smarthomebot.data.appliance.Appliance;
import seedu.smarthomebot.data.location.LocationList;
import seedu.smarthomebot.logic.commands.exceptions.InvalidApplianceNameException;
import seedu.smarthomebot.logic.commands.exceptions.LocationNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LightsTest {

    private Appliance light;

    @BeforeEach
    void setUp() throws InvalidApplianceNameException, LocationNotFoundException, DuplicateDataException {
        String bedroom = "bedroom";
        LocationList locationList = new LocationList();
        locationList.addLocation(bedroom);
        light = new Lights("pluggy", bedroom, "150", locationList);
    }

    @Test
    void getTypeTest_returnsLight() {
        assertEquals("light", light.getType());
    }

    @Test
    void getParameterTest_returnsNone() {
        assertEquals("None", light.getParameter(true));
    }
}