package seedu.ezmanager.commands.task;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TaskTest {

    /**
    static Task task;

    @BeforeAll
    static void createTask() {
        task = new Task("task1", LocalDate.parse("2020-07-14"));
    }

    @Test
    void testToString() {
        assertEquals("task1 | 14/07/2020", task.toString());
    }

    @Test
    void equals_sameTask_true() {
        Task sameTask = new Task("task1", LocalDate.parse("2020-07-14"));
        assertTrue(sameTask.equals(task));
    }

    @Test
    void equals_diffDescription_false() {
        Task diffTask = new Task("task2", LocalDate.parse("2020-07-14"));
        assertFalse(diffTask.equals(task));
    }

    @Test
    void equals_diffDeadline_false() {
        Task diffTask = new Task("task1", LocalDate.parse("2020-04-14"));
        assertFalse(diffTask.equals(task));
    }
    **/
}