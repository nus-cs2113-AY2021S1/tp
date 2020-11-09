package seedu.smarthomebot.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.smarthomebot.data.appliance.ApplianceList;
import seedu.smarthomebot.data.location.LocationList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class WriteStorageFileTest {

    private LocationList locationList;
    private ApplianceList applianceList;

    @BeforeEach
    void setUp() {
        locationList = new LocationList();
        applianceList = new ApplianceList();
    }

    @Test
    void writeStorageFileTest_doesNotThrowException() {
        WriteStorageFile test = new WriteStorageFile("data/SmartHomeBot.txt", applianceList, locationList);
        assertDoesNotThrow(() -> test.execute());
    }
}