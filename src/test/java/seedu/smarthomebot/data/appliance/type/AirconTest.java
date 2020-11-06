package seedu.smarthomebot.data.appliance.type;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.smarthomebot.commons.exceptions.DuplicateDataException;
import seedu.smarthomebot.data.appliance.Appliance;
import seedu.smarthomebot.data.location.LocationList;
import seedu.smarthomebot.logic.commands.exceptions.InvalidApplianceNameException;
import seedu.smarthomebot.logic.commands.exceptions.LocationNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AirconTest {

    private Appliance aircon;
    private LocationList locationList;

    @BeforeEach
    public void setUp() throws InvalidApplianceNameException, LocationNotFoundException, DuplicateDataException {
        String bedroom = "bedroom";
        locationList = new LocationList();
        locationList.addLocation(bedroom);
        aircon = new AirConditioner("coldy", bedroom, "150", locationList);
    }

    @Test
    void getType_nullInput_returnsAC() {
        assertEquals("aircon", aircon.getType());
    }

}