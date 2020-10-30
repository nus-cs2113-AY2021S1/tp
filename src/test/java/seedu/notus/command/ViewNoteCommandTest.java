package seedu.notus.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.notus.data.notebook.Note;
import seedu.notus.data.notebook.Notebook;
import seedu.notus.data.tag.Tag;
import seedu.notus.ui.Formatter;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.notus.util.CommandMessage.NOTE_DOES_NOT_EXIST_MESSAGE;
import static seedu.notus.util.CommandMessage.VIEW_NOTE_SUCCESSFUL_MESSAGE;

//@@author prachi2023
class ViewNoteCommandTest {
    private Note noteWithoutTags;
    private Note noteWithTags;

    private Notebook notebook;

    private static final ArrayList<String> NOTE1_CONTENT = new ArrayList<>();

    private static final ArrayList<String> NOTE2_CONTENT = new ArrayList<>();

    private static final String NOTE1_TITLE = "TestNote1";
    private static final String NOTE2_TITLE = "TestNote2";
    private Tag tag1 = new Tag("tag1", "PURPLE");
    private Tag tag2 = new Tag("tag2", "RED");

    private ArrayList<Tag> tags = new ArrayList<>();

    @BeforeEach
    void setUp() {
        NOTE1_CONTENT.add("This is a test note");

        NOTE2_CONTENT.add("This is a test note");
        NOTE2_CONTENT.add("With some lines");
        NOTE2_CONTENT.add("");
        NOTE2_CONTENT.add("This is the end");

        tags.add(tag1);
        tags.add(tag2);

        noteWithoutTags = new Note(NOTE1_TITLE, NOTE1_CONTENT, true, false);
        noteWithTags = new Note(NOTE2_TITLE, NOTE2_CONTENT, false, false, tags);

        notebook = new Notebook();

        notebook.addNote(noteWithoutTags);
        notebook.addNote(noteWithTags);
    }

    @Test
    void execute_inputIndex_NoteExists_returnsContent() {
        String note1ExpectedOutput = Formatter.formatNote(VIEW_NOTE_SUCCESSFUL_MESSAGE, noteWithoutTags);
        String note2ExpectedOutput = Formatter.formatNote(VIEW_NOTE_SUCCESSFUL_MESSAGE, noteWithTags);

        assertEquals(note1ExpectedOutput, getExecutionStringInputIndex(notebook, 0));
        assertEquals(note2ExpectedOutput, getExecutionStringInputIndex(notebook, 1));
    }

    @Test
    void execute_inputTitle_NoteExists_returnsContent() {
        String note1ExpectedOutput = Formatter.formatNote(VIEW_NOTE_SUCCESSFUL_MESSAGE, noteWithoutTags);
        String note2ExpectedOutput = Formatter.formatNote(VIEW_NOTE_SUCCESSFUL_MESSAGE, noteWithTags);

        assertEquals(note1ExpectedOutput, getExecutionStringInputTitle(notebook, NOTE1_TITLE));
        assertEquals(note2ExpectedOutput, getExecutionStringInputTitle(notebook, NOTE2_TITLE));
    }

    @Test
    void execute_inputIndex_NoteDoesNotExists_returnsUnsuccessful() {

        String unsuccessfulMessage = Formatter.formatString(NOTE_DOES_NOT_EXIST_MESSAGE);

        assertEquals(unsuccessfulMessage, getExecutionStringInputIndex(notebook, 5));
        assertEquals(unsuccessfulMessage, getExecutionStringInputIndex(notebook, 3));
    }

    @Test
    void execute_inputTitle_NoteDoesNotExists_returnsUnsuccessful() {

        String unsuccessfulMessage = Formatter.formatString(NOTE_DOES_NOT_EXIST_MESSAGE);

        assertEquals(unsuccessfulMessage, getExecutionStringInputTitle(notebook, "Title"));
        assertEquals(unsuccessfulMessage, getExecutionStringInputTitle(notebook, "Random"));
    }

    private String getExecutionStringInputIndex(Notebook notebook, int index) {
        ViewNoteCommand viewNoteCommand = new ViewNoteCommand(index);
        viewNoteCommand.setData(notebook, null, null, null);

        return viewNoteCommand.execute();
    }

    private String getExecutionStringInputTitle(Notebook notebook, String title) {
        ViewNoteCommand viewNoteCommand = new ViewNoteCommand(title);
        viewNoteCommand.setData(notebook, null, null, null);

        return viewNoteCommand.execute();
    }
}
