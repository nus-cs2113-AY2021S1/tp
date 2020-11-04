package seedu.modtracker;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ModuleListTest {
    private static final String TEST_FILEPATH = "test/data/modlist.txt";
    Storage storage = new Storage(TEST_FILEPATH);
    ModuleList modulesTest = new ModuleList();
    TaskList taskList = new TaskList();


    @Test
    public void checkIfModuleValid_validModules_true() {
        assertTrue(modulesTest.checkIfModuleValid("CS2113", true));
        assertTrue(modulesTest.checkIfModuleValid("CS2113T", true));
        assertTrue(modulesTest.checkIfModuleValid("GER1000", true));
        assertTrue(modulesTest.checkIfModuleValid("GES1000T", true));
    }

    @Test
    public void checkIfExpStringValid_expWorkloadWithDiffDecimalPlace_validityOfExpWorkload() {
        assertTrue(modulesTest.checkIfExpStringValid("2", true));
        assertTrue(modulesTest.checkIfExpStringValid("2.", true));
        assertTrue(modulesTest.checkIfExpStringValid("2.0", true));
        assertFalse(modulesTest.checkIfExpStringValid("2.00", false));
        assertFalse(modulesTest.checkIfExpStringValid("2.00", true));
    }

    @Test
    public void checkIfExpTimeValid_expWorkloadWithDiffAmount_validityOfExpWorkload() {
        assertFalse(modulesTest.checkIfExpTimeValid(0, true));
        assertFalse(modulesTest.checkIfExpTimeValid(0.9, true));
        assertFalse(modulesTest.checkIfExpTimeValid(24.1, false));
        assertFalse(modulesTest.checkIfExpTimeValid(24.01, true));
        assertTrue(modulesTest.checkIfExpTimeValid(24.0, true));
        assertTrue(modulesTest.checkIfExpTimeValid(1, true));
    }

    @Test
    public void addExp_modWithExpTime_modWithExpTimeAdded() {
        modulesTest.deleteMod("deleteMod CS3000", false, storage);
        ArrayList<Module> testList = new ArrayList<>();
        Module testMod = new Module("CS3030", "4");
        testList.add(testMod);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        modulesTest.addExp("addExp CS3030 4", true, storage);
        String expected = "CS3030, Expected Workload: 4.0h is added." + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
        modulesTest.clear();
    }

    @Test
    public void addExp_modWithSameInitialExpTime_printErrorMessage() {
        modulesTest.addExp("addExp CS3030 4", false, storage);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        modulesTest.addExp("addExp CS3030 4", true, storage);
        String expected = "The expected workload is already 4.0h." + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
        modulesTest.clear();
    }

    @Test
    public void addExp_modWithDiffInitialExpTime_expTimeUpdated() {
        modulesTest.addExp("addExp CS3030 4", false, storage);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        modulesTest.addExp("addExp CS3030 8", true, storage);
        String expected = "Expected workload of CS3030 is changed to 8.0h." + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
        modulesTest.clear();
    }

    @Test
    public void addExp_modWithInvalidTimeFormat_printErrorMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        modulesTest.addExp("addExp CS3030 4,5", true, storage);
        String expected = "Please type addexp <module code> <expected workload> with expected workload "
            + "being a number between 1 and 24 with a maximum of 1 decimal place." + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
        modulesTest.clear();
    }

    @Test
    public void addModule_emptyModuleList_newModuleAdded() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expected = "CS5000 is added." + System.lineSeparator();
        modulesTest.addMod("addMod CS5000", true, storage);
        assertTrue(modulesTest.checkIfModuleExist("CS5000"));
        assertEquals(expected + System.lineSeparator(), outContent.toString());
        modulesTest.clear();
    }

    @Test
    public void addModule_invalidAddModInput_printErrorMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expected = "Please type addmod <module code>" + System.lineSeparator();
        modulesTest.addMod("addMod", true, storage);
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void addModule_invalidModuleSpacing_printErrorMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expectedStart = "The module code should have 2 - 3 characters, followed by "
                + "4 digits, followed by an optional character without any spacing." + System.lineSeparator();
        String expectedEnd = "The accepted module code is of the following forms: CG1111, "
                + "CS2113T, GER1000, GES1000T." + System.lineSeparator();
        modulesTest.addMod("addmod cs 1010", true, storage);
        assertEquals(expectedStart + expectedEnd + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void addModule_invalidModuleChar_printErrorMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expectedStart = "The module code should have 2 - 3 characters, followed by "
                + "4 digits, followed by an optional character without any spacing." + System.lineSeparator();
        String expectedEnd = "The accepted module code is of the following forms: CG1111, "
                + "CS2113T, GER1000, GES1000T." + System.lineSeparator();

        modulesTest.addMod("addmod cs1234567", true, storage);
        assertEquals(expectedStart + expectedEnd + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void checkIfModuleValid_invalidModule_false() {
        assertFalse(modulesTest.checkIfModuleValid("AAAAAA", true));
    }

    @Test
    public void deleteModule_removeModule_moduleRemoved() {
        modulesTest.addMod("addMod CS5000", false, storage);
        modulesTest.addMod("addMod CS5330", false, storage);
        taskList.addTask("addtask CS5000 dowork", false, storage);
        taskList.addTask("addtask CS5330 stuff", false, storage);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expected = "CS5000 is removed." + System.lineSeparator()
                + "All tasks under CS5000 are deleted." + System.lineSeparator();

        modulesTest.deleteMod("deleteMod CS5000", true, storage);
        assertFalse(modulesTest.checkIfModuleExist("CS5000"));
        assertEquals(expected + System.lineSeparator(), outContent.toString());
        modulesTest.clear();

    }

    @Test
    public void deleteModule_removeNonExistingModule_printErrorMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expected = "CS0000 does not exist." + System.lineSeparator();

        modulesTest.deleteMod("deleteMod CS0000", true, storage);
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void deleteModule_invalidDeleteModInput_printErrorMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expected = "Please type deletemod <module code>" + System.lineSeparator();
        modulesTest.deleteMod("deleteMod", true, storage);
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void deleteExp_moduleWithExpTime_moduleWithExpTimeRemoved() {
        modulesTest.addExp("addExp CS3030 5", false, storage);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        modulesTest.deleteExp("deleteExp CS3030", true, storage);
        String expected = "Expected Workload of CS3030 is removed." + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
        modulesTest.clear();
    }

    @Test
    public void deleteExp_noExpTime_printErrorMessage() {
        modulesTest.addExp("addExp CS3232 5", true, storage);
        modulesTest.deleteExp("deleteExp CS3232", false, storage);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        modulesTest.deleteExp("deleteExp CS3232", true, storage);
        String expected = "There is no input in the expected workload." + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void deleteExp_invalidCommand_printErrorMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        modulesTest.deleteExp("deleteExp", true, storage);
        String expected = "Please type deleteexp <module code>" + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void deleteExp_nonExistingModule_printErrorMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        modulesTest.deleteExp("deleteExp LM9999", true, storage);
        String expected = "LM9999 does not exist." + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void deleteTime_moduleWithActualTime_actualTimeRemoved() {
        modulesTest.addMod("addMod CS5555", true, storage);
        modulesTest.addTime("addTime CS5555 4 1", true, storage);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        modulesTest.deleteTime("deleteTime CS5555 1", true, storage);
        String expected = "Actual time of CS5555 of week 1 is removed." + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
        modulesTest.clear();
    }

    @Test
    public void deleteTime_noActualTime_printErrorMessage() {
        modulesTest.addExp("addexp BA1111 5", true, storage);
        modulesTest.deleteTime("deleteTime BA1111 1", false, storage);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        modulesTest.deleteTime("deleteTime BA1111 1", true, storage);
        String expected = "There is no input in the actual time." + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void deleteTime_invalidModule_printErrorMessages() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        modulesTest.deleteTime("deleteTime CS123 5", true, storage);
        String expectedStart = "The module code should have 2 - 3 characters, followed by "
                + "4 digits, followed by an optional character without any spacing." + System.lineSeparator();
        String expectedEnd = "The accepted module code is of the following forms: CG1111, "
                + "CS2113T, GER1000, GES1000T." + System.lineSeparator();
        assertEquals(expectedStart + expectedEnd + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void deleteTime_nonExistingModule_printErrorMessages() {
        modulesTest.deleteMod("deleteMod CS1234", false, storage);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expected = "CS1234 does not exist." + System.lineSeparator();
        modulesTest.deleteTime("deleteTime CS1234 4 1", true, storage);
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void deleteTime_weekLessThanOne_printErrorMessages() {
        modulesTest.addMod("addMod CS1234", true, storage);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        modulesTest.deleteTime("deleteTime CS1234 0", true, storage);
        String expected = "The week number should be between 1 and 13." + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void deleteTime_weekMoreThanThirteen_printErrorMessages() {
        modulesTest.addMod("addMod CS1234", true, storage);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        modulesTest.deleteTime("deleteTime CS1234 14", true, storage);
        String expected = "The week number should be between 1 and 13." + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void deleteTime_sameObject_true() {
        modulesTest.addMod("addMod CS1234", false, storage);
        modulesTest.addMod("addMod CS5555", false, storage);
        Module testMod1 = modulesTest.modList.get(1);
        Module testMod2 = modulesTest.modList.get(1);
        assertTrue(testMod1.equals(testMod2));
        modulesTest.clear();
    }

}