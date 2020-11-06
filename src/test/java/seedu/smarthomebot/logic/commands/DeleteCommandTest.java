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


public class DeleteCommandTest {

    private LocationList locationList = new LocationList();
    private ApplianceList applianceList = new ApplianceList();

    @BeforeEach
    public void setup() throws DuplicateDataException,InvalidApplianceNameException, LocationNotFoundException {
        locationList.addLocation("BedRoom1");
        Lights l1 = new Lights("l1", "BedRoom1", "50", locationList);
        Lights l2 = new Lights("l2", "BedRoom1", "50", locationList);
        applianceList.addAppliance(l1);
        applianceList.addAppliance(l2);
    }

    @Test
    void deleteApplianceTest_deleteApplianceSuccess() {
        Command deleteAppliance = new DeleteCommand("l2");
        deleteAppliance.setData(applianceList, locationList);
        CommandResult actualCommandResult = deleteAppliance.execute();
        assertEquals("Deleting l2(50W), located at BedRoom1 .......DELETED.", actualCommandResult.feedbackToUser);
    }

    @Test
    void deleteApplianceTest_ApplianceNotIsList_ApplianceNotFoundException() {
        Command deleteAppliance = new DeleteCommand("l3");
        deleteAppliance.setData(applianceList, locationList);
        CommandResult actualCommandResult = deleteAppliance.execute();
        assertEquals("l3 does not exist.", actualCommandResult.feedbackToUser);
    }

}
