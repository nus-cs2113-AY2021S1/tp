package seedu.eduke8.note;

import org.junit.jupiter.api.Test;
import seedu.eduke8.exception.Eduke8Exception;
import seedu.eduke8.topic.Topic;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoteTest {

    private static final String NOTE_TEST = "Test Note";

    @Test
    void getsTopicDescription_topicDescription_returnsTopicDescription() throws Eduke8Exception {
        String input = NOTE_TEST;
        Note note = new Note(input);

        assertEquals(input, note.getDescription());
    }
}
