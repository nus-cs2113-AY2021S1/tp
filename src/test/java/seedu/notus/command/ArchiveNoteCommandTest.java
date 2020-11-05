package seedu.notus.command;

//@@author R-Ramana

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.notus.data.notebook.Note;
import seedu.notus.data.notebook.Notebook;
import seedu.notus.data.notebook.NotebookStub;
import seedu.notus.data.tag.Tag;
import seedu.notus.data.tag.TagManager;
import seedu.notus.data.timetable.Timetable;
import seedu.notus.storage.StorageManager;
import seedu.notus.ui.Formatter;
import seedu.notus.util.parser.ParserManager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.notus.util.CommandMessage.INDEX_OUT_OF_RANGE_MESSAGE;
import static seedu.notus.util.CommandMessage.NOTE_DOES_NOT_EXIST_MESSAGE;

class ArchiveNoteCommandTest {

    Notebook notebook;
    Timetable timetable;
    TagManager tagManager;
    ParserManager parserManager;
    StorageManager storageManager;

    ArrayList<String> content;

    ArrayList<Tag> tag = new ArrayList<>();
    ArrayList<Tag> tagSet = new ArrayList<>();
    Tag tagImpt;
    Tag tagCs2113;
    Tag tagNus;

    @BeforeEach
    void setup() {
        notebook = new Notebook();
        storageManager = new StorageManager(timetable, parserManager, notebook, tagManager);

        content = new ArrayList<>();
        tagImpt = new Tag("Impt", Tag.COLOR_RED_STRING);
        tagCs2113 = new Tag("CEG", Tag.COLOR_YELLOW_STRING);
        tagNus = new Tag("NUS", Tag.COLOR_BLUE_STRING);

        content.add("default");
        content.add("hi how are you");

        tag.add(tagImpt);

        tagSet.add(tagNus);
        tagSet.add(tagCs2113);

        Note testNote1 = new Note("Default", content, true, false, tag);
        Note testNote2 = new Note("TestNote1", content, false, false);
        Note testNote3 = new Note("TestNote2", content, false, false, tagSet);
        Note testNote4 = new Note("Random Text", content, true, false, tagSet);

        notebook.addNote(testNote1);
        notebook.addNote(testNote2);
        notebook.addNote(testNote3);
        notebook.addNote(testNote4);
    }

    @Test
    void execute_validIndex_returnsArchiveMessage() {
        /*int index = 1;
        String title = NotebookStub.getArchiveNoteTitle(index);

        String expected = Formatter.formatString("hola" + title);
        String result = getCommandExecutionString(notebook, index - 1);

        assertEquals(expected, result);*/
    }

    @Test
    void execute_invalidIndex_returnsInvalidIndexMessage() {
        int index = 50;

        String expected = Formatter.formatString(INDEX_OUT_OF_RANGE_MESSAGE);
        String result = getCommandExecutionString(notebook, index - 1);

        assertEquals(expected, result);
    }

    @Test
    void execute_validTitle_returnsArchiveMessage() {
        /*String title = "random text";

        String expected = Formatter.formatString("hola" + title);
        String result = getCommandExecutionString(notebook, title);

        assertEquals(expected, result);*/
    }

    @Test
    void execute_existingNoteTitle_returnsNoNoteMessage() {
        String title = "random text";
        notebook.archiveNotes(title);

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
        ArchiveNoteCommand archiveCommand = new ArchiveNoteCommand(keyword);
        archiveCommand.setData(notebook, null, null, storageManager);
        return archiveCommand.execute();
    }

    private String getCommandExecutionString(Notebook notebook, int index) {
        ArchiveNoteCommand archiveCommand = new ArchiveNoteCommand(index);
        archiveCommand.setData(notebook, null, null, storageManager);
        return archiveCommand.execute();
    }
}