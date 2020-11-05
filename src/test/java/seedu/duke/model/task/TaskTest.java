package seedu.duke.model.task;

import org.junit.jupiter.api.Test;
import seedu.duke.model.project.Project;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
