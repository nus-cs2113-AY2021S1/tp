package fitr.storage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {
    @Test
    public void load_invalidExerciseData_exceptionThrown() throws Exception {
        Storage storage = new Storage("src/test/data/StorageTest/InvalidUserData.txt",
                "src/test/data/StorageTest/InvalidFoodData.txt",
                "src/test/data/StorageTest/InvalidExerciseData.txt");
        assertThrows(ArrayIndexOutOfBoundsException.class, storage::loadExerciseList);
    }

    @Test
    public void load_invalidFoodData_exceptionThrown() throws Exception {
        Storage storage = new Storage("src/test/data/StorageTest/InvalidUserData.txt",
                "src/test/data/StorageTest/InvalidFoodData.txt",
                "src/test/data/StorageTest/InvalidExerciseData.txt");
        assertThrows(ArrayIndexOutOfBoundsException.class, storage::loadFoodList);
    }
}
