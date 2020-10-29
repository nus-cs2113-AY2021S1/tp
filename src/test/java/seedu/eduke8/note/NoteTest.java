package seedu.eduke8.note;

import org.junit.jupiter.api.Test;
import seedu.eduke8.exception.Eduke8Exception;
import seedu.eduke8.topic.Topic;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoteTest {

    private static final String TEST_NAME = "Note name";
    private static final String TEST = "Test";

    @Test
    void getsNoteDescription_returnsNoteDescription() throws Eduke8Exception {
        Note note = new Note(TEST_NAME, TEST);

        assertEquals(TEST_NAME, note.getDescription());
    }

    @Test
    void getsNoteName_returnsNoteName() throws Eduke8Exception {
        Note note = new Note(TEST_NAME, TEST);

        assertEquals(TEST, note.getNoteText());
    }
}
