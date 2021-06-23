package diet.dietmanager;

import diet.dietsession.DietSession;
import exceptions.InvalidDateFormatException;
import org.junit.jupiter.api.Test;
import storage.diet.DietStorage;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

//@@author CFZeon
public class DietSessionStorageTest {

    private static final String TEST_SAVES_FOLDER_DIET = "src/test/java/diet/dietmanager/saves/";
    private static final String TEST_SAVES_FOLDER_CORRUPTED_DIET = "src/test/java/diet/dietmanager/corruptedsaves/";
    private static final String TEST_SAVE_NAME = "2020-05-04 breakfast";

    /**
     * Tests readDietSession method of class DietStorage when data from file is read.
     */
    @Test
    void testReadDietSession_exampleDietData_returnsDietSessionInstance() {
        DietStorage storage = new DietStorage();
        DietSession loadedInstance = null;
        loadedInstance = storage.readDietSession(TEST_SAVES_FOLDER_DIET, "2020-11-05 breakfast.json");
        assertNotNull(loadedInstance);
    }

    /**
     * Tests readDietSession method of class DietStorage when corrupted data from file is read.
     */
    @Test
    void testReadDietSession_corruptedDietData_returnsNull() {
        DietStorage storage = new DietStorage();
        DietSession loadedInstance = null;
        loadedInstance = storage.readDietSession(TEST_SAVES_FOLDER_CORRUPTED_DIET, "2020-11-09 unspecified.json");
        assertNull(loadedInstance);
    }

    /**
     * Tests the saveDietSession by saving and reading from folder.
     *
     * @throws IOException if file is not read properly
     * @throws InvalidDateFormatException if date format is wrong during DietSession instantiation
     */
    @Test
    void saveDietSession_newDietData_returnsDietSessionInstance() throws IOException, InvalidDateFormatException {
        DietStorage storage = new DietStorage();
        storage.init(TEST_SAVES_FOLDER_DIET, TEST_SAVE_NAME);
        DietSession savedInstance = new DietSession("breakfast", "2020-05-04", true, 1);
        savedInstance.saveToFile(TEST_SAVES_FOLDER_DIET, storage, savedInstance);
        DietSession loadedInstance = null;
        loadedInstance = storage.readDietSession(TEST_SAVES_FOLDER_DIET, TEST_SAVE_NAME + ".json");
        assertNotNull(loadedInstance);
    }
}
