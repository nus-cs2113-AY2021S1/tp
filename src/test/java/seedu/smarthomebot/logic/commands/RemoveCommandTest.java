package seedu.smarthomebot.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.smarthomebot.data.appliance.ApplianceList;
import seedu.smarthomebot.data.location.LocationList;
import seedu.smarthomebot.commons.exceptions.DuplicateDataException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.smarthomebot.commons.Messages.MESSAGE_LOCATION_NOT_EXIST;


public class RemoveCommandTest {

    private LocationList locationList = new LocationList();
    private ApplianceList applianceList = new ApplianceList();

    @BeforeEach
    public void setup() throws DuplicateDataException {
        locationList.addLocation("BedRoom1");
        locationList.addLocation("BedRoom2");
    }

    @Test
    void removeLocationTest_removeLocationSuccess() {
        Command removeLocation = new RemoveCommand("BedRoom1");
        removeLocation.setData(applianceList, locationList);
        CommandResult actualCommandResult = removeLocation.execute();
        assertEquals("Removing LOCATION \"BedRoom1\"......REMOVED!", actualCommandResult.feedbackToUser);
    }

    @Test
    void removeLocationTest_LocationNotInList_catch_InvalidLocationException() {
        Command removeLocation = new RemoveCommand("BedRoom3");
        removeLocation.setData(applianceList, locationList);
        CommandResult actualCommandResult = removeLocation.execute();
        assertEquals(MESSAGE_LOCATION_NOT_EXIST + " Nothing will be deleted.", actualCommandResult.feedbackToUser);
    }

}
