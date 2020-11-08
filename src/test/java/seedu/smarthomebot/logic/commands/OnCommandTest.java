package seedu.smarthomebot.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.smarthomebot.data.appliance.ApplianceList;
import seedu.smarthomebot.data.appliance.type.Fan;
import seedu.smarthomebot.data.location.LocationList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OnCommandTest {

    private LocationList myHome;
    private ApplianceList applianceList;

    @BeforeEach
    public void setUp() throws Exception {
        applianceList = new ApplianceList();
        myHome = new LocationList();
        myHome.addLocation("MasterRoom");
        Fan newFan = new Fan("Fan", "MasterRoom", "150", myHome);
        applianceList.addAppliance(newFan);


    }

    @Test
    void onCommandTest_OnNormally() {
        Command onCommand = new OnCommand("Fan", "");
        onCommand.setData(applianceList, myHome);
        assertDoesNotThrow(() -> onCommand.execute());
    }

    @Test
    void onCommandTest_ParameterFoundException() {
        Command onCommand = new OnCommand("Fan", "-2");
        onCommand.setData(applianceList, myHome);
        String expectedOutput = "Invalid speed is detected, "
                + "ensure that it is within 1-3 speed.\n"
                + "Previous set speed will be set.Switching Fan(150W), located at MasterRoom @ Speed 1.....ON";
        CommandResult actualCommandResult = onCommand.execute();
        assertEquals(expectedOutput, actualCommandResult.feedbackToUser);
    }

}


