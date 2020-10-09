package seedu.duke;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.duke.common.Messages.MESSAGE_LOCATION_NOT_EXIST;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.Command;
import seedu.duke.commands.RemoveCommand;
import seedu.duke.data.ApplianceList;
import seedu.duke.data.HomeLocations;
import seedu.duke.data.framework.Appliance;
import seedu.duke.data.type.Lights;
import seedu.duke.exceptions.EmptyParameterException;
import seedu.duke.exceptions.InvalidAdditionOfAppliance;
import seedu.duke.exceptions.InvalidAddtionOfLocation;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


class SmartHomeBotTest {

    @Test
    public void addLocationTest() throws InvalidAddtionOfLocation {
        String br1 = "BedRoom 1";
        String br2 = "BedRoom 2";
        HomeLocations homeLocations = new HomeLocations();
        homeLocations.addLocation(br1);
        homeLocations.addLocation(br2);
        assertEquals("[BedRoom 1, BedRoom 2]", homeLocations.getLocations().toString());
        assertThrows(InvalidAddtionOfLocation.class, () ->
        {
            homeLocations.addLocation(br1);
        });
    }

    @Test
    public void addApplianceTest() throws InvalidAdditionOfAppliance {
        ApplianceList applianceList = new ApplianceList();
        Lights l1 = new Lights("l1","BedRoom1","50");
        Lights l2 = new Lights("l1","BedRoom1","50");
        applianceList.addAppliance(l1);
        applianceList.addAppliance(l2);
        assertThrows(InvalidAdditionOfAppliance.class, () ->
        {
            applianceList.addAppliance(l1);
        });

    }

    @Test
    public void removeLocationTest() throws InvalidAddtionOfLocation, EmptyParameterException {
        //Create Sample Locations and empty appliance list
        HomeLocations homeLocations = new HomeLocations();
        ApplianceList applianceList = new ApplianceList();
        homeLocations.addLocation("BedRoom1");
        homeLocations.addLocation("BedRoom3");
        // Prepare to read output of command
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Remove Invalid location
        Command RemoveCommand = new RemoveCommand("Bedroom2");
        RemoveCommand.setData(applianceList, homeLocations);
        RemoveCommand.execute();

        //Process ui output
        String outputString = outContent.toString().replace(System.getProperty("line.separator"), "");

        //compare outputs
        assertEquals(MESSAGE_LOCATION_NOT_EXIST+" Nothing will be deleted.",outputString);

    }
}
