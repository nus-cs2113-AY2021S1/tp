package seedu.smarthomebot.data.appliance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.smarthomebot.commons.exceptions.ApplianceNotFoundException;

import seedu.smarthomebot.data.appliance.type.Fan;
import seedu.smarthomebot.data.location.LocationList;
import seedu.smarthomebot.logic.commands.exceptions.LocationNotFoundException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ApplianceListTest {

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
    void addAppliance_ApplianceNotInList_ApplianceAddedNormally() {
        for (int i = 0; i < applianceList.getAllAppliance().size(); i++) {
            assertEquals(applianceList.getAppliance(i).toString(), expectedOutput.get(i));
        }
    }

    @Test
    void addAppliance_ApplianceNotInList_LocationNotInLocationList() {
        assertThrows(LocationNotFoundException.class, () ->
                new Fan("Fan", "Bedroom1", "150", myHome));
    }

    @Test
    void deleteAppliance_ApplianceInList_ApplianceDeleteNormally() {
        assertDoesNotThrow(() -> applianceList.deleteAppliance("Fan"));

    }

    @Test
    void deleteAppliance_ApplianceInList_ExceptionThrown() {
        assertThrows(ApplianceNotFoundException.class, () ->
                applianceList.deleteAppliance("Fan3"));

    }

    @Test
    void isApplianceExist_ApplianceInList_IsTrue() {
        assertTrue(() -> applianceList.isApplianceExist("Fan"));
    }

    @Test
    void isApplianceExist_ApplianceInList_IsFalse() {
        assertFalse(() -> applianceList.isApplianceExist("Fan3"));
    }

    @Test
    void getApplianceIndex_ApplianceInList_GetNormally() {
        assertDoesNotThrow(() -> applianceList.getApplianceIndex("Fan"));
    }

    @Test
    void getApplianceIndex_ApplianceNotInList_exceptionThrown() {
        assertThrows(ApplianceNotFoundException.class, () ->
                applianceList.getApplianceIndex("Fan3"));
    }

    @Test
    void deleteByLocation_LocationInList_DeleteNormally() {
        assertDoesNotThrow(() ->
                applianceList.deleteByLocation("MasterRoom"));
    }

    @Test
    void getAppliance_ApplianceInList_GetNormally() {
        assertDoesNotThrow(() -> applianceList.getAppliance(0));
    }
    

}
