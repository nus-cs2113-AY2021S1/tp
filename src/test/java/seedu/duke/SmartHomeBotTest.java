package seedu.duke;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import seedu.duke.data.ApplianceList;
import seedu.duke.data.HomeLocations;
import seedu.duke.data.type.Lights;
import seedu.duke.exceptions.InvalidAdditionOfAppliance;
import seedu.duke.exceptions.InvalidAddtionOfLocation;

class SmartHomeBotTest {
    @Test
    public void addLocationTest() throws InvalidAddtionOfLocation {
        String br1 = "BedRoom 1";
        String br2 = "BedRoom 2";
        HomeLocations homeLocations = new HomeLocations();
        homeLocations.addLocation(br1);
        homeLocations.addLocation(br2);
        assertEquals("[BedRoom 1, BedRoom 2]", homeLocations.getLocations().toString());
        assertThrows(InvalidAddtionOfLocation.class, () ->
        {
            homeLocations.addLocation(br1);
        });
    }

    public void addApplianceTest() throws InvalidAdditionOfAppliance, InvalidAddtionOfLocation {
        ApplianceList applianceList = new ApplianceList();
        addLocationTest();
        Lights l1 = new Lights("l1","BedRoom1","50");
        Lights l2 = new Lights("l1","BedRoom1","50");
        applianceList.addAppliance(l1);
        applianceList.addAppliance(l2);
        assertThrows(InvalidAdditionOfAppliance.class, () ->
        {
            applianceList.addAppliance(l1);
        });

    }
}
