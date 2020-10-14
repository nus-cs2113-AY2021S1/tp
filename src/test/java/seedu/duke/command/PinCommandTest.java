package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import seedu.duke.data.notebook.Note;
import seedu.duke.data.notebook.Notebook;

class PinCommandTest {
    private Note notePinned;
    private Note noteNotPinned;

    private Notebook notebook;

    private static final String NOTE_CONTENT = "This is a test note.";
    private static final String NOTE1_TITLE = "TestNote1";
    private static final String NOTE2_TITLE = "TestNote2";

    @BeforeEach
    void setUp() {
        notePinned = new Note(NOTE1_TITLE, NOTE_CONTENT, true);
        noteNotPinned = new Note(NOTE2_TITLE, NOTE_CONTENT, false);

        notebook = new Notebook();

        notebook.addNote(notePinned);
        notebook.addNote(noteNotPinned);
    }

    @Test
    void execute() {
    }

    @Test
    void execute_inputIndex_NoteExists_PinsAndUnpinsNotes() {
        assertEquals(getExecutionStringInputIndex(notebook, 0),NOTE1_TITLE + " pinned: " + 'N');
        assertEquals(getExecutionStringInputIndex(notebook, 1),NOTE2_TITLE + " pinned: " + 'Y');

        assertTrue(notePinned.getPinned().equals("N"));
        assertTrue(noteNotPinned.getPinned().equals("Y"));
    }

    @Test
    void execute_inputTitle_NoteExists_PinsAndUnpinsNotes() {
        assertEquals(getExecutionStringInputTitle(notebook, NOTE1_TITLE),NOTE1_TITLE + " pinned: " + 'N');
        assertEquals(getExecutionStringInputTitle(notebook, NOTE2_TITLE),NOTE2_TITLE + " pinned: " + 'Y');

        assertTrue(notePinned.getPinned().equals("N"));
        assertTrue(noteNotPinned.getPinned().equals("Y"));
    }

    @Test
    void execute_inputIndex_NoteDoesNotExists_returnsUnsuccessful() {
        assertEquals(getExecutionStringInputIndex(notebook, 5),PinCommand.COMMAND_UNSUCCESSFUL_MESSAGE);
        assertEquals(getExecutionStringInputIndex(notebook, 3),PinCommand.COMMAND_UNSUCCESSFUL_MESSAGE);
    }

    @Test
    void execute_inputTitle_NoteDoesNotExists_returnsUnsuccessful() {
        assertEquals(getExecutionStringInputTitle(notebook, "Title"),PinCommand.COMMAND_UNSUCCESSFUL_MESSAGE);
        assertEquals(getExecutionStringInputTitle(notebook, "Random"),PinCommand.COMMAND_UNSUCCESSFUL_MESSAGE);
    }

    private String getExecutionStringInputIndex(Notebook notebook, int index) {
        PinCommand pinCommand = new PinCommand(index);
        pinCommand.setData(notebook, null, null, null);

        return pinCommand.execute();
    }

    private String getExecutionStringInputTitle(Notebook notebook, String title) {
        PinCommand pinCommand = new PinCommand(title);
        pinCommand.setData(notebook, null, null, null);

        return pinCommand.execute();
    }
}
