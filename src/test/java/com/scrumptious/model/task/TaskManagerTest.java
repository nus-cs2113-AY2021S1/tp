package com.scrumptious.model.task;

import com.scrumptious.model.project.Project;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskManagerTest {

    @Test
    void getTask_emptyTaskList_noTaskTitle_returnsNullPointerException() {
        Project project = new Project(1, "Title", "Desc", 3, 3);
        assertThrows(NullPointerException.class, () -> project.getBacklog().getTask(1).getTitle());
    }

    @Test
    void taskManager_isEmpty_returnsNormalBehavior() {
        Project project = new Project(1, "Title", "Desc", 3, 3);
        assertTrue(project.getBacklog().isEmpty());
    }

}
