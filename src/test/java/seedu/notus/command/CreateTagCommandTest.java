package seedu.notus.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.notus.data.notebook.Notebook;
import seedu.notus.data.tag.TagManager;
import seedu.notus.data.tag.Tag;
import seedu.notus.data.timetable.Timetable;
import seedu.notus.storage.StorageManager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author Chongjx
class CreateTagCommandTest {

    private Tag tagRed;
    private Tag tagGreen;
    private Tag tagBlue;
    private Tag tagRedToYellow;
    private Tag tagGreenToPurple;

    private ArrayList<Tag> tags;
    private TagManager tagManager;
    private Notebook notebook = new Notebook();
    private Timetable timetable = new Timetable();


    @BeforeEach
    void setUp() {
        tagRed = new Tag("Red", Tag.COLOR_RED_STRING);
        tagGreen = new Tag("Green", Tag.COLOR_GREEN_STRING);
        tagBlue  = new Tag("Blue", Tag.COLOR_BLUE_STRING);
        tagRedToYellow = new Tag("Red", Tag.COLOR_YELLOW_STRING);
        tagGreenToPurple = new Tag("Green", Tag.COLOR_MAGENTA_STRING);

        tags = new ArrayList<>();
        tagManager = new TagManager();
    }

    @Test
    void createTagCommand_createNonExistingTag_createsTag() {
        tags.add(tagRed);
        tags.add(tagGreen);
        tags.add(tagBlue);

        getCommandExecutionString(tagManager, tags);

        assertTrue(tagManager.getTagMap().containsKey(tagRed));
        assertTrue(tagManager.getTagMap().containsKey(tagGreen));
        assertTrue(tagManager.getTagMap().containsKey(tagBlue));
        assertEquals(tagManager.getTagMap().size(), 3);
    }

    @Test
    void createTagCommand_CreateExistingTag_updatesTag() {
        tags.add(tagRed);
        tags.add(tagGreen);
        tags.add(tagBlue);

        getCommandExecutionString(tagManager, tags);

        tags = new ArrayList<>();
        tags.add(tagRedToYellow);
        tags.add(tagGreenToPurple);

        getCommandExecutionString(tagManager, tags);

        assertTrue(tagManager.getTagMap().containsKey(tagRed));
        assertTrue(tagManager.getTagMap().containsKey(tagGreen));
        assertTrue(tagManager.getTagMap().containsKey(tagBlue));
        assertEquals(tagManager.getTag(tagRedToYellow.getTagName()), tagRed);
        assertEquals(tagManager.getTag(tagGreenToPurple.getTagName()), tagGreen);
        assertEquals(tagManager.getTagMap().size(), 3);
    }

    @Test
    void createTagCommand_createNonExistingAndExistingTag_createsTagAndUpdatesTag() {
        tags.add(tagRed);
        tags.add(tagGreen);

        getCommandExecutionString(tagManager, tags);

        assertTrue(tagManager.getTagMap().containsKey(tagRed));
        assertTrue(tagManager.getTagMap().containsKey(tagGreen));
        assertFalse(tagManager.getTagMap().containsKey(tagBlue));

        tags = new ArrayList<>();
        tags.add(tagRedToYellow);
        tags.add(tagGreenToPurple);
        tags.add(tagBlue);

        getCommandExecutionString(tagManager, tags);

        assertTrue(tagManager.getTagMap().containsKey(tagRed));
        assertTrue(tagManager.getTagMap().containsKey(tagGreen));
        assertTrue(tagManager.getTagMap().containsKey(tagBlue));
        assertEquals(tagManager.getTag(tagRedToYellow.getTagName()), tagRed);
        assertEquals(tagManager.getTag(tagGreenToPurple.getTagName()), tagGreen);
        assertEquals(tagManager.getTagMap().size(), 3);
    }

    private String getCommandExecutionString(TagManager tagManager, ArrayList<Tag> tags) {
        CreateTagCommand createTagCommand = new CreateTagCommand(tags);
        StorageManager storageManager = new StorageManager(timetable, null, notebook, tagManager);
        createTagCommand.setData(notebook, timetable, tagManager, storageManager);
        return createTagCommand.execute();
    }
}
