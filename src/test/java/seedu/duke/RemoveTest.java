package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.Command;
import seedu.duke.commands.RemoveCommand;
import seedu.duke.data.ApplianceList;
import seedu.duke.data.HomeLocations;
import seedu.duke.exceptions.EmptyParameterException;
import seedu.duke.exceptions.InvalidAddtionOfLocation;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.duke.common.Messages.MESSAGE_LOCATION_NOT_EXIST;

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