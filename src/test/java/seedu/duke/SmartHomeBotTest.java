package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import seedu.duke.data.HomeLocations;

class SmartHomeBotTest {
    @Test
    public void addLocationTest() {
        String br1 = "BedRoom 1";
        String br2 = "BedRoom 2";
        HomeLocations homeLocations = new HomeLocations();
        homeLocations.addLocation(br1);
        homeLocations.addLocation(br2);
        assertEquals("[BedRoom 1, BedRoom 2]", homeLocations.getLocations().toString());
    }
}
