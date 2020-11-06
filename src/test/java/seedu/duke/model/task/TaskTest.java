package seedu.duke.model.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TaskTest {

    @Test
    void task_newTask_checkMethodsNormalBehavior() {
        Task task = new Task(1, "Title", "Desc", "HIGH", false);

        assertFalse(task.getDone());
        assertEquals("Title", task.getTitle());
        assertEquals("Desc", task.getDescription());
        assertEquals("High priority", task.getPriority());
    }


}
