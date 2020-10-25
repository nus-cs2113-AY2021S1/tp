package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.data.notebook.Note;
import seedu.duke.data.notebook.Notebook;
import seedu.duke.data.notebook.Tag;
import seedu.duke.data.notebook.TagManager;
import seedu.duke.ui.Formatter;
import seedu.duke.ui.FormatterStub;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindCommandTest {

    Notebook notebook;
    TagManager tagManager;
    int maxRowLength = 100;

    ArrayList<Tag> tag = new ArrayList<>();
    Tag tagImpt;

    @BeforeEach
    void setup() {
        notebook = new Notebook();
        tagManager = new TagManager();

        ArrayList<String> content = new ArrayList<>();

        content.add("default");

        tag.add(tagImpt);

        Note defaultNote = new Note("Default", content, false, false, tag);
        Note testNote1 = new Note("TestNote1", content, true, false);
        Note testNote2 = new Note("TestNote2", content, false, false);
        Note testNote3 = new Note("Song Lyrics", content, true, false);

        notebook.addNote(defaultNote);
        notebook.addNote(testNote1);
        notebook.addNote(testNote2);
        notebook.addNote(testNote3);
    }

    @Test
    void execute_keywordTest_returnsTestNote1AndTestNote2() {
        String keyword = "test";

        String expected = "=".repeat(maxRowLength)
                + FormatterStub.encloseRow(FindCommandStub.execute(keyword))
                + "=".repeat(maxRowLength) + Formatter.LS
                + "[96m1. Title: TestNote1 [0m"
                + Formatter.LS + "default..." + Formatter.LS
                + "=".repeat(maxRowLength) + Formatter.LS
                + "[96m2. Title: TestNote2 [0m"
                + Formatter.LS + "default..." + Formatter.LS
                + "=".repeat(maxRowLength) + Formatter.LS
                + "=".repeat(maxRowLength) + Formatter.LS;
        String result = getCommandExecutionString(notebook, keyword);

        assertEquals(expected, result);
    }

    @Test
    void execute_keywordNil_returnsNoMatch() {
        String keyword = "NIL";

        String expected = "=".repeat(maxRowLength)
                + FormatterStub.encloseRow("There are no matching notes. Please try another search query.")
                + "=".repeat(maxRowLength) + Formatter.LS;

        String result = getCommandExecutionString(notebook, keyword);

        assertEquals(expected, result);
    }

    private String getCommandExecutionString(Notebook notebook, String keyword) {
        FindCommand findCommand = new FindCommand(keyword);
        findCommand.setData(notebook, null, tagManager, null);
        return findCommand.execute();
    }
}
