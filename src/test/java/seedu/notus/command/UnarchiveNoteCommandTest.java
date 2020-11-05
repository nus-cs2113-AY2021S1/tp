package seedu.notus.command;

//@@author R-Ramana

import seedu.notus.data.notebook.Note;
import seedu.notus.data.notebook.Notebook;
import seedu.notus.data.notebook.NotebookStub;
import seedu.notus.data.tag.Tag;
import seedu.notus.data.tag.TagManager;
import seedu.notus.data.timetable.Timetable;
import seedu.notus.storage.StorageManager;
import seedu.notus.ui.Formatter;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.notus.util.CommandMessage.INDEX_OUT_OF_RANGE_MESSAGE;
import static seedu.notus.util.CommandMessage.NOTE_DOES_NOT_EXIST_MESSAGE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.notus.util.parser.ParserManager;

class UnarchiveNoteCommandTest {

    Notebook notebook;
    Timetable timetable;
    TagManager tagManager;
    ParserManager parserManager;
    StorageManager storageManager;

    ArrayList<String> content;
    ArrayList<Note> notes;

    ArrayList<Tag> tag = new ArrayList<>();
    ArrayList<Tag> tagSet = new ArrayList<>();
    Tag tagImpt;
    Tag tagCs2113;
    Tag tagNus;

    Note testNote1;
    Note testNote2;
    Note testNote3;
    Note testNote4;

    @BeforeEach
    void setup() {
        notebook = new Notebook();
        storageManager = new StorageManager(timetable, parserManager, notebook, tagManager);

        content = new ArrayList<>();
        notes = new ArrayList<>();
        tagImpt = new Tag("Impt", Tag.COLOR_RED_STRING);
        tagCs2113 = new Tag("CEG", Tag.COLOR_YELLOW_STRING);
        tagNus = new Tag("NUS", Tag.COLOR_BLUE_STRING);

        content.add("default");
        content.add("hi how are you");

        tag.add(tagImpt);

        tagSet.add(tagNus);
        tagSet.add(tagCs2113);

        testNote1 = new Note("Default", content, true, false, tag);
        testNote2 = new Note("TestNote1", content, false, false);
        testNote3 = new Note("TestNote2", content, false, false, tagSet);
        testNote4 = new Note("Random Text", content, true, false, tagSet);

        notebook.addNote(testNote1);
        notebook.addNote(testNote2);
        notebook.addNote(testNote3);
        notebook.addNote(testNote4);

        int index = 1;

        notebook.archiveNotes(index - 1);
        notebook.archiveNotes("random text");
    }

    @Test
    void execute_validIndex_returnsUnarchiveMessage() {
//        int index = 1;
//        String title = NotebookStub.getUnarchiveNoteTitle(index);
//
//        String expected = Formatter.formatString("hola" + title);
//        String result = getCommandExecutionString(notebook, index - 1);
//
//        assertEquals(expected, result);
    }

    @Test
    void execute_invalidIndex_returnsInvalidIndexMessage() {
        int index = 50;

        String expected = Formatter.formatString(INDEX_OUT_OF_RANGE_MESSAGE);
        String result = getCommandExecutionString(notebook, index - 1);

        assertEquals(expected, result);
    }

    @Test
    void execute_validTitle_returnsUnarchiveMessage() {
//        String title = "random text";
//
//        String expected = Formatter.formatString("hola" + title);
//        String result = getCommandExecutionString(notebook, title);
//
//        assertEquals(expected, result);
    }

    @Test
    void execute_existingNoteTitle_returnsNoNoteMessage() {
        String title = "random text";
        notebook.unarchiveNotes(title);

        String expected = Formatter.formatString(NOTE_DOES_NOT_EXIST_MESSAGE);
        String result = getCommandExecutionString(notebook, title);

        assertEquals(expected, result);
    }

    @Test
    void execute_invalidTitle_returnsNoNotes() {
        String title = "rando";

        String expected = Formatter.formatString(NOTE_DOES_NOT_EXIST_MESSAGE);
        String result = getCommandExecutionString(notebook, title);

        assertEquals(expected, result);
    }

    private String getCommandExecutionString(Notebook notebook, String keyword) {
        UnarchiveNoteCommand unarchiveCommand = new UnarchiveNoteCommand(keyword);
        unarchiveCommand.setData(notebook, null, null, storageManager);
        return unarchiveCommand.execute();
    }

    private String getCommandExecutionString(Notebook notebook, int index) {
        UnarchiveNoteCommand unarchiveCommand = new UnarchiveNoteCommand(index);
        unarchiveCommand.setData(notebook, null, null, storageManager);
        return unarchiveCommand.execute();
    }

}