package seedu.duke.data;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.InvalidAdditionOfAppliance;
import seedu.duke.exceptions.InvalidAddtionOfLocation;


class SmartHomeBotTest {

    @Test
    void getNameTest() {
        assertEquals("Xiao Mi Light", new Lights("Xiao Mi Light", "BedRoom 1", "45").getName());
    }

    @Test
    void getLocationTest() {
        assertEquals("BedRoom1", new Lights("Xiao Mi Light", "BedRoom1", "45").getLocation());
    }

    @Test
    void getStatusTest() {
        //New appliance added should be always remain Off until User switch it On
        assertEquals("Off", new Lights("Xiao Mi Light", "BedRoom 1", "45").getStatus());
    }

    @Test
    public void addLocationTest() throws InvalidAddtionOfLocation {
        String br1 = "BedRoom 1";
        String br2 = "BedRoom 2";
        HomeLocations homeLocations = new HomeLocations();
        homeLocations.addLocation(br1);
        homeLocations.addLocation(br2);
        assertEquals("[BedRoom 1, BedRoom 2]", homeLocations.getLocations().toString());
        assertThrows(InvalidAddtionOfLocation.class, () -> homeLocations.addLocation(br1));
    }

    @Test
    public void addApplianceTest() throws InvalidAdditionOfAppliance {
        ApplianceList applianceList = new ApplianceList();
        Lights l1 = new Lights("l1", "BedRoom1", "50");
        Lights l2 = new Lights("l2", "BedRoom1", "50");
        applianceList.addAppliance(l1);
        applianceList.addAppliance(l2);
        assertThrows(InvalidAdditionOfAppliance.class, () -> applianceList.addAppliance(l1));

    }

    @Test
    void getTypeTest() {
        assertEquals("AirConditioner", new AirConditioner("aircon1", "br1", "25").getType());
    }

    @Test
    void onOffTest() {
        AirConditioner aircon = new AirConditioner("aircon", "br1", "200");
        if (aircon.getStatus().equals("On")) {
            assertEquals("aircon: On", aircon.toString());
        } else {
            assertEquals("aircon: Off", aircon.toString());
        }
    }

}
