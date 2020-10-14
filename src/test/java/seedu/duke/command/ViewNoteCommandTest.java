package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import seedu.duke.data.notebook.Note;
import seedu.duke.data.notebook.Notebook;
import seedu.duke.data.notebook.Tag;
import seedu.duke.ui.InterfaceManager;

import java.util.ArrayList;

class ViewNoteCommandTest {
    private Note noteWithoutTags;
    private Note noteWithTags;

    private Notebook notebook;

    private static final String NOTE1_CONTENT = "This is a test note";
    private static final String NOTE2_CONTENT = "This is a test note"
                                                + "With some lines"
                                                + "  \n"
                                                + "This is the end";

    private static final String NOTE1_TITLE = "TestNote1";
    private static final String NOTE2_TITLE = "TestNote2";
    private Tag tag1 = new Tag("tag1", "PURPLE");
    private Tag tag2 = new Tag("tag2", "RED");

    private ArrayList<Tag> tags = new ArrayList<>();

    @BeforeEach
    void setUp() {
        tags.add(tag1);
        tags.add(tag2);

        noteWithoutTags = new Note(NOTE1_TITLE, NOTE1_CONTENT, true);
        noteWithTags = new Note(NOTE2_TITLE, NOTE2_CONTENT, false, tags);

        notebook = new Notebook();

        notebook.addNote(noteWithoutTags);
        notebook.addNote(noteWithTags);
    }

    @Test
    void execute_inputIndex_NoteExists_returnsContent() {
        String tagsName = "";

        for (Tag t : tags) {
            tagsName = tagsName.concat(t.toString());
        }

        String note1ExpectedOutput = NOTE1_TITLE + " " + InterfaceManager.LS + NOTE1_CONTENT;
        String note2ExpectedOutput = NOTE2_TITLE + " " + tagsName + InterfaceManager.LS + NOTE2_CONTENT;

        assertEquals(getExecutionStringInputIndex(notebook, 0),note1ExpectedOutput);
        assertEquals(getExecutionStringInputIndex(notebook, 1),note2ExpectedOutput);
    }

    @Test
    void execute_inputTitle_NoteExists_returnsContent() {
        String tagsName = "";

        for (Tag t : tags) {
            tagsName = tagsName.concat(t.toString());
        }

        String note1ExpectedOutput = NOTE1_TITLE + " " + InterfaceManager.LS + NOTE1_CONTENT;
        String note2ExpectedOutput = NOTE2_TITLE + " " + tagsName + InterfaceManager.LS + NOTE2_CONTENT;

        assertEquals(getExecutionStringInputTitle(notebook, NOTE1_TITLE),note1ExpectedOutput);
        assertEquals(getExecutionStringInputTitle(notebook, NOTE2_TITLE),note2ExpectedOutput);
    }

    @Test
    void execute_inputIndex_NoteDoesNotExists_returnsUnsuccessful() {
        assertEquals(getExecutionStringInputIndex(notebook, 5),ViewNoteCommand.COMMAND_UNSUCCESSFUL_MESSAGE);
        assertEquals(getExecutionStringInputIndex(notebook, 3),ViewNoteCommand.COMMAND_UNSUCCESSFUL_MESSAGE);
    }

    @Test
    void execute_inputTitle_NoteDoesNotExists_returnsUnsuccessful() {
        assertEquals(getExecutionStringInputTitle(notebook, "Title"),ViewNoteCommand.COMMAND_UNSUCCESSFUL_MESSAGE);
        assertEquals(getExecutionStringInputTitle(notebook, "Random"),ViewNoteCommand.COMMAND_UNSUCCESSFUL_MESSAGE);
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
