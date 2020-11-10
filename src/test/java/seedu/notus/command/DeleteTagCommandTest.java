package seedu.notus.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.notus.data.notebook.Note;
import seedu.notus.data.notebook.Notebook;
import seedu.notus.data.tag.TagManager;
import seedu.notus.data.tag.Tag;
import seedu.notus.storage.StorageManager;
import seedu.notus.ui.Formatter;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.notus.util.CommandMessage.DELETE_TAG_UNSUCCESSFUL_MESSAGE;
import static seedu.notus.util.CommandMessage.HEADER_DELETE_TAG;

//@@author Chongjx
class DeleteTagCommandTest {

    private Tag tagRed;
    private Tag tagGreen;
    private Tag tagBlue;
    private Tag tagRedRef;
    private Tag tagGreenRef;

    private ArrayList<Tag> tags;
    private TagManager tagManager;
    private ArrayList<String> content;

    private Note defaultNote;
    private Notebook notebook;
    private StorageManager storageManager;

    @BeforeEach
    void setUp() {
        tagRed = new Tag("Red", Tag.COLOR_RED_STRING);
        tagGreen = new Tag("Green", Tag.COLOR_GREEN_STRING);
        tagBlue  = new Tag("Blue", Tag.COLOR_BLUE_STRING);
        tagRedRef = new Tag("Red", Tag.COLOR_RED_STRING);
        tagGreenRef = new Tag("Green", Tag.COLOR_GREEN_STRING);

        tags = new ArrayList<>();
        tagManager = new TagManager();

        content = new ArrayList<>();
        content.add("Default");

        defaultNote = new Note("Default", content, false, false);

        notebook = new Notebook();
        storageManager = new StorageManager(null, null, notebook, tagManager);
    }

    @Test
    void deleteTagCommand_deleteNonExistingTag_returnDefaultMessage() {
        tags.add(tagRed);
        tags.add(tagGreen);
        tags.add(tagBlue);

        ArrayList<String> expectedResult = new ArrayList<>();
        expectedResult.add(HEADER_DELETE_TAG);
        expectedResult.add(DELETE_TAG_UNSUCCESSFUL_MESSAGE + tagRed);
        expectedResult.add(DELETE_TAG_UNSUCCESSFUL_MESSAGE + tagGreen);
        expectedResult.add(DELETE_TAG_UNSUCCESSFUL_MESSAGE + tagBlue);

        String result = getCommandExecutionString(tagManager, tags);

        assertEquals(Formatter.formatString(expectedResult, true), result);
    }

    @Test
    void deleteTagCommand_deleteExistingTag_deletesTag() {
        tagManager.createTag(tagRed, true);
        tagManager.createTag(tagGreen, true);

        tags.add(tagRed);
        tags.add(tagGreen);

        defaultNote.setTags(tags);
        tagManager.rebindTags(defaultNote);

        tags = new ArrayList<>();
        tags.add(tagRedRef);
        tags.add(tagGreenRef);

        // Ensures the note is tagged properly
        assertEquals(defaultNote.getTags().size(), 2);
        assertTrue(defaultNote.getTags().contains(tagRed));
        assertTrue(defaultNote.getTags().contains(tagGreen));
        assertEquals(tagManager.getTagMap().size(), 2);
        assertTrue(tagManager.getTagMap().get(tagRed).contains(defaultNote));
        assertTrue(tagManager.getTagMap().get(tagGreen).contains(defaultNote));

        getCommandExecutionString(tagManager, tags);

        assertEquals(defaultNote.getTags().size(), 0);
        assertEquals(tagManager.getTagMap().size(), 0);
    }

    @Test
    void deleteTagCommand_deleteNonExistingAndExistingTag_deletesTagAndReturnDefaultMessage() {
        tagManager.createTag(tagRed, true);
        tagManager.createTag(tagGreen, true);

        tags.add(tagRed);
        tags.add(tagGreen);

        defaultNote.setTags(tags);
        tagManager.rebindTags(defaultNote);

        tags = new ArrayList<>();
        tags.add(tagRedRef);
        tags.add(tagGreenRef);
        tags.add(tagBlue);

        // Ensures the note is tagged properly
        assertEquals(defaultNote.getTags().size(), 2);
        assertTrue(defaultNote.getTags().contains(tagRed));
        assertTrue(defaultNote.getTags().contains(tagGreen));
        assertEquals(tagManager.getTagMap().size(), 2);
        assertTrue(tagManager.getTagMap().get(tagRed).contains(defaultNote));
        assertTrue(tagManager.getTagMap().get(tagGreen).contains(defaultNote));

        getCommandExecutionString(tagManager, tags);

        assertEquals(defaultNote.getTags().size(), 0);
        assertEquals(tagManager.getTagMap().size(), 0);
    }

    private String getCommandExecutionString(TagManager tagManger, ArrayList<Tag> tags) {
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(tags);
        deleteTagCommand.setData(notebook, null, tagManger, storageManager);
        return deleteTagCommand.execute();
    }
}
