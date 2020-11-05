package seedu.notus.command;

//@@author R-Ramana

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.notus.data.notebook.Note;
import seedu.notus.data.notebook.Notebook;
import seedu.notus.data.tag.Tag;
import seedu.notus.data.tag.TagManager;
import seedu.notus.data.timetable.Timetable;
import seedu.notus.storage.StorageManager;
import seedu.notus.ui.Formatter;
import seedu.notus.util.parser.ParserManager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.notus.util.CommandMessage.ARCHIVE_NOTES_MESSAGE;
import static seedu.notus.util.CommandMessage.EMPTY_NOTEBOOK_MESSAGE;
import static seedu.notus.util.CommandMessage.INVALID_TAG_MESSAGE;
import static seedu.notus.util.CommandMessage.LIST_NOTES_MESSAGE;
import static seedu.notus.util.CommandMessage.PINNED_NOTES_MESSAGE;
import static seedu.notus.util.CommandMessage.UNPINNED_NOTES_MESSAGE;

class ListNoteCommandTest {

    Notebook notebook;
    Timetable timetable;
    TagManager tagManager;
    ParserManager parserManager;
    StorageManager storageManager;

    int maxRowLength = 100;

    ArrayList<Note> noteArrayList;
    ArrayList<Note> pinNotes;
    ArrayList<Note> unpinnedNotes;
    ArrayList<Tag> tag;
    ArrayList<Tag> tagSet;

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
        storageManager = new StorageManager(timetable, parserManager, notebook, tagManager);

        noteArrayList = new ArrayList<>();
        pinNotes = new ArrayList<>();
        unpinnedNotes = new ArrayList<>();

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

        tag = new ArrayList<>();
        tagSet = new ArrayList<>();

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
        String expected = Formatter.formatString(EMPTY_NOTEBOOK_MESSAGE);

        String actual = getCommandExecutionString(notebook);

