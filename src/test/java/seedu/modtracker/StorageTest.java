package seedu.modtracker;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    public void loadData_validFormat_success() {
        ModTracker modTracker = new ModTracker(TEST_DATA_FILEPATH);
        modTracker.getStorage().loadData(modTracker);

        ModuleList actualModList = modTracker.getModList();
        assertTrue(actualModList.checkIfModuleExist("CS1000"));
        assertTrue(actualModList.checkIfModuleExist("EE2000"));
        assertFalse(actualModList.checkIfModuleExist("CS1231"));

        TaskList actualTaskList = modTracker.getTaskList();
        assertEquals("CS1000", actualTaskList.getTaskData().get(0).getModCode());
        assertEquals(1, actualTaskList.getTaskData().size());
    }

    @Test
    public void appendToFile_validFormat_success() {
        long lines = numOfLines();
        Storage storage = new Storage(TEST_DATA_FILEPATH);
        storage.appendToFile("addmod AB1000C");
        storage.appendToFile("deletemod AB1000C");
        assertEquals(lines + 2, numOfLines());
    }

    @Test
    public void clearData_validFormat_success() {
        Storage storage = new Storage(TEST_DATA_FILEPATH);
        storage.clearData();
        assertEquals(1, numOfLines());

        // load back data for other tests
        storage.appendToFile("addmod CS1000");
        storage.appendToFile("addmod EE2000");
        storage.appendToFile("addtask CS1000 revision");
    }

    @Test
    public void resetData_validFormat_success() {
        Storage storage = new Storage(TEST_DATA_FILEPATH);
        storage.reset();
        assertEquals(0, numOfLines());

        // load back data for other tests
        storage.appendToFile("John Doe");
        storage.appendToFile("addmod CS1000");
        storage.appendToFile("addmod EE2000");
        storage.appendToFile("addtask CS1000 revision");
    }

    private long numOfLines() {
        try {
            Path path = Paths.get(TEST_DATA_FILEPATH);
            return Files.lines(path).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
