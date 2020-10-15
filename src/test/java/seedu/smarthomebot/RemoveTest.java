package seedu.smarthomebot;

import org.junit.jupiter.api.Test;
import seedu.smarthomebot.data.HomeLocations;
import seedu.smarthomebot.exceptions.EmptyParameterException;
import seedu.smarthomebot.exceptions.InvalidAddtionOfLocation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RemoveTest {

    @Test
    public void removeLocationTest() throws InvalidAddtionOfLocation, EmptyParameterException {
        //Create Sample Locations and empty appliance list
        HomeLocations homeLocations = new HomeLocations();
        homeLocations.addLocation("BedRoom1");
        homeLocations.addLocation("BedRoom3");

        // Prepare to read output of command
        assertThrows(IndexOutOfBoundsException.class, () -> homeLocations.removeLocation("Bedroom 2"));
    }
}