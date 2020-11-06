package seedu.smarthomebot.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.smarthomebot.data.appliance.ApplianceList;
import seedu.smarthomebot.data.appliance.type.Fan;
import seedu.smarthomebot.data.location.LocationList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void switchOff_Appliance_switchOffNormally() {
        applianceList.getAppliance(0).switchOn();
        assertTrue(applianceList.getAppliance(0).switchOff());
    }

    @Test
    void switchOff_Appliance_switchOffPreviously() {
        assertFalse(applianceList.getAppliance(0).switchOff());
    }
}


