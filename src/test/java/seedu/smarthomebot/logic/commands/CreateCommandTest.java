package seedu.smarthomebot.logic.commands;

import org.junit.jupiter.api.Test;

import seedu.smarthomebot.data.appliance.ApplianceList;
import seedu.smarthomebot.data.location.LocationList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CreateCommandTest {

    @Test
    public void createLocationTest_doesNotThrowException() {

        LocationList locationList = new LocationList();
        ApplianceList applianceList = new ApplianceList();

        Command createLocation = new CreateCommand("BedRoom1");
        createLocation.setData(applianceList, locationList);

        assertDoesNotThrow(() -> createLocation.execute());
    }
}
