package seedu.smarthomebot.logic.commands;

import org.junit.jupiter.api.Test;

import seedu.smarthomebot.commons.exceptions.DuplicateDataException;
import seedu.smarthomebot.data.appliance.ApplianceList;
import seedu.smarthomebot.data.appliance.type.Lights;
import seedu.smarthomebot.data.location.LocationList;
import seedu.smarthomebot.logic.commands.exceptions.InvalidApplianceNameException;
import seedu.smarthomebot.logic.commands.exceptions.LocationNotFoundException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


public class DeleteCommandTest {

    @Test
    public void deleteApplianceTest_doesNotThrowException() throws DuplicateDataException,
            InvalidApplianceNameException, LocationNotFoundException {

        LocationList locationList = new LocationList();
        ApplianceList applianceList = new ApplianceList();
        locationList.addLocation("BedRoom1");
        Lights l1 = new Lights("l1", "BedRoom1", "50", locationList);
        Lights l2 = new Lights("l2", "BedRoom1", "50", locationList);
        applianceList.addAppliance(l1);
        applianceList.addAppliance(l2);

        Command deleteAppliance = new DeleteCommand("l3");
        deleteAppliance.setData(applianceList, locationList);

        assertDoesNotThrow(() -> deleteAppliance.execute());
    }


}
