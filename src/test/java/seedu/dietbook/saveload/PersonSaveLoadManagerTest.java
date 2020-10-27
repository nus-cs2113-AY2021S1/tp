package seedu.dietbook.saveload;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class PersonSaveLoadManagerTest {
    private static PersonSaveLoadManager pslTest;

    @BeforeEach
    private void setUp(){
        pslTest = new PersonSaveLoadManager();
        pslTest.setName("Victor Chng");
        pslTest.setActivityLevel(0);
        pslTest.setGender("UnKnown");
        pslTest.setAge(100);
        pslTest.setOriginalWeight(200);
        pslTest.setCurrentWeight(300);
        pslTest.setTargetWeight(100);
    }
}