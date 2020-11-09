package seedu.quotesify.todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoTest {
    private ToDo toDo;
    private String taskName = "Return Harry Potter";
    private String taskDeadline = "2020-11-11";

    @BeforeEach
    void setUp() {
        toDo = new ToDo(taskName, taskDeadline);
    }

    @Test
    public void toStringTestBeforeFormatting() {
        String taskDetails = "[x] Return Harry Potter (by: 2020-11-11)";
        assertEquals(taskDetails, toDo.toString());
    }

    @Test
    public void toStringTestWithFormatting() {
        String taskDetails = "[x] Return Harry Potter (by: Nov 11 2020, WEDNESDAY)";
        toDo.updateDateFormat();
        assertEquals(taskDetails, toDo.toString());
    }

    @Test
    public void toStringTestWithDone() {
        String taskDetails = "[v] Return Harry Potter (by: Nov 11 2020, WEDNESDAY)";
        toDo.updateDateFormat();
        toDo.setDone(true);

        assertEquals(taskDetails, toDo.toString());
    }
}