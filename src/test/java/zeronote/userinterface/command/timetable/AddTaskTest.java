package zeronote.userinterface.command.timetable;

import org.junit.jupiter.api.Test;
import zeronote.exceptions.IncorrectDeadlineFormatException;
import zeronote.exceptions.TaskTitleException;
import zeronote.exceptions.TaskWrongFormatException;
import zeronote.exceptions.ZeroNoteException;
import zeronote.tasks.TaskList;
import zeronote.userinterface.AppMode;
import zeronote.userinterface.AppState;
import zeronote.userinterface.InputParser;
import zeronote.userinterface.command.CliCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author chuckiex3
public class AddTaskTest {
    @Test
    void addTask_dateFormat_expectException() {
        InputParser parser = new InputParser();
        String inputString = "/by18 Oct";
        assertThrows(IncorrectDeadlineFormatException.class, () -> {
            parser.parseDeadline(inputString);
        });
    }

    @Test
    void addTask_date_correctFormat() throws IncorrectDeadlineFormatException {
        InputParser parser = new InputParser();
        String inputString = "/by18-10-2020 1900";
        String expectedString = "18-10-2020 1900";
        assertEquals(expectedString, parser.parseDeadline(inputString));
    }

    @Test
    void addTask_emptyTask_expectException() throws ZeroNoteException {
        InputParser parser = new InputParser();
        AppState appState = new AppState();
        appState.setAppMode(AppMode.TIMETABLE);
        String inputString = "add /t /by18-10-2020 1900";
        String argument = "/t /by18-10-2020 1900";
        CliCommand command = parser.getCommandFromInput(inputString, appState);

        assertThrows(TaskTitleException.class, command::execute);
    }

    @Test
    void addTask_emptyEverything_expectException() throws ZeroNoteException {
        InputParser parser = new InputParser();
        AppState appState = new AppState();
        appState.setAppMode(AppMode.TIMETABLE);
        String inputString = "add /t /by";
        String argument = "/t /by";
        CliCommand command = parser.getCommandFromInput(inputString, appState);
        assertThrows(TaskTitleException.class, command::execute);
    }

    @Test
    void addTask_successfully() throws ZeroNoteException {
        InputParser parser = new InputParser();
        AppState appState = new AppState();
        appState.setAppMode(AppMode.TIMETABLE);
        String inputString = "add /ttask /by10-10-2020 1900";
        CliCommand command = parser.getCommandFromInput(inputString, appState);
        command.execute();
        TaskList tasks = appState.getTaskList();
        assertEquals(1, tasks.getNumberOfTasks());
    }

    //@@author NeilBaner
    @Test
    void addTask_noDeadlineDelimiter() {
        AppState currentAppState = new AppState();
        CliCommand command = new AddCommandTimetableMode("/t Test1 10-10-2020", currentAppState);
        assertThrows(TaskWrongFormatException.class, command::execute);
    }

    @Test
    void addTask_noTaskDelimiter() {
        AppState currentAppState = new AppState();
        CliCommand command = new AddCommandTimetableMode("Test1 /by 10-10-2020 1800", currentAppState);
        assertThrows(TaskWrongFormatException.class, command::execute);
    }
}
