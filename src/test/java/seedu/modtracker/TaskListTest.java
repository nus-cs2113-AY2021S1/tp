package seedu.modtracker;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TaskListTest {
    TaskList taskList = new TaskList();
    ArrayList<Task> emptyList = new ArrayList<>();

    @Test
    public void addTask_nonExistingModule_expectTaskNotAdded() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        taskList.addTask("addtask CS2101 study");
        String expected = "CS2101 does not exist." + System.lineSeparator();
        assertArrayEquals(emptyList.toArray(), taskList.getTaskData().toArray());
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void addTask_invalidModule_expectTaskNotAdded() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        taskList.addTask("addtask cs21 study");
        String expected = "Please check module code again. The module code should have "
                + "6 - 8 characters without any spacing." + System.lineSeparator();
        assertArrayEquals(emptyList.toArray(), taskList.getTaskData().toArray());
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

}
