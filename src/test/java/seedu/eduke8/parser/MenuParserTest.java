package seedu.eduke8.parser;

import org.junit.jupiter.api.Test;
import seedu.eduke8.Eduke8Test;
import seedu.eduke8.bookmark.BookmarkList;
import seedu.eduke8.command.Command;
import seedu.eduke8.command.IncorrectCommand;
import seedu.eduke8.topic.TopicList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuParserTest extends Eduke8Test {
    @Test
    void menuParser_wrongStringInput_expectIncorrectCommand() {
        BookmarkList bookmarks = createTestBookmarkList();
        TopicList topicList = createTestTopicList();
        MenuParser menuParser = new MenuParser(bookmarks);

        Command badCommand = menuParser.parseCommand(topicList, "quiz t/wrongtopic n/badnnum");
        assertTrue(badCommand instanceof IncorrectCommand);
    }
}
