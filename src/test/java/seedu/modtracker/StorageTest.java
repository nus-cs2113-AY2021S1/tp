package seedu.modtracker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {
    private static final String TEST_DATA_FILEPATH = "test/data/modtracker.txt";

    @Test
    public void constructor_nullFilePath_exceptionThrown() {
        assertThrows(NullPointerException.class, () -> new Storage(null));
    }

    @Test
    public void load_validFormat_success() throws Exception {
        ModTracker modTracker = new ModTracker(TEST_DATA_FILEPATH);
        modTracker.loadData();
        ModuleList actualModList = modTracker.getModList();

        assertEquals(2, actualModList.getSize());
        assertTrue(actualModList.checkIfModuleExist("CS1000"));
        assertTrue(actualModList.checkIfModuleExist("EE2000"));
        assertFalse(actualModList.checkIfModuleExist("CS1231"));
    }
}