        assertEquals(expected, actual);
    }

    @Test
    void execute_noNotesArchivedNotesExist_notebookIsEmpty() {
        notebook.addNote(defaultNote);
        notebook.addNote(songLyrics);

        int index = 1;

        notebook.archiveNotes("default");
        notebook.archiveNotes(index - 1);

        String expected = Formatter.formatString(EMPTY_NOTEBOOK_MESSAGE);

        String actual = getCommandExecutionString(notebook);

        assertEquals(expected, actual);
    }

    @Test
    void execute_noPinnedNotes_defaultList() {
        notebook.addNote(defaultNote);
        notebook.addNote(songLyrics);

        noteArrayList.add(defaultNote);
        noteArrayList.add(songLyrics);

        String expected = Formatter.formatNotes(LIST_NOTES_MESSAGE, noteArrayList, notebook);

        String actual = getCommandExecutionString(notebook);

        assertEquals(expected, actual);
    }

    @Test
    void execute_SortUp_AscendingSortList() {
        notebook.addNote(defaultNote);
        notebook.addNote(songLyrics);
        notebook.addNote(cs2113);
        cs2113.togglePinned();

        noteArrayList.add(cs2113);
        noteArrayList.add(defaultNote);
        noteArrayList.add(songLyrics);

        String expected = Formatter.formatNotes(LIST_NOTES_MESSAGE, noteArrayList, notebook);

        String actual = getCommandExecutionString(notebook, (Boolean) true);

        assertEquals(expected, actual);
    }

    @Test
    void execute_SortDown_DescendingSortList() {
        notebook.addNote(defaultNote);
        notebook.addNote(songLyrics);
        notebook.addNote(cs2113);
        cs2113.togglePinned();

        noteArrayList.add(songLyrics);
        noteArrayList.add(defaultNote);
        noteArrayList.add(cs2113);

        String expected = Formatter.formatNotes(LIST_NOTES_MESSAGE, noteArrayList, notebook);

        String actual = getCommandExecutionString(notebook, (Boolean) false);

        assertEquals(expected, actual);
    }

    @Test
    void execute_PinnedNotes_defaultList() {
        notebook.addNote(cs2113);

        pinNotes.add(cs2113);

        String expected = Formatter.formatNotes(PINNED_NOTES_MESSAGE, UNPINNED_NOTES_MESSAGE,
                pinNotes, unpinnedNotes, notebook);

        String actual = getCommandExecutionString(notebook);

        assertEquals(expected, actual);
    }

    @Test
    void execute_PinnedNotesSortUp_SortUpPinnedNotesList() {
        notebook.addNote(cs2113);
        notebook.addNote(testNote1);

        pinNotes.add(cs2113);
        pinNotes.add(testNote1);

        String expected = Formatter.formatNotes(PINNED_NOTES_MESSAGE, UNPINNED_NOTES_MESSAGE,
                pinNotes, unpinnedNotes, notebook);

        String actual = getCommandExecutionString(notebook, (Boolean) true);

        assertEquals(expected, actual);
    }

    @Test
    void execute_PinnedNotesSortDown_SortDownPinnedNotesList() {
        notebook.addNote(cs2113);
        notebook.addNote(testNote1);

        pinNotes.add(testNote1);
        pinNotes.add(cs2113);

        String expected = Formatter.formatNotes(PINNED_NOTES_MESSAGE, UNPINNED_NOTES_MESSAGE,
                pinNotes, unpinnedNotes, notebook);

        String actual = getCommandExecutionString(notebook, (Boolean) false);

        assertEquals(expected, actual);
    }

    @Test
    void execute_PinnedAndUnpinnedNotes_defaultList() {
        notebook.addNote(testNote1);
        notebook.addNote(songLyrics);

        pinNotes.add(testNote1);
        unpinnedNotes.add(songLyrics);

        String expected = Formatter.formatNotes(PINNED_NOTES_MESSAGE, UNPINNED_NOTES_MESSAGE,
                pinNotes, unpinnedNotes, notebook);

        String actual = getCommandExecutionString(notebook);

        assertEquals(expected, actual);
    }

    @Test
    void execute_PinnedAndUnpinnedNotesSortUp_TwoSortedUpList() {
        notebook.addNote(testNote1);
        notebook.addNote(songLyrics);
        notebook.addNote(defaultNote);
        notebook.addNote(cs2113);

        pinNotes.add(cs2113);
        pinNotes.add(testNote1);

        unpinnedNotes.add(defaultNote);
        unpinnedNotes.add(songLyrics);

        String expected = Formatter.formatNotes(PINNED_NOTES_MESSAGE, UNPINNED_NOTES_MESSAGE,
                pinNotes, unpinnedNotes, notebook);

        String actual = getCommandExecutionString(notebook, (Boolean) true);

        assertEquals(expected, actual);
    }

    @Test
    void execute_PinnedAndUnpinnedNotesSortDown_TwoSortedDownList() {
        notebook.addNote(testNote1);
        notebook.addNote(songLyrics);
        notebook.addNote(defaultNote);
        notebook.addNote(cs2113);

        pinNotes.add(testNote1);
        pinNotes.add(cs2113);

        unpinnedNotes.add(songLyrics);
        unpinnedNotes.add(defaultNote);

        String expected = Formatter.formatNotes(PINNED_NOTES_MESSAGE, UNPINNED_NOTES_MESSAGE,
                pinNotes, unpinnedNotes, notebook);

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

        String expected = Formatter.formatString(INVALID_TAG_MESSAGE);

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
        tagManager.tagObject(songLyrics, tagCs2113);
        tagManager.tagObject(songLyrics, tagNus);
        tagManager.tagObject(testNote1, tagNus);

        pinNotes.add(testNote1);
        unpinnedNotes.add(songLyrics);

        String expected = Formatter.formatNotes(PINNED_NOTES_MESSAGE, UNPINNED_NOTES_MESSAGE,
                pinNotes, unpinnedNotes, notebook);

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
        tagManager.tagObject(songLyrics, tagCs2113);
        tagManager.tagObject(songLyrics, tagNus);

        unpinnedNotes.add(songLyrics);

        String expected = Formatter.formatNotes(PINNED_NOTES_MESSAGE, UNPINNED_NOTES_MESSAGE,
                pinNotes, unpinnedNotes, notebook);

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

        noteArrayList.add(testNote1);
        noteArrayList.add(cs2113);

        String expected = Formatter.formatNotes(ARCHIVE_NOTES_MESSAGE, noteArrayList, notebook);

        String actual = getCommandExecutionString(notebook, true);

        assertEquals(expected, actual);
    }

    private String getCommandExecutionString(Notebook notebook) {
        ListNoteCommand listNoteCommand = new ListNoteCommand();
        listNoteCommand.setData(notebook, null, null, storageManager);
        return listNoteCommand.execute();
    }

    private String getCommandExecutionString(Notebook notebook, boolean isArchived) {
        ListNoteCommand listNoteCommand = new ListNoteCommand(isArchived);
        listNoteCommand.setData(notebook, null, null, storageManager);
        return listNoteCommand.execute();
    }

    private String getCommandExecutionString(Notebook notebook, Boolean isAscendingOrder) {
        ListNoteCommand listNoteCommand = new ListNoteCommand(isAscendingOrder);
        listNoteCommand.setData(notebook, null, null, storageManager);
        return listNoteCommand.execute();
    }

    private String getCommandExecutionString(Notebook notebook, Boolean isAscendingOrder, ArrayList<String> tags) {
        ListNoteCommand listNoteCommand = new ListNoteCommand(isAscendingOrder, tags);
        listNoteCommand.setData(notebook, null, tagManager, storageManager);
        return listNoteCommand.execute();
    }

    private String getCommandExecutionString(Notebook notebook, ArrayList<String> tags) {
        ListNoteCommand listNoteCommand = new ListNoteCommand(tags);
        listNoteCommand.setData(notebook, null, tagManager, storageManager);
        return listNoteCommand.execute();
    }
}
