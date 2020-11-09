package seedu.smarthomebot.data.location;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.smarthomebot.commons.exceptions.DuplicateDataException;
import seedu.smarthomebot.commons.exceptions.InvalidLocationException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LocationListTest {

    private LocationList myHome;
    private ArrayList<String> expectedOutput;
    private ArrayList<String> emptyList;

    @BeforeEach
    void setUp() throws Exception {
        myHome = new LocationList();
        myHome.addLocation("LivingRoom");
        expectedOutput = new ArrayList<>();
        expectedOutput.add("LivingRoom");
        emptyList = new ArrayList<>();
    }

    @Test
    void addLocation_locationNotInList_locationAddedNormally() throws DuplicateDataException {
        myHome.addLocation("MasterRoom");
        expectedOutput.add("MasterRoom");
        assertEquals(expectedOutput, myHome.getAllLocations());
    }

    @Test
    void addLocation_locationAlreadyInList_throwsDuplicateDataException() {
        assertThrows(DuplicateDataException.class, () -> myHome.addLocation("LivingRoom"));
    }

    @Test
    void removeLocation_locationInList_locationRemovedNormally() throws InvalidLocationException {
        myHome.removeLocation("LivingRoom");
        assertEquals(emptyList, myHome.getAllLocations());
    }

    @Test
    void removeLocation_locationNotExist_throws_InvalidRemovalLocationException() {
        assertThrows(InvalidLocationException.class, () -> myHome.removeLocation("Other Places"));
    }

    @Test
    void getLocations() {
        assertEquals(expectedOutput, myHome.getAllLocations());
    }

    @Test
    void testToString() {
        assertEquals("LivingRoom\n", myHome.toString());
    }

    @Test
    void isLocationCreated_locationInList_returnTrue() {
        assertTrue(myHome.isLocationCreated("LivingRoom"));
    }

}