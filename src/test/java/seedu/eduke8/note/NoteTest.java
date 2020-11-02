package seedu.eduke8.note;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoteTest {

    private static final String TEST_NAME = "Note name";
    private static final String TEST = "Test";

    Note note;

    NoteTest() {
        note = new Note(TEST_NAME, TEST);
    }

    @Test
    void getsNoteDescription_returnsNoteDescription() {
        assertEquals(TEST_NAME, note.getDescription());
    }

    @Test
    void getsNoteName_returnsNoteName() {
        assertEquals(TEST, note.getNoteText());
    }
}
