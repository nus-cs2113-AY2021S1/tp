package zeronote.userinterface.command.timetable;

import org.junit.jupiter.api.Test;
import zeronote.exceptions.IndexIncorrectFormatException;
import zeronote.exceptions.TaskIndexOutOfBoundsException;
import zeronote.exceptions.TaskListEmptyException;
import zeronote.tasks.Task;
import zeronote.tasks.TaskList;
import zeronote.userinterface.AppState;
import zeronote.userinterface.command.CliCommand;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author yAOwzers
public class MarkAsDoneTest {
    @Test
    void execute_MarkAsDoneTwo_IndexOutOfBound() {
        AppState appState = new AppState();

        String input = "/tTask1 /by12-10-2020 2252";
        Task t = new Task("Task 1","12-10-2020 2252");
        appState.getTaskList().addTask(t);

        assertThrows(TaskIndexOutOfBoundsException.class, () -> {
            appState.getTaskList().markAsDone(2);
        });
    }

    // @@author neilbaner
    @Test
    void markDone_positiveTest() {
        AppState appState = new AppState();
        TaskList current = appState.getTaskList();
        Task toAdd = new Task("Test", "10-10-2020 1800");
        current.addTask(toAdd);

        CliCommand done = new DoneCommandTimetableMode("1", appState);
        assertDoesNotThrow(done::execute);
        assertDoesNotThrow(() -> {
            boolean output = current.getTask(0).isDone();
            assertEquals(true, output);
        });
    }

    @Test
    void markDone_IndexOutOfBounds() {
        AppState appState = new AppState();
        TaskList current = appState.getTaskList();
        Task toAdd = new Task("Test", "10-10-2020 1800");
        current.addTask(toAdd);

        CliCommand done = new DoneCommandTimetableMode("2", appState);
        assertThrows(TaskIndexOutOfBoundsException.class, done::execute);
    }

    @Test
    void markDone_ListEmpty() {
        AppState appState = new AppState();

        CliCommand done = new DoneCommandTimetableMode("1", appState);
        assertThrows(TaskListEmptyException.class, done::execute);
    }

    @Test
    void markDone_IndexNotANumber() {
        AppState appState = new AppState();
        TaskList current = appState.getTaskList();
        Task toAdd = new Task("Test", "10-10-2020 1800");
        current.addTask(toAdd);

        CliCommand done = new DoneCommandTimetableMode("rekt lol", appState);
        assertThrows(IndexIncorrectFormatException.class, done::execute);
    }

    @Test
    void markDone_ShouldPrintPersonalMessage() {
        CliCommand done = new DoneCommandTimetableMode("1", new AppState());
        assertEquals(true, done.isTriggerAutoSave());
    }
}
