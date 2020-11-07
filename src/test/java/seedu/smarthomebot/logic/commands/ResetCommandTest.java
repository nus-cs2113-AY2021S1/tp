package seedu.smarthomebot.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.smarthomebot.commons.exceptions.DuplicateDataException;
import seedu.smarthomebot.data.appliance.ApplianceList;
import seedu.smarthomebot.data.appliance.type.Fan;
import seedu.smarthomebot.data.appliance.type.Lights;
import seedu.smarthomebot.data.location.LocationList;
import seedu.smarthomebot.logic.commands.exceptions.InvalidApplianceNameException;
import seedu.smarthomebot.logic.commands.exceptions.LocationNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.smarthomebot.commons.Messages.MESSAGE_LIST_NO_APPLIANCES;
import static seedu.smarthomebot.commons.Messages.MESSAGE_USAGE_RESET;

class ResetCommandTest {

    private final ApplianceList emptyApplianceList = new ApplianceList();
    private LocationList locationList = new LocationList();
    private ApplianceList applianceList = new ApplianceList();


    @Test
    void resetTest_EmptyApplianceList_EmptyApplianceListException() {
        Command resetPower = new ResetCommand();
        resetPower.setData(emptyApplianceList, locationList);
        CommandResult actualCommandResult = resetPower.execute();
        assertEquals(MESSAGE_LIST_NO_APPLIANCES, actualCommandResult.feedbackToUser);
    }

    @Test
    void resetTest_resetPowerUsageSuccess() throws DuplicateDataException,
            InvalidApplianceNameException, LocationNotFoundException {
        locationList.addLocation("BedRoom");
        Lights light = new Lights("BrightLight", "BedRoom", "100", locationList);
        Fan fan = new Fan("SpeedyFan", "BedRoom", "50", locationList);
        applianceList.addAppliance(light);
        applianceList.addAppliance(fan);
        Command resetPower = new ResetCommand();
        resetPower.setData(emptyApplianceList, locationList);
        CommandResult actualCommandResult = resetPower.execute();
        assertEquals(MESSAGE_USAGE_RESET, actualCommandResult.feedbackToUser);
    }

}