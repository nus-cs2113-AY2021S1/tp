package seedu.duke.model.task;

import org.junit.jupiter.api.Test;
import seedu.duke.model.project.Project;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskManagerTest {

    @Test
    void getTask_emptyTaskList_noTaskTitle_returnsNullPointerException() {
        Project project = new Project(1, "Title", "Desc", 3, 3);
        assertThrows(NullPointerException.class, () -> project.getTaskList().getTask(1).getTitle());
    }

    @Test
    void taskManager_isEmpty_returnsNormalBehavior() {
        Project project = new Project(1, "Title", "Desc", 3, 3);
        assertTrue(project.getTaskList().isEmpty());
    }

}
