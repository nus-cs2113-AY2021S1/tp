package seedu.duke.todo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoTest {
    @Test
    public void toStringTest() {
        String taskName = "read Chapter 2";
        String deadline = "Tuesday night";
        ToDo toDo = new ToDo(taskName, deadline);
        assertEquals("[x] read Chapter 2 (by: Tuesday night)", toDo.toString());
    }
}