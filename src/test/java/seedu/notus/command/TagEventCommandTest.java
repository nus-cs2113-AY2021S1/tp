package seedu.notus.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.notus.data.tag.TagManager;
import seedu.notus.data.tag.Tag;
import seedu.notus.data.timetable.Timetable;
import seedu.notus.data.timetable.Event;
import seedu.notus.storage.StorageManager;
import seedu.notus.ui.Formatter;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.notus.util.CommandMessage.INDEX_OUT_OF_RANGE_MESSAGE;

//@@author Chongjx
class TagEventCommandTest {

    private Tag tagRed;
    private Tag tagBlue;

    private Tag tagRedRef;
    private Tag tagBlueRef;

    private Event noTagEvent;
    private Event taggedEvent;

    private ArrayList<Tag> tags;
    private TagManager tagManager;
    private Timetable timetable;
    private StorageManager storageManager;

    private ArrayList<String> content;

    @BeforeEach
    void setUp() {
        content = new ArrayList<>();
        content.add("default");

        tagRed = new Tag("Red", Tag.COLOR_RED_STRING);
        tagBlue = new Tag("Blue", Tag.COLOR_BLUE_STRING);
        tagRedRef = new Tag("Red", Tag.COLOR_RED_STRING);
        tagBlueRef = new Tag("Blue", Tag.COLOR_BLUE_STRING);

        noTagEvent = new Event("Default", LocalDateTime.of(2020, 1, 1, 0, 0), LocalDateTime.of(2020, 1, 1, 1, 0));
        taggedEvent = new Event("TaggedEvent", LocalDateTime.of(2020, 1, 1, 0, 0), LocalDateTime.of(2020, 1, 1, 1, 0));

        tags = new ArrayList<>();

        tagManager = new TagManager();
        timetable = new Timetable();
        storageManager = new StorageManager(timetable, null, null, tagManager);
    }

    @Test
    void tagCommand_invalidIndex_returnsUnsuccessfulMessage() {
        timetable.addEvent(noTagEvent);
        timetable.addEvent(taggedEvent);

        tags = new ArrayList<>();

        String result = getCommandExecutionString(timetable, tagManager, storageManager, 3, tags);
        assertEquals(Formatter.formatString(INDEX_OUT_OF_RANGE_MESSAGE), result);
    }

    @Test
    void tagCommand_tagEvent_tagsEvent() {
        tags.add(tagRed);
        tags.add(tagBlue);

        taggedEvent.setTags(tags);
        tagManager.rebindTags(taggedEvent);

        timetable.addEvent(noTagEvent);
        timetable.addEvent(taggedEvent);

        getCommandExecutionString(timetable, tagManager, storageManager, 0, tags);

        assertEquals(noTagEvent.getTags().size(), 2);
        assertTrue(noTagEvent.getTags().contains(tagRed));
        assertEquals(tagManager.getTagMap().size(), 2);
        assertEquals(tagManager.getTagMap().get(tagRed).size(), 2);
    }

    @Test
    void tagCommand_untagEvent_untagsEvent() {
        tags.add(tagRed);
        tags.add(tagBlue);

        taggedEvent.setTags(tags);
        tagManager.rebindTags(taggedEvent);

        timetable.addEvent(noTagEvent);
        timetable.addEvent(taggedEvent);

        tags = new ArrayList<>();
        tags.add(tagRedRef);
        tags.add(tagBlueRef);

        getCommandExecutionString(timetable, tagManager, storageManager, 1 , tags);

        assertEquals(taggedEvent.getTags().size(), 0);
        assertEquals(tagManager.getTagMap().get(tagRed).size(), 0);
        assertEquals(tagManager.getTagMap().get(tagBlue).size(), 0);
    }

    @Test
    void tagCommand_tagAndUntagEvent_tagsEventAndUntagsEvent() {
        tags.add(tagRed);
        tags.add(tagBlue);

        taggedEvent.setTags(tags);
        tagManager.rebindTags(taggedEvent);

        tags = new ArrayList<>();
        tags.add(tagBlue);

        noTagEvent.setTags(tags);

        timetable.addEvent(noTagEvent);
        timetable.addEvent(taggedEvent);

        tags = new ArrayList<>();
        tags.add(tagRedRef);
        tags.add(tagBlueRef);

        getCommandExecutionString(timetable, tagManager, storageManager, 0, tags);

        assertEquals(noTagEvent.getTags().size(), 1);
        assertEquals(tagManager.getTagMap().get(tagRed).size(), 2);
        assertEquals(tagManager.getTagMap().get(tagBlue).size(), 1);
    }

    private String getCommandExecutionString(Timetable timetable, TagManager tagManager, StorageManager storageManager,
                                             int index, ArrayList<Tag> tags) {
        TagEventCommand tagEventCommand = new TagEventCommand(index, tags);
        tagEventCommand.setData(null, timetable, tagManager, storageManager);
        return tagEventCommand.execute();
    }
}
