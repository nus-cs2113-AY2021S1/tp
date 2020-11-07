package seedu.duke.task;

import org.junit.jupiter.api.Test;
import seedu.duke.model.item.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskTest {
    private static final String TEST_DESCRIPTION = "test description";

    @Test
    void getDescription_inputDescription_returnsCorrectDescription() {
        Task todo = new Task(TEST_DESCRIPTION);
        assertEquals(TEST_DESCRIPTION, todo.getDescription());
    }

    @Test
    void getIsDone_isDone_returnsFalseByDefault() {
        Task todo = new Task(TEST_DESCRIPTION);
        assertFalse(todo.getIsDone());
    }

    @Test
    void markAsDone_setIsDone_todoSetAsDone() {
        Task todo = new Task(TEST_DESCRIPTION);
        assertFalse(todo.getIsDone());
        todo.markAsDone();
        assertTrue(todo.getIsDone());
    }

    @Test
    void toFile_getToFile_returnsCorrectString() {
        Task todo = new Task(TEST_DESCRIPTION);
        String fileString = todo.toFile();
        String expectedString = "T | 0 | " + TEST_DESCRIPTION + " | 0 |  | ";
        assertEquals(expectedString, fileString);
    }

    @Test
    void testToString_toString_returnsCorrectString() {
        Task todo = new Task(TEST_DESCRIPTION);
        String todoString = todo.toString();
        String expectedString = "[T][N] " + TEST_DESCRIPTION + " (p:0)";
        assertEquals(expectedString, todoString);
    }

    @Test
    void todo_noInputPriority_setsDefaultPriority() {
        Task todo = new Task(TEST_DESCRIPTION);
        assertEquals(0, todo.getPriority());
    }

    @Test
    void setPriority_validPriority_setsCorrectPriority() {
        Task todo = new Task(TEST_DESCRIPTION);
        todo.setPriority(5);
        assertEquals(5, todo.getPriority());
    }
}
