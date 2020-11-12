package zeronote.userinterface.command.timetable;

import org.junit.jupiter.api.Test;
import zeronote.exceptions.InvalidTagException;
import zeronote.tasks.Task;
import zeronote.userinterface.AppState;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@author Lusi711
class FindTaskTest {

    @Test
    void execute_keywordInput_noneFound() {
        AppState appState = new AppState();
        Task t = new Task("Task 1", "08-11-2020 1456");
        appState.getTaskList().addTask(t);
        FindCommandTimetableMode f = new FindCommandTimetableMode("test", "", appState);
        f.execute();
        assertEquals(f.getTasksFound().size(), 0);
    }

    @Test
    void execute_keywordInput_taskFound() {
        AppState appState = new AppState();
        Task t = new Task("Task 1", "03-11-2020 1112");
        appState.getTaskList().addTask(t);

        FindCommandTimetableMode f = new FindCommandTimetableMode("task", "", appState);
        f.execute();
        assertEquals(f.getTasksFound().size(), 1);
    }

    @Test
    void execute_tagInput_noneFound() {
        AppState appState = new AppState();
        Task t = new Task("Task 1", "03-11-2020 1112");
        try {
            t.setTag("test");
        } catch (InvalidTagException ite) {
            ite.printErrorMessage();
        }
        appState.getTaskList().addTask(t);

        FindCommandTimetableMode f = new FindCommandTimetableMode("", "task", appState);
        f.execute();
        assertEquals(f.getTasksFound().size(), 0);
    }

    @Test
    void execute_tagInput_taskFound() {
        AppState appState = new AppState();
        Task t = new Task("Task 1", "03-11-2020 1112");
        try {
            t.setTag("test");
        } catch (InvalidTagException ite) {
            ite.printErrorMessage();
        }
        appState.getTaskList().addTask(t);

        FindCommandTimetableMode f = new FindCommandTimetableMode("", "test", appState);
        f.execute();
        assertEquals(f.getTasksFound().size(), 1);
    }
}