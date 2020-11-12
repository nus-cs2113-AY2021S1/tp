package zeronote.userinterface.command.timetable;

import org.junit.jupiter.api.Test;
import zeronote.exceptions.InvalidCommandException;
import zeronote.tasks.Task;
import zeronote.tasks.TaskList;
import zeronote.userinterface.AppState;
import zeronote.userinterface.command.CliCommand;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// @@author longngng
class ListTaskTest {

    @Test
    void execute_invalidInput_exceptionThrown() {
        AppState appState = new AppState();
        String input = "list /d";

        ListCommandTimetableMode listCommandTimetableMode = new ListCommandTimetableMode(input, appState);
        try {
            listCommandTimetableMode.execute();
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("list /d", e.problematicInput);
        }
    }
}