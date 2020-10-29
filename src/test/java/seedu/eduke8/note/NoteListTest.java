package seedu.eduke8.note;

import org.junit.jupiter.api.Test;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.exception.Eduke8Exception;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoteListTest {

    private static final String NOTE_ONE = "First Note";
    private static final String NOTE_TWO = "Second Note";
    private static final String NOTE_THREE = "Third Note";
    private static final String DESCRIPTION = "Test";
    private static final int DEFAULT_NOTE_COUNT = 2;

    @Test
    void getCount_TwoNotes_returnsCountOfTwo() throws Eduke8Exception {
        NoteList noteList = createTestNoteList();

        assertEquals(DEFAULT_NOTE_COUNT, noteList.getCount());
    }

    @Test
    void add_thirdNoteToNoteList_returnsCountOfThree() throws Eduke8Exception {
        NoteList noteList = createTestNoteList();
        Note note3 = new Note(NOTE_THREE, DESCRIPTION);
        noteList.add(note3);

        assertEquals(DEFAULT_NOTE_COUNT + 1, noteList.getCount());
    }

    @Test
    void delete_thirdNoteToNoteList_returnsCountOfOne() throws Eduke8Exception {
        NoteList noteList = createTestNoteList();
        noteList.delete(1);

        assertEquals(DEFAULT_NOTE_COUNT - 1, noteList.getCount());
    }

    private NoteList createTestNoteList() throws Eduke8Exception {
        Note note1 = new Note(NOTE_ONE, DESCRIPTION);
        Note note2 = new Note(NOTE_TWO, DESCRIPTION);

        ArrayList<Displayable> noteArrayList = new ArrayList<>(
                Arrays.asList(note1, note2)
        );

        return new NoteList(noteArrayList);
    }
}
