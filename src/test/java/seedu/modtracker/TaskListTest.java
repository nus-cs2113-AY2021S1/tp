package seedu.modtracker;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private static final String TEST_FILEPATH = "test/data/tasklist.txt";
    Storage storage = new Storage(TEST_FILEPATH);
    ModuleList moduleTest = new ModuleList();
    TaskList taskList = new TaskList();

    @Test
    public void addTask_nonExistingModule_expectTaskNotAdded() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        taskList.addTask("addtask CS2101 study", true, null);
        String expected = "CS2101 does not exist." + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void addTask_invalidModule_expectTaskNotAdded() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        taskList.addTask("addtask cs21 study", true, null);
        String expectedStart = "The module code should have 2 - 3 characters, followed by "
                + "4 digits, followed by an optional character without any spacing." + System.lineSeparator();
        String expectedEnd = "The accepted module code is of the following forms: CG1111, "
                + "CS2113T, GER1000, GES1000T." + System.lineSeparator();
        assertEquals(expectedStart + expectedEnd + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void addTask_existingModule_expectTaskAdded() {
        TaskList.tasks.clear();
        ModuleList.modList.clear();

        moduleTest.addMod("addmod CG1111", true, storage);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        taskList.addTask("addtask cg1111 study", true, storage);
        String expected = "Got it. I've added this task under CG1111:" + System.lineSeparator()
                + "[X] [CG1111] study" + System.lineSeparator()
                + "Now you have 1 task in the list." + System.lineSeparator();

        assertEquals(expected + System.lineSeparator(), outContent.toString());

        TaskList.tasks.clear();
        ModuleList.modList.clear();
    }

    @Test
    public void setDone_taskNumberOutOfBounds_expectInvalidTask() {
        TaskList.tasks.clear();
        ModuleList.modList.clear();

        moduleTest.addMod("addmod CG1111", true, storage);
        taskList.addTask("addtask cg1111 study", true, storage);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        taskList.setDone("done 3", true, storage);

        String expected = "Invalid task number." + System.lineSeparator()
                + "Enter a task number from 1 to 1." + System.lineSeparator();

        assertEquals(expected + System.lineSeparator(), outContent.toString());

        TaskList.tasks.clear();
        ModuleList.modList.clear();
    }

    @Test
    public void setDone_validTaskNumber_expectTaskMarkedDone() {
        TaskList.tasks.clear();
        ModuleList.modList.clear();

        moduleTest.addMod("addmod CG1111", true, storage);
        taskList.addTask("addtask cg1111 study", true, storage);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        taskList.setDone("done 1", true, storage);
        String expected = TaskList.MARKED_DONE + System.lineSeparator()
                + "[/] [CG1111] study" + System.lineSeparator();

        assertEquals(expected + System.lineSeparator(), outContent.toString());

        TaskList.tasks.clear();
        ModuleList.modList.clear();
    }

    @Test
    public void setDone_taskMarkedDone_expectTaskMarkedDonePreviously() {
        TaskList.tasks.clear();
        ModuleList.modList.clear();

        moduleTest.addMod("addmod CG1111", true, storage);
        taskList.addTask("addtask cg1111 study", true, storage);
        taskList.setDone("done 1", true, storage);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        taskList.setDone("done 1", true, storage);
        String expected = TaskList.MARKED_DONE_PREVIOUSLY + System.lineSeparator();

        assertEquals(expected + System.lineSeparator(), outContent.toString());

        TaskList.tasks.clear();
        ModuleList.modList.clear();
    }

    @Test
    public void deleteTasks_taskNumberOutOfBounds_expectInvalidTask() {
        TaskList.tasks.clear();
        ModuleList.modList.clear();

        moduleTest.addMod("addmod CG1111", true, storage);
        taskList.addTask("addtask cg1111 study", true, storage);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        taskList.deleteTasks("deletetask 3", true, storage);
        String expected = "Invalid task number." + System.lineSeparator()
                + "Enter a task number from 1 to 1." + System.lineSeparator();

        assertEquals(expected + System.lineSeparator(), outContent.toString());

        TaskList.tasks.clear();
        ModuleList.modList.clear();
    }

    @Test
    public void deleteTasks_validTaskNumber_expectTaskDeleted() {
        TaskList.tasks.clear();
        ModuleList.modList.clear();

        moduleTest.addMod("addmod CG1111", true, storage);
        taskList.addTask("addtask cg1111 study", true, storage);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        taskList.deleteTasks("deletetask 1", true, storage);
        String expected = "Noted. I've removed this task:" + System.lineSeparator()
                + "[X] [CG1111] study" + System.lineSeparator()
                + "You currently have no task :-)" + System.lineSeparator();

        assertEquals(expected + System.lineSeparator(), outContent.toString());

        TaskList.tasks.clear();
        ModuleList.modList.clear();
    }


}
