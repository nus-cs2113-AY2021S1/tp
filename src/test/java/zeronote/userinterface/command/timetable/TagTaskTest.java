package zeronote.userinterface.command.timetable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import zeronote.tasks.Task;
import zeronote.userinterface.AppState;
import zeronote.userinterface.command.notebook.TagCommandNotebookMode;

//@@author Lusi711
class TagTaskTest {

    @Test
    void execute_tagExistingTask_tagSet() {

        assertDoesNotThrow(() -> {
            AppState appState = new AppState();
            Task task = new Task("Task 1", "05-11-2020 1847");
            appState.getTaskList().addTask(task);
            TagCommandTimetableMode t = new TagCommandTimetableMode(0, "test", appState);
            t.execute();
            task = appState.getTaskList().getTask(0);
            assertEquals("test", task.getTag());
        });

    }

    @Test
    void execute_invalidIndex_noTagSet() {
        AppState appState = new AppState();
        assertDoesNotThrow(() -> {
            Task task = new Task("Task 1", "05-11-2020 1847");
            appState.getTaskList().addTask(task);
            TagCommandTimetableMode t = new TagCommandTimetableMode(-1, "test", appState);
            t.execute();
            task = appState.getTaskList().getTask(0);
            assertEquals("", task.getTag());
        });

    }

    @Test
    void isTriggerAutoSave() {
        AppState appState = new AppState();
        TagCommandNotebookMode t = new TagCommandNotebookMode("", appState);
        assertTrue(t.isTriggerAutoSave());
    }
}