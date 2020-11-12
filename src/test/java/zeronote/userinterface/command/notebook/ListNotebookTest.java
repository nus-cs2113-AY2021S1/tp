package zeronote.userinterface.command.notebook;

import org.junit.jupiter.api.Test;
import zeronote.exceptions.IncorrectAppModeException;
import zeronote.exceptions.InvalidCommandException;
import zeronote.userinterface.AppMode;
import zeronote.userinterface.AppState;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//@@author longngng
class ListNotebookTest {
    @Test
    void execute_validInput_success() {
        AppState appState = new AppState();
        String input = "/s";

        ListCommandNotebookMode listCommandNotebookMode = new ListCommandNotebookMode(input, appState);
        appState.setAppMode(AppMode.NOTEBOOK_SHELF);
        assertEquals("/s", listCommandNotebookMode.getCommandParams());
    }

    @Test
    void execute_invalidInput_exceptionThrown() {
        AppState appState = new AppState();
        String input = " /d";

        ListCommandNotebookMode listCommandNotebookMode = new ListCommandNotebookMode(input, appState);
        appState.setAppMode(AppMode.NOTEBOOK_SHELF);
        try {
            listCommandNotebookMode.execute();
            fail();
        } catch (InvalidCommandException e) {
            assertEquals(" /d", e.problematicInput);
        } catch (IncorrectAppModeException e) {
            e.printStackTrace();
        }
    }
}
