package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.data.notebook.Note;
import seedu.duke.data.notebook.TagManager;
import seedu.duke.data.notebook.Tag;
import seedu.duke.util.Formatter;

import static seedu.duke.command.DeleteTagCommand.COMMAND_SUCCESSFUL_MESSAGE;
import static seedu.duke.command.DeleteTagCommand.COMMAND_UNSUCCESSFUL_MESSAGE;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteTagCommandTest {

    private Tag tagRed;
    private Tag tagGreen;
    private Tag tagBlue;
    private Tag tagRedRef;
    private Tag tagGreenRef;

    private ArrayList<Tag> tags;
    private TagManager tagManager;

    private Note defaultNote;

    @BeforeEach
    void setUp() {
        tagRed = new Tag("Red", Tag.COLOR_RED_STRING);
        tagGreen = new Tag("Green", Tag.COLOR_GREEN_STRING);
        tagBlue  = new Tag("Blue", Tag.COLOR_BLUE_STRING);
        tagRedRef = new Tag("Red", Tag.COLOR_RED_STRING);
        tagGreenRef = new Tag("Green", Tag.COLOR_GREEN_STRING);

        tags = new ArrayList<>();
        tagManager = new TagManager();

        defaultNote = new Note("Default", "Default", false);
    }

    @Test
    void deleteTagCommand_deleteNonExistingTag_returnDefaultMessage() {
        tags.add(tagRed);
        tags.add(tagGreen);
        tags.add(tagBlue);

        ArrayList<String> expectedResult = new ArrayList<>();
        expectedResult.add(COMMAND_UNSUCCESSFUL_MESSAGE + tagRed);
        expectedResult.add(COMMAND_UNSUCCESSFUL_MESSAGE + tagGreen);
        expectedResult.add(COMMAND_UNSUCCESSFUL_MESSAGE + tagBlue);

        String result = getCommandExecutionString(tagManager, tags);

        assertEquals(Formatter.formatString(expectedResult, false, true), result);
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
        deleteTagCommand.setData(null, null, tagManger, null);
        return deleteTagCommand.execute();
    }
}
