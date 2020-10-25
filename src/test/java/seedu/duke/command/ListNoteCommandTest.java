package seedu.duke.command;

import com.diogonunes.jcolor.Attribute;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.data.notebook.Note;
import seedu.duke.data.notebook.Notebook;
import seedu.duke.data.notebook.Tag;
import seedu.duke.data.notebook.TagManager;
import seedu.duke.ui.Formatter;
import seedu.duke.ui.FormatterStub;

import java.util.ArrayList;

import static com.diogonunes.jcolor.Ansi.colorize;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ListNoteCommandTest {

    Notebook notebook;
    TagManager tagManager;

    int maxRowLength = 100;

    ArrayList<Note> noteArrayList = new ArrayList<>();
    ArrayList<Tag> tag = new ArrayList<>();
    ArrayList<Tag> tagSet = new ArrayList<>();

    Note defaultNote;
    Note testNote1;
    Note cs2113;
    Note songLyrics;

    Tag tagSports;
    Tag tagCs2113;
    Tag tagNus;

    @BeforeEach
    void setup() {
        notebook = new Notebook();
        tagManager = new TagManager();

        tagSports = new Tag("Sports", Tag.COLOR_RED_STRING);
        tagCs2113 = new Tag("CEG", Tag.COLOR_YELLOW_STRING);
        tagNus = new Tag("NUS", Tag.COLOR_BLUE_STRING);

        ArrayList<String> content = new ArrayList<>();
        content.add("default");
        ArrayList<String> contentOne = new ArrayList<>();
        contentOne.add("testing");
        ArrayList<String> contentTwo = new ArrayList<>();
        contentTwo.add("JavaDocs");
        ArrayList<String> contentThree = new ArrayList<>();
        contentThree.add("I like to move it move it");
        contentThree.add("I like to move it move it");
        contentThree.add("I like to... MOVE IT!");

        tag.add(tagSports);
        tagSet.add(tagCs2113);
        tagSet.add(tagNus);

        defaultNote = new Note("Default", content, false, false, tag);
        testNote1 = new Note("TestNote1", contentOne, true, false);
        cs2113 = new Note("CS2113", contentTwo, true, false, tagSet);
        songLyrics = new Note("Song Lyrics", contentThree, false, false);
    }

    @Test
    void execute_noNotes_notebookIsEmpty() {
        String expected = printDivider(false)
                + FormatterStub.encloseRow("The notebook is empty!")
                + printDivider(false)
                + System.lineSeparator();

        String actual = getCommandExecutionString(notebook);

        assertEquals(expected, actual);
    }

    @Test
    void execute_noPinnedNotes_defaultList() {
        notebook.addNote(defaultNote);
        notebook.addNote(songLyrics);

        String expected = printDivider(false)
                + FormatterStub.encloseRow("Here are the list of notes:")
                + printDivider(true)
                + "[96m1. Title: Default [91m[Sports][0m[0m"
                + Formatter.LS + "default..." + Formatter.LS
                + printDivider(true)
                + colorize("2. Title: Song Lyrics ", Attribute.BRIGHT_CYAN_TEXT()) + Formatter.LS
                + "I like to move it move it..." + Formatter.LS
                + printDivider(true)
                + printDivider(true);

        String actual = getCommandExecutionString(notebook);

        assertEquals(expected, actual);
    }

    @Test
    void execute_SortUp_AscendingSortList() {
        notebook.addNote(defaultNote);
        notebook.addNote(songLyrics);
        notebook.addNote(cs2113);
        cs2113.togglePinned();

        String expected = printDivider(false)
                + FormatterStub.encloseRow("Here are the list of notes:")
                + printDivider(true)
                + "[96m1. Title: CS2113 [93m[CEG][0m[94m[NUS][0m[0m"
                + Formatter.LS + "JavaDocs..." + Formatter.LS
                + printDivider(true)
                + "[96m2. Title: Default [91m[Sports][0m[0m"
                + Formatter.LS + "default..." + Formatter.LS
                + printDivider(true)
                + colorize("3. Title: Song Lyrics ", Attribute.BRIGHT_CYAN_TEXT()) + Formatter.LS
                + "I like to move it move it..." + Formatter.LS
                + printDivider(true) + printDivider(true);

        String actual = getCommandExecutionString(notebook, (Boolean) true);

        assertEquals(expected, actual);
    }

    @Test
    void execute_SortDown_DescendingSortList() {
        notebook.addNote(defaultNote);
        notebook.addNote(songLyrics);
        notebook.addNote(cs2113);
        cs2113.togglePinned();

        String expected = printDivider(false)
                + FormatterStub.encloseRow("Here are the list of notes:")
                + printDivider(true)
                + colorize("1. Title: Song Lyrics ", Attribute.BRIGHT_CYAN_TEXT()) + Formatter.LS
                + "I like to move it move it..." + Formatter.LS
                + printDivider(true)
                + "[96m2. Title: Default [91m[Sports][0m[0m"
                + Formatter.LS + "default..." + Formatter.LS
                + printDivider(true)
                + "[96m3. Title: CS2113 [93m[CEG][0m[94m[NUS][0m[0m"
                + Formatter.LS + "JavaDocs..." + Formatter.LS
                + printDivider(true) + printDivider(true);

        String actual = getCommandExecutionString(notebook, (Boolean) false);

        assertEquals(expected, actual);
    }

    @Test
    void execute_PinnedNotes_defaultList() {
        notebook.addNote(cs2113);

        String expected = printDivider(false)
                + FormatterStub.encloseRow("Here are the list of pinned notes:")
                + printDivider(true)
                + "[96m1. Title: CS2113 [93m[CEG][0m[94m[NUS][0m[0m"
                + Formatter.LS + "JavaDocs..." + Formatter.LS
                + printDivider(true)
                + printDivider(true)
                + printDivider(false)
                + FormatterStub.encloseRow("Here are the list of unpinned notes:")
                + printDivider(true) + printDivider(true);

        String actual = getCommandExecutionString(notebook);

        assertEquals(expected, actual);
    }

    @Test
    void execute_PinnedNotesSortUp_SortUpPinnedNotesList() {
        notebook.addNote(cs2113);
        notebook.addNote(testNote1);

        String expected = printDivider(false)
                + FormatterStub.encloseRow("Here are the list of pinned notes:")
                + printDivider(true)
                + "[96m1. Title: CS2113 [93m[CEG][0m[94m[NUS][0m[0m"
                + Formatter.LS + "JavaDocs..." + Formatter.LS
                + printDivider(true)
                + colorize("2. Title: TestNote1 ", Attribute.BRIGHT_CYAN_TEXT())
                + Formatter.LS + "testing..." + Formatter.LS
                + printDivider(true)
                + printDivider(true)
                + printDivider(false)
                + FormatterStub.encloseRow("Here are the list of unpinned notes:")
                + printDivider(true) + printDivider(true);

        String actual = getCommandExecutionString(notebook, (Boolean) true);

        assertEquals(expected, actual);
    }

    @Test
    void execute_PinnedNotesSortDown_SortDownPinnedNotesList() {
        notebook.addNote(cs2113);
        notebook.addNote(testNote1);

        String expected = printDivider(false)
                + FormatterStub.encloseRow("Here are the list of pinned notes:")
                + printDivider(true)
                + colorize("1. Title: TestNote1 ", Attribute.BRIGHT_CYAN_TEXT())
                + Formatter.LS + "testing..." + Formatter.LS
                + printDivider(true)
                + "[96m2. Title: CS2113 [93m[CEG][0m[94m[NUS][0m[0m"
                + Formatter.LS + "JavaDocs..." + Formatter.LS
                + printDivider(true)
                + printDivider(true)
                + printDivider(false)
                + FormatterStub.encloseRow("Here are the list of unpinned notes:")
                + printDivider(true) + printDivider(true);

        String actual = getCommandExecutionString(notebook, (Boolean) false);

        assertEquals(expected, actual);
    }

    @Test
    void execute_PinnedAndUnpinnedNotes_defaultList() {
        notebook.addNote(testNote1);
        notebook.addNote(songLyrics);

        String expected = printDivider(false)
                + FormatterStub.encloseRow("Here are the list of pinned notes:")
                + printDivider(true)
                + colorize("1. Title: TestNote1 ", Attribute.BRIGHT_CYAN_TEXT())
                + Formatter.LS + "testing..." + Formatter.LS
                + printDivider(true)
                + printDivider(true)
                + printDivider(false)
                + FormatterStub.encloseRow("Here are the list of unpinned notes:")
                + printDivider(true)
                + colorize("1. Title: Song Lyrics ", Attribute.BRIGHT_CYAN_TEXT()) + Formatter.LS
                + "I like to move it move it..." + Formatter.LS
                + printDivider(true)
                + printDivider(true);

        String actual = getCommandExecutionString(notebook);

        assertEquals(expected, actual);
    }

    @Test
    void execute_PinnedAndUnpinnedNotesSortUp_TwoSortedUpList() {
        notebook.addNote(testNote1);
        notebook.addNote(songLyrics);
        notebook.addNote(defaultNote);
        notebook.addNote(cs2113);

        String expected = printDivider(false)
                + FormatterStub.encloseRow("Here are the list of pinned notes:")
                + printDivider(true)
                + "[96m1. Title: CS2113 [93m[CEG][0m[94m[NUS][0m[0m"
                + Formatter.LS + "JavaDocs..." + Formatter.LS
                + printDivider(true)
                + colorize("2. Title: TestNote1 ", Attribute.BRIGHT_CYAN_TEXT())
                + Formatter.LS + "testing..." + Formatter.LS
                + printDivider(true)
                + printDivider(true)
                + printDivider(false)
                + FormatterStub.encloseRow("Here are the list of unpinned notes:")
                + printDivider(true)
                + "[96m1. Title: Default [91m[Sports][0m[0m"
                + Formatter.LS + "default..." + Formatter.LS
                + printDivider(true)
                + colorize("2. Title: Song Lyrics ", Attribute.BRIGHT_CYAN_TEXT()) + Formatter.LS
                + "I like to move it move it..." + Formatter.LS
                + printDivider(true)
                + printDivider(true);

        String actual = getCommandExecutionString(notebook, (Boolean) true);

        assertEquals(expected, actual);
    }

    @Test
    void execute_PinnedAndUnpinnedNotesSortDown_TwoSortedDownList() {
        notebook.addNote(testNote1);
        notebook.addNote(songLyrics);
        notebook.addNote(defaultNote);
        notebook.addNote(cs2113);

        String expected = printDivider(false)
                + FormatterStub.encloseRow("Here are the list of pinned notes:")
                + printDivider(true)
                + colorize("1. Title: TestNote1 ", Attribute.BRIGHT_CYAN_TEXT())
                + Formatter.LS + "testing..." + Formatter.LS
                + printDivider(true)
                + "[96m2. Title: CS2113 [93m[CEG][0m[94m[NUS][0m[0m"
                + Formatter.LS + "JavaDocs..." + Formatter.LS
                + printDivider(true)
                + printDivider(true)
                + printDivider(false)
                + FormatterStub.encloseRow("Here are the list of unpinned notes:")
                + printDivider(true)
                + colorize("1. Title: Song Lyrics ", Attribute.BRIGHT_CYAN_TEXT()) + Formatter.LS
                + "I like to move it move it..." + Formatter.LS
                + printDivider(true)
                + "[96m2. Title: Default [91m[Sports][0m[0m"
                + Formatter.LS + "default..." + Formatter.LS
                + printDivider(true)
                + printDivider(true);

        String actual = getCommandExecutionString(notebook, (Boolean) false);

        assertEquals(expected, actual);
    }

    @Test
    void execute_InvalidTag_NoResult() {
        notebook.addNote(testNote1);
        notebook.addNote(songLyrics);
        notebook.addNote(defaultNote);
        notebook.addNote(cs2113);

        ArrayList<String> tags = new ArrayList<>();
        tags.add("heyya");

        String error = "Your tags return no result. Please try an alternative tag or check your spellings ";

        String expected = printDivider(false)
                + FormatterStub.encloseRow(error)
                + printDivider(true);

        String actual = getCommandExecutionString(notebook, tags);

        assertEquals(expected, actual);
    }

    @Test
    void execute_ValidTagSortUp_NotesWithTag() {
        notebook.addNote(testNote1);
        notebook.addNote(songLyrics);
        notebook.addNote(defaultNote);

        tagManager.createTag(tagCs2113, false);
        tagManager.createTag(tagNus, false);
        ArrayList<String> tags = new ArrayList<>();
        tags.add("NUS");
        tags.add("CEG");
        tagManager.tagNote(songLyrics, tagCs2113);
        tagManager.tagNote(songLyrics, tagNus);
        tagManager.tagNote(testNote1, tagNus);


        String expected = printDivider(false)
                + FormatterStub.encloseRow("Here are the list of pinned notes:")
                + printDivider(true)
                + "[96m1. Title: TestNote1 [94m[NUS][0m[0m"
                + Formatter.LS + "testing..." + Formatter.LS
                + printDivider(true)
                + printDivider(true)
                + printDivider(false)
                + FormatterStub.encloseRow("Here are the list of unpinned notes:")
                + printDivider(true)
                + "[96m1. Title: Song Lyrics [93m[CEG][0m[94m[NUS][0m[0m"
                + Formatter.LS + "I like to move it move it..." + Formatter.LS
                + printDivider(true) + printDivider(true);

        String actual = getCommandExecutionString(notebook, true, tags);

        assertEquals(expected, actual);
    }

    @Test
    void execute_ValidTag_NotesWithTag() {
        notebook.addNote(testNote1);
        notebook.addNote(songLyrics);
        notebook.addNote(defaultNote);

        tagManager.createTag(tagCs2113, false);
        tagManager.createTag(tagNus, false);
        ArrayList<String> tags = new ArrayList<>();
        tags.add("NUS");
        tags.add("CEG");
        tagManager.tagNote(songLyrics, tagCs2113);
        tagManager.tagNote(songLyrics, tagNus);

        String expected = printDivider(false)
                + FormatterStub.encloseRow("Here are the list of pinned notes:")
                + printDivider(true)
                + printDivider(true)
                + printDivider(false)
                + FormatterStub.encloseRow("Here are the list of unpinned notes:")
                + printDivider(true)
                + "[96m1. Title: Song Lyrics [93m[CEG][0m[94m[NUS][0m[0m"
                + Formatter.LS + "I like to move it move it..." + Formatter.LS
                + printDivider(true) + printDivider(true);

        String actual = getCommandExecutionString(notebook, tags);

        assertEquals(expected, actual);
    }

    @Test
    void execute_ArchivedNotes_ArchiveList() {
        int index = 2;
        String title = "CS2113";

        notebook.addNote(defaultNote);
        notebook.addNote(songLyrics);
        notebook.addNote(testNote1);
        notebook.addNote(cs2113);
        notebook.archiveNotes(index);
        notebook.archiveNotes(title);

        String expected = printDivider(false)
                + FormatterStub.encloseRow("Here are the list of archived notes:")
                + printDivider(true)
                + colorize("1. Title: TestNote1 ", Attribute.BRIGHT_CYAN_TEXT())
                + Formatter.LS + "testing..." + Formatter.LS
                + printDivider(true)
                + "[96m2. Title: CS2113 [93m[CEG][0m[94m[NUS][0m[0m" + Formatter.LS
                + "JavaDocs..." + Formatter.LS
                + printDivider(true)
                + printDivider(true);

        String actual = getCommandExecutionString(notebook, true);

        assertEquals(expected, actual);
    }

    private String printDivider(boolean isNewline) {
        if (isNewline) {
            return "=".repeat(maxRowLength) + Formatter.LS;
        }
        return "=".repeat(maxRowLength);
    }

    private String getCommandExecutionString(Notebook notebook) {
        ListNoteCommand listNoteCommand = new ListNoteCommand();
        listNoteCommand.setData(notebook, null, null, null);
        return listNoteCommand.execute();
    }

    private String getCommandExecutionString(Notebook notebook, boolean isArchived) {
        ListNoteCommand listNoteCommand = new ListNoteCommand(isArchived);
        listNoteCommand.setData(notebook, null, null, null);
        return listNoteCommand.execute();
    }

    private String getCommandExecutionString(Notebook notebook, Boolean isAscendingOrder) {
        ListNoteCommand listNoteCommand = new ListNoteCommand(isAscendingOrder);
        listNoteCommand.setData(notebook, null, null, null);
        return listNoteCommand.execute();
    }

    private String getCommandExecutionString(Notebook notebook, Boolean isAscendingOrder, ArrayList<String> tags) {
        ListNoteCommand listNoteCommand = new ListNoteCommand(isAscendingOrder, tags);
        listNoteCommand.setData(notebook, null, tagManager, null);
        return listNoteCommand.execute();
    }

    private String getCommandExecutionString(Notebook notebook, ArrayList<String> tags) {
        ListNoteCommand listNoteCommand = new ListNoteCommand(tags);
        listNoteCommand.setData(notebook, null, tagManager, null);
        return listNoteCommand.execute();
    }
}
