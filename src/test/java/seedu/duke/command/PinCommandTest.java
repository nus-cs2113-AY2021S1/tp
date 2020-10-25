package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.duke.command.PinCommand.COMMAND_UNSUCCESSFUL_MESSAGE;

import seedu.duke.data.notebook.Note;
import seedu.duke.data.notebook.Notebook;
import seedu.duke.ui.Formatter;

import java.util.ArrayList;

class PinCommandTest {
    private Note notePinned;
    private Note noteNotPinned;

    private Notebook notebook;

    private static final ArrayList<String> NOTE_CONTENT = new ArrayList<>();

    private static final String NOTE1_TITLE = "TestNote1";
    private static final String NOTE2_TITLE = "TestNote2";

    private static final String UNSUCCESFUL_MESSAGE = Formatter.formatString(COMMAND_UNSUCCESSFUL_MESSAGE);

    @BeforeEach
    void setUp() {
        NOTE_CONTENT.add("This is a test note.");
        notePinned = new Note(NOTE1_TITLE, NOTE_CONTENT, true, false);
        noteNotPinned = new Note(NOTE2_TITLE, NOTE_CONTENT, false, false);

        notebook = new Notebook();

        notebook.addNote(notePinned);
        notebook.addNote(noteNotPinned);
    }

    @Test
    void execute() {
    }

    @Test
    void execute_inputIndex_NoteExists_PinsAndUnpinsNotes() {
        String note1Expected = Formatter.formatString(NOTE1_TITLE + " pinned: " + 'N');
        String note2Expected = Formatter.formatString(NOTE2_TITLE + " pinned: " + 'Y');

        assertEquals(note1Expected, getExecutionStringInputIndex(notebook, 0));
        assertEquals(note2Expected, getExecutionStringInputIndex(notebook, 1));

        assertTrue(notePinned.getPinnedString().equals("N"));
        assertTrue(noteNotPinned.getPinnedString().equals("Y"));
    }

    @Test
    void execute_inputTitle_NoteExists_PinsAndUnpinsNotes() {
        String note1Expected = Formatter.formatString(NOTE1_TITLE + " pinned: " + 'N');
        String note2Expected = Formatter.formatString(NOTE2_TITLE + " pinned: " + 'Y');

        assertEquals(note1Expected, getExecutionStringInputTitle(notebook, NOTE1_TITLE));
        assertEquals(note2Expected, getExecutionStringInputTitle(notebook, NOTE2_TITLE));

        assertTrue(notePinned.getPinnedString().equals("N"));
        assertTrue(noteNotPinned.getPinnedString().equals("Y"));
    }

    @Test
    void execute_inputIndex_NoteDoesNotExists_returnsUnsuccessful() {
        String expected = Formatter.formatString(COMMAND_UNSUCCESSFUL_MESSAGE);

        assertEquals(expected, getExecutionStringInputIndex(notebook, 5));
        assertEquals(expected, getExecutionStringInputIndex(notebook, 3));
    }

    @Test
    void execute_inputTitle_NoteDoesNotExists_returnsUnsuccessful() {
        String expected = Formatter.formatString(COMMAND_UNSUCCESSFUL_MESSAGE);

        assertEquals(expected, getExecutionStringInputTitle(notebook, "Title"));
        assertEquals(expected, getExecutionStringInputTitle(notebook, "Random"));
    }

    private String getExecutionStringInputIndex(Notebook notebook, int index) {
        PinCommand pinCommand = new PinCommand(index);
        pinCommand.setData(notebook, null, null, null);

        return pinCommand.execute();
    }

    private String getExecutionStringInputTitle(Notebook notebook, String title) {
        PinCommand pinCommand = new PinCommand(title);
        pinCommand.setData(notebook, null,null, null);

        return pinCommand.execute();
    }
}
