package seedu.eduke8.note;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NoteTest {

    private static final String TEST_NAME = "Note name";
    private static final String TEST = "Test";

    Note note;

    NoteTest() {
        note = new Note(TEST_NAME, TEST);
    }

    @Test
    void noteConstructor_nullDescriptionArgument_expectsAssertionError() {
        assertThrows(AssertionError.class, () -> {
            new Note(null, null);
        });
    }

    @Test
    void getsNoteDescription_returnsNoteDescription() {
        assertEquals(TEST_NAME, note.getDescription());
    }

    @Test
    void getsNoteText_returnsNoteText() {
        assertEquals(TEST, note.getNoteText());
    }
}
