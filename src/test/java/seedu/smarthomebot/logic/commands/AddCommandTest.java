package seedu.smarthomebot.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.smarthomebot.commons.exceptions.DuplicateDataException;
import seedu.smarthomebot.data.appliance.ApplianceList;
import seedu.smarthomebot.data.location.LocationList;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AddCommandTest {

    @Test
    public void addApplianceTest_doesNotThrowException() throws DuplicateDataException {

        LocationList locationList = new LocationList();
        ApplianceList applianceList = new ApplianceList();
        locationList.addLocation("BedRoom1");

        Command addAppliance = new AddCommand("l3", "BedRoom1", "50", "light");
        addAppliance.setData(applianceList, locationList);

        assertDoesNotThrow(() -> addAppliance.execute());
    }
}
