package fitr.storage;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StandardExerciseStorageTest {
    StandardExerciseStorage standardExerciseStorage = new StandardExerciseStorage();

    @Test
    public void loadAerobicList_success() throws IOException {
        assertEquals(13, standardExerciseStorage.loadAerobicList().getSize());
    }

    @Test
    public void loadLowerBodyList_success() throws IOException {
        assertEquals(14, standardExerciseStorage.loadLowerBodyList().getSize());
    }

    @Test
    public void loadUpperBodyList_success() throws IOException {
        assertEquals(9, standardExerciseStorage.loadUpperBodyList().getSize());
    }

    @Test
    public void loadStretchList_success() throws IOException {
        assertEquals(8, standardExerciseStorage.loadStretchList().getSize());
    }
}
