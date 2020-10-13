package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.data.notebook.TagManager;
import seedu.duke.data.notebook.Tag;
import seedu.duke.ui.InterfaceManager;
import static seedu.duke.command.CreateTagCommand.COMMAND_SUCCESSFUL_MESSAGE;
import static seedu.duke.command.CreateTagCommand.COMMAND_UNSUCCESSFUL_MESSAGE;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateTagCommandTest {

    private Tag tagRed;
    private Tag tagGreen;
    private Tag tagBlue;
    private Tag tagRedToYellow;
    private Tag tagGreenToPurple;

    private ArrayList<Tag> tags;
    private TagManager tagManager;

    @BeforeEach
    void setUp() {
        tagRed = new Tag("Red", Tag.COLOR_RED_STRING);
        tagGreen = new Tag("Green", Tag.COLOR_GREEN_STRING);
        tagBlue  = new Tag("Blue", Tag.COLOR_BLUE_STRING);
        tagRedToYellow = new Tag("Red", Tag.COLOR_YELLOW_STRING);
        tagGreenToPurple = new Tag("Green", Tag.COLOR_PURPLE_STRING);

        tags = new ArrayList<>();
        tagManager = new TagManager();
    }

    @Test
    void createTagCommand_createNonExistingTag_createsTag() {
        tags.add(tagRed);
        tags.add(tagGreen);
        tags.add(tagBlue);

        String result = getCommandExecutionString(tagManager, tags);

        assertTrue(tagManager.getTagMap().containsKey(tagRed));
        assertTrue(tagManager.getTagMap().containsKey(tagGreen));
        assertTrue(tagManager.getTagMap().containsKey(tagBlue));
        assertEquals(tagManager.getTagMap().size(), 3);

        assertEquals(COMMAND_SUCCESSFUL_MESSAGE + tagRed + InterfaceManager.LS
                + COMMAND_SUCCESSFUL_MESSAGE + tagGreen + InterfaceManager.LS
                + COMMAND_SUCCESSFUL_MESSAGE + tagBlue
                , result);
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

        String result = getCommandExecutionString(tagManager, tags);

        assertTrue(tagManager.getTagMap().containsKey(tagRed));
        assertTrue(tagManager.getTagMap().containsKey(tagGreen));
        assertTrue(tagManager.getTagMap().containsKey(tagBlue));
        assertEquals(tagManager.getTag(tagRedToYellow.getTagName()), tagRed);
        assertEquals(tagManager.getTag(tagGreenToPurple.getTagName()), tagGreen);
        assertEquals(tagManager.getTagMap().size(), 3);

        assertEquals(COMMAND_UNSUCCESSFUL_MESSAGE + tagRedToYellow + InterfaceManager.LS
                        + COMMAND_UNSUCCESSFUL_MESSAGE + tagGreenToPurple
                , result);
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

        String result = getCommandExecutionString(tagManager, tags);

        assertTrue(tagManager.getTagMap().containsKey(tagRed));
        assertTrue(tagManager.getTagMap().containsKey(tagGreen));
        assertTrue(tagManager.getTagMap().containsKey(tagBlue));
        assertEquals(tagManager.getTag(tagRedToYellow.getTagName()), tagRed);
        assertEquals(tagManager.getTag(tagGreenToPurple.getTagName()), tagGreen);
        assertEquals(tagManager.getTagMap().size(), 3);

        assertEquals(COMMAND_UNSUCCESSFUL_MESSAGE + tagRedToYellow + InterfaceManager.LS
                        + COMMAND_UNSUCCESSFUL_MESSAGE + tagGreenToPurple + InterfaceManager.LS
                        + COMMAND_SUCCESSFUL_MESSAGE + tagBlue
                , result);
    }

    private String getCommandExecutionString(TagManager tagManager, ArrayList<Tag> tags) {
        CreateTagCommand createTagCommand = new CreateTagCommand(tags);
        createTagCommand.setData(null, null, tagManager, null);
        return createTagCommand.execute();
    }
}
