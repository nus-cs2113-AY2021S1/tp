package seedu.dietbook.saveload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PersonSaveLoadManagerTest {

    @BeforeEach
    private void setUp() {
        PersonSaveLoadManager pslTest = new PersonSaveLoadManager();
        pslTest.setName("Victor Chng");
        pslTest.setActivityLevel(0);
        pslTest.setGender("UnKnown");
        pslTest.setAge(100);
        pslTest.setOriginalWeight(200);
        pslTest.setCurrentWeight(300);
        pslTest.setTargetWeight(100);
        pslTest.save("pslTest");
    }

    @Test
    private void load_noSuchFile_expectFileNotFoundException() {
        PersonSaveLoadManager localpslTest = new PersonSaveLoadManager();
        assertThrows(FileNotFoundException.class, () -> localpslTest.load("pie die pie"));
    }

    @Test
    private void load_correctFile_allContentsCorrect() throws Exception {
        PersonSaveLoadManager localpslTest = new PersonSaveLoadManager();
        localpslTest.load("pslTest");
        assertEquals("Victor Chng", localpslTest.getName());
        assertEquals("Unknown", localpslTest.getGender());
        assertEquals(200, localpslTest.getOriginalWeight());
        assertEquals(100, localpslTest.getAge());
        assertEquals(300, localpslTest.getCurrentWeight());
        assertEquals(100, localpslTest.getTargetWeight());
        assertEquals(0, localpslTest.getFitnessLevel());
    }
}