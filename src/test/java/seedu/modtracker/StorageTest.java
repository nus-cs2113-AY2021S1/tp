package seedu.modtracker;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {
    private static final String TEST_DATA_FILEPATH = "test/data/modtracker.txt";

    @Test
    public void constructor_nullFilePath_exceptionThrown() {
        assertThrows(NullPointerException.class, () -> new Storage(null));
    }

    @Test
    public void testIfFileExists_validFilePath_success() {
        new Storage(TEST_DATA_FILEPATH);
        assertTrue(new File(TEST_DATA_FILEPATH).isFile());
    }

    @Test
    public void getReader_validFilePath_success() {
        Storage storage = new Storage(TEST_DATA_FILEPATH);
        assertNotNull(storage.getReader());
    }

    @Test
    public void getName_validName_success() {
        Storage storage = new Storage(TEST_DATA_FILEPATH);
        assertEquals("John Doe", storage.getName());
    }

    @Test
    public void load_validFormat_success() throws Exception {
        ModTracker modTracker = new ModTracker(TEST_DATA_FILEPATH);
        modTracker.getStorage().loadData(modTracker);
        ModuleList actualModList = modTracker.getModList();

        assertTrue(actualModList.checkIfModuleExist("CS1000"));
        assertTrue(actualModList.checkIfModuleExist("EE2000"));
        assertFalse(actualModList.checkIfModuleExist("CS1231"));
    }
}
