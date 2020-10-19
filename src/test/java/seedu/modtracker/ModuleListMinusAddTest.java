package seedu.modtracker;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;



class ModuleListMinusAddTest {
    private static final String TEST_FILEPATH = "test/data/modlist.txt";
    Storage storage = new Storage(TEST_FILEPATH);
    ModuleList modulesTest = new ModuleList();


    @Test
    public void addTime_ModuleWithoutActualWorkload_ModuleWithWorkloadAdded() {
        modulesTest.addMod("addmod CS3030", true, storage);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        modulesTest.addTime("addtime CS3030 4 4", true, storage);
        String expected = "4 hours are added to CS3030" + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void minusTime_ModuleWithActualWorkload_ModuleWithWorkloadSubtracted() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        modulesTest.minusTime("minustime CS3030 2 4", true, storage);
        String expected = "2 hours are removed from CS3030" + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }


}