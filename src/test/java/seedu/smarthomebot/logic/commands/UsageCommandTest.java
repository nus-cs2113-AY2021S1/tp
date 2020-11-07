package seedu.smarthomebot.logic.commands;


import org.junit.jupiter.api.Test;
import seedu.smarthomebot.commons.exceptions.DuplicateDataException;
import seedu.smarthomebot.data.appliance.ApplianceList;
import seedu.smarthomebot.data.appliance.type.Lights;
import seedu.smarthomebot.data.location.LocationList;
import seedu.smarthomebot.logic.commands.exceptions.InvalidApplianceNameException;
import seedu.smarthomebot.logic.commands.exceptions.LocationNotFoundException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.smarthomebot.commons.Messages.MESSAGE_LIST_NO_APPLIANCES;

class UsageCommandTest {

    private final ApplianceList emptyApplianceList = new ApplianceList();
    private LocationList locationList = new LocationList();
    private ApplianceList applianceList = new ApplianceList();



    @Test
    void usageTest_EmptyApplianceList_EmptyApplianceListException() {
        Command computeUsage = new UsageCommand();
        computeUsage.setData(emptyApplianceList, locationList);
        CommandResult actualCommandResult = computeUsage.execute();
        assertEquals(MESSAGE_LIST_NO_APPLIANCES, actualCommandResult.feedbackToUser);
    }

    @Test
    void usageTest_computePowerUsageSuccess() throws DuplicateDataException,
            InvalidApplianceNameException, LocationNotFoundException {
        locationList.addLocation("BedRoom");
        Lights light = new Lights("BrightLight", "BedRoom", "100", locationList);
        applianceList.addAppliance(light);
        Command computeUsage = new UsageCommand();
        computeUsage.setData(applianceList, locationList);
        computeUsage.execute();
        assertEquals(applianceList.getAppliance(0).getPowerInString(), "0.00");
    }

}