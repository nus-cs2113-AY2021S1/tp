package seedu.smarthomebot.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.smarthomebot.data.appliance.ApplianceList;
import seedu.smarthomebot.data.appliance.type.Fan;
import seedu.smarthomebot.data.location.LocationList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class OnCommandTest {

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
    void switchOn_Appliance_switchOnNormally() {
        assertTrue(applianceList.getAppliance(0).switchOn());
    }

    @Test
    void switchOn_Appliance_switchOnPreviously() {
        applianceList.getAppliance(0).switchOn();
        assertFalse(applianceList.getAppliance(0).switchOn());
    }
}


