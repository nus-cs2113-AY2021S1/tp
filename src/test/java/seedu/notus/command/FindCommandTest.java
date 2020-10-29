package seedu.notus.command;

//@@author R-Ramana

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.notus.data.notebook.Note;
import seedu.notus.data.notebook.Notebook;
import seedu.notus.data.tag.Tag;
import seedu.notus.data.tag.TagManager;
import seedu.notus.ui.Formatter;
import seedu.notus.ui.FormatterStub;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.notus.command.FindCommand.COMMAND_SUCCESSFUL_MESSAGE;
import static seedu.notus.command.FindCommand.COMMAND_UNSUCCESSFUL_MESSAGE;

class FindCommandTest {

    Notebook notebook;
    TagManager tagManager;
    int maxRowLength = 100;
    ArrayList<Note> notes;

    ArrayList<Tag> tag = new ArrayList<>();
    Tag tagImpt;

    Note defaultNote;
    Note testNote1;
    Note testNote2;
    Note testNote3;

    @BeforeEach
    void setup() {
        notebook = new Notebook();
        tagManager = new TagManager();

        ArrayList<String> content = new ArrayList<>();

        content.add("default");

        tag.add(tagImpt);

        defaultNote = new Note("Default", content, false, false, tag);
        testNote1 = new Note("TestNote1", content, true, false);
        testNote2 = new Note("TestNote2", content, false, false);
        testNote3 = new Note("Song Lyrics", content, true, false);

        notebook.addNote(defaultNote);
        notebook.addNote(testNote1);
        notebook.addNote(testNote2);
        notebook.addNote(testNote3);

        notes = new ArrayList<>();
    }

    @Test
    void execute_keywordTest_returnsTestNote1AndTestNote2() {
        String keyword = "test";

        notes.add(testNote1);
        notes.add(testNote2);

        String expected = Formatter.formatNotes(COMMAND_SUCCESSFUL_MESSAGE, notes, notebook);
        String result = getCommandExecutionString(notebook, keyword);

        assertEquals(expected, result);
    }

    @Test
    void execute_keywordNil_returnsNoMatch() {
        String keyword = "NIL";

        String expected = Formatter.formatString(COMMAND_UNSUCCESSFUL_MESSAGE);
        String result = getCommandExecutionString(notebook, keyword);

        assertEquals(expected, result);
    }

    private String getCommandExecutionString(Notebook notebook, String keyword) {
        FindCommand findCommand = new FindCommand(keyword);
        findCommand.setData(notebook, null, tagManager, null);
        return findCommand.execute();
    }
}
