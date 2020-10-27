package seedu.modtracker;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ModuleListMinusAddTest {
    private final ModView view = new ModView();
    private static final String TEST_FILEPATH = "test/data/modlist.txt";
    Storage storage = new Storage(TEST_FILEPATH);
    ModuleList modulesTest = new ModuleList();


    @Test
    @Order(1)
    public void addTime_AddOnlyAnHour_ModuleWithoutActualWorkload_ModuleWithWorkloadAdded() {
        modulesTest.addMod("addmod CS3030", true, storage);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        modulesTest.addTime("addtime CS3030 1 4", true, storage);
        String expected = "1 hour has been added to CS3030." + System.lineSeparator()
                + "1.0 hours have been spent on this module in week 4." + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }


    @Test
    @Order(2)
    public void addTime_ModuleWithoutActualWorkload_ModuleWithWorkloadAdded() {
        modulesTest.addMod("addmod CS3030", true, storage);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        modulesTest.addTime("addtime CS3030 4 4", true, storage);
        String expected = "4 hours have been added to CS3030." + System.lineSeparator()
                + "5.0 hours have been spent on this module in week 4." + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }


    @Test
    @Order(3)
    public void minusTime_ModuleWithActualWorkload_ModuleWithWorkloadSubtracted() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        modulesTest.minusTime("minustime CS3030 2 4", true, storage);
        String expected = "2 hours have been removed from CS3030." + System.lineSeparator()
                + "3.0 hours have been spent on this module in week 4." + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    @Order(4)
    public void minusTime_MinusOnlyAnHour_ModuleWithActualWorkload_ModuleWithWorkloadSubtracted() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        modulesTest.minusTime("minustime CS3030 1 4", true, storage);
        String expected = "1 hour has been removed from CS3030." + System.lineSeparator()
                + "2.0 hours have been spent on this module in week 4." + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    @Order(5)
    public void minusTime_CheckIfHoursExceed() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        modulesTest.minusTime("minustime CS3030 5 4", true, storage);
        String expected = "Sorry you are trying to remove too many hours." + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }


    @Test
    @Order(6)
    public void editTime_ModuleWithActualWorkload() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        modulesTest.editTime("edittime CS3030 5 4", true, storage);
        String expected = "5 hours is the new actual workload for the module CS3030." + System.lineSeparator()
                + "5.0 hours have been spent on this module in week 4." + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    @Order(7)
    public void viewModule_checkIfTimeIsEditedCorrectly() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        view.printAllModuleInformation(modulesTest, 4);
        String expected = ModView.FIRST_PART_OF_BORDER + ModView.SECOND_PART_OF_BORDER
                + ModView.FIRST_PART_OF_HEADER + ModView.SECOND_PART_OF_HEADER
                + ModView.FIRST_PART_OF_BORDER + ModView.SECOND_PART_OF_BORDER
                + "|  04  | CS3030 | No Input |    5.0   |\n"
                + ModView.FIRST_PART_OF_BORDER + ModView.SECOND_PART_OF_BORDER;
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }


}