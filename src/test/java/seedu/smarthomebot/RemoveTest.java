package seedu.smarthomebot;

import org.junit.jupiter.api.Test;
import seedu.smarthomebot.data.LocationList;
import seedu.smarthomebot.exceptions.EmptyParameterException;
import seedu.smarthomebot.exceptions.InvalidAddtionOfLocation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RemoveTest {

    @Test
    public void removeLocationTest() throws InvalidAddtionOfLocation, EmptyParameterException {
        //Create Sample Locations and empty appliance list
        LocationList locationList = new LocationList();
        locationList.addLocation("BedRoom1");
        locationList.addLocation("BedRoom3");

        // Prepare to read output of command
        assertThrows(IndexOutOfBoundsException.class, () -> locationList.removeLocation("Bedroom 2"));
    }
}