package seedu.smarthomebot.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.smarthomebot.commons.exceptions.DuplicateDataException;
import seedu.smarthomebot.data.appliance.ApplianceList;
import seedu.smarthomebot.data.appliance.type.Lights;
import seedu.smarthomebot.data.location.LocationList;
import seedu.smarthomebot.logic.commands.exceptions.InvalidApplianceNameException;
import seedu.smarthomebot.logic.commands.exceptions.LocationNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.smarthomebot.commons.Messages.MESSAGE_LOCATION_EXIST;

public class CreateCommandTest {

    private LocationList locationList = new LocationList();
    private ApplianceList applianceList = new ApplianceList();

    @BeforeEach
    public void setup() throws DuplicateDataException, InvalidApplianceNameException, LocationNotFoundException {
        locationList.addLocation("BedRoom1");
        Lights l1 = new Lights("l1", "BedRoom1", "50", locationList);
        applianceList.addAppliance(l1);
    }

    @Test
    void createLocationTest_createLocationSuccess() {
        Command createLocation = new CreateCommand("BedRoom3");
        createLocation.setData(applianceList, locationList);
        CommandResult actualCommandResult = createLocation.execute();
        assertEquals("Creating Location \"BedRoom3\".....CREATED!", actualCommandResult.feedbackToUser);
    }

    @Test
    void createLocationTest_DuplicateLocation_catch_DuplicateDataException() {
        Command createLocation = new CreateCommand("BedRoom1");
        createLocation.setData(applianceList, locationList);
        CommandResult actualCommandResult = createLocation.execute();
        assertEquals(MESSAGE_LOCATION_EXIST, actualCommandResult.feedbackToUser);
    }

    @Test
    void createLocationTest_DuplicateNameAsAppliance_catch_InvalidLocationException() {
        Command createLocation = new CreateCommand("l1");
        createLocation.setData(applianceList, locationList);
        CommandResult actualCommandResult = createLocation.execute();
        assertEquals(MESSAGE_LOCATION_EXIST + " as an Appliance, please choose another name.",
                actualCommandResult.feedbackToUser);
    }
}
