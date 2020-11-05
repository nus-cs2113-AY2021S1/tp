package seedu.smarthomebot.logic.commands;

import org.junit.jupiter.api.Test;

import seedu.smarthomebot.data.appliance.ApplianceList;
import seedu.smarthomebot.data.location.LocationList;
import seedu.smarthomebot.commons.exceptions.DuplicateDataException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


public class RemoveCommandTest {

    @Test
    public void removeLocationTest_doesNotThrowException() throws DuplicateDataException {

        LocationList locationList = new LocationList();
        ApplianceList applianceList = new ApplianceList();
        locationList.addLocation("BedRoom1");
        locationList.addLocation("BedRoom2");

        Command removeLocation = new RemoveCommand("BedRoom1");
        removeLocation.setData(applianceList, locationList);

        assertDoesNotThrow(() -> removeLocation.execute());
    }
}
