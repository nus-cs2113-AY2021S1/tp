package seedu.eduke8.note;

import org.junit.jupiter.api.Test;
import seedu.eduke8.Eduke8Test;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.exception.Eduke8Exception;
import seedu.eduke8.question.QuestionList;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NoteListTest extends Eduke8Test {

    private static final int DEFAULT_NOTE_COUNT = 2;

    @Test
    void getCount_TwoNotes_returnsCountOfTwo() {
        NoteList noteList = createTestNoteList();

        assertEquals(DEFAULT_NOTE_COUNT, noteList.getCount());
    }

    @Test
    void add_thirdNoteToNoteList_returnsCountOfThree() {
        NoteList noteList = createTestNoteList();
        Note note3 = new Note(NOTE_THREE, NOTE_DESCRIPTION);
        noteList.add(note3);

        assertEquals(DEFAULT_NOTE_COUNT + 1, noteList.getCount());
    }

    @Test
    void delete_thirdNoteToNoteList_returnsCountOfOne() {
        NoteList noteList = createTestNoteList();
        noteList.delete(1);

        assertEquals(DEFAULT_NOTE_COUNT - 1, noteList.getCount());
    }

    @Test
    void getCount_getInnerList_returnsCountOfTwo() {
        NoteList noteList = createTestNoteList();
        ArrayList<Displayable> innerList = noteList.getInnerList();

        assertEquals(DEFAULT_NOTE_COUNT, innerList.size());
    }

    @Test
    void getNote_returnsNoteDescription() {
        NoteList noteList = createTestNoteList();

        assertEquals(noteList.get(1).getDescription(), NOTE_TWO);
    }
}
