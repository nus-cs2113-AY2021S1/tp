package seedu.smarthomebot.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.smarthomebot.data.appliance.ApplianceList;
import seedu.smarthomebot.data.appliance.type.Fan;
import seedu.smarthomebot.data.location.LocationList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.smarthomebot.commons.Messages.MESSAGE_APPLIANCE_OR_LOCATION_NOT_EXIST;

public class OffCommandTest {

    private LocationList myHome;
    private ArrayList<String> expectedOutput;
    private ApplianceList applianceList;
    private ArrayList<String> emptyList;

    @BeforeEach
    public void setUp() throws Exception {
        applianceList = new ApplianceList();
        emptyList = new ArrayList<>();
        expectedOutput = new ArrayList<>();
        myHome = new LocationList();
        myHome.addLocation("MasterRoom");
        Fan newFan = new Fan("Fan", "MasterRoom", "150", myHome);
        applianceList.addAppliance(newFan);
        expectedOutput.add(newFan.toString());

    }

    @Test
    void offCommandTest_OffNormally() {
        applianceList.getAppliance(0).switchOn();
        Command offCommand = new OffCommand("Fan");
        offCommand.setData(applianceList, myHome);
        assertDoesNotThrow(() -> offCommand.execute());
    }

    @Test
    void offCommandTest_ApplianceNotFoundException() {
        Command offCommand = new OffCommand("Fan1");
        offCommand.setData(applianceList, myHome);
        CommandResult actualCommandResult = offCommand.execute();
        assertEquals(MESSAGE_APPLIANCE_OR_LOCATION_NOT_EXIST, actualCommandResult.feedbackToUser);
    }

}


