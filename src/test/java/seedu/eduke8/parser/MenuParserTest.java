package seedu.eduke8.parser;

import org.junit.jupiter.api.Test;
import seedu.eduke8.Eduke8Test;
import seedu.eduke8.bookmark.BookmarkList;
import seedu.eduke8.command.Command;
import seedu.eduke8.command.IncorrectCommand;
import seedu.eduke8.command.AboutCommand;
import seedu.eduke8.command.BookmarkCommand;
import seedu.eduke8.command.ExitCommand;
import seedu.eduke8.command.HelpCommand;
import seedu.eduke8.command.NoteCommand;
import seedu.eduke8.command.QuizCommand;
import seedu.eduke8.command.TopicsCommand;
import seedu.eduke8.command.TextbookCommand;
import seedu.eduke8.command.StatsCommand;
import seedu.eduke8.topic.TopicList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuParserTest extends Eduke8Test {
    @Test
    void menuParser_wrongStringInput_expectIncorrectCommand() {
        BookmarkList bookmarks = createTestBookmarkList();
        TopicList topicList = createTestTopicList();
        MenuParser menuParser = new MenuParser(bookmarks);

        Command badCommand = menuParser.parseCommand(topicList, "quiz t/wrongtopic n/badnnum");
        assertTrue(badCommand instanceof IncorrectCommand);

        Command quizCommand = menuParser.parseCommand(topicList, "quiz t/OOP n/2");
        assertTrue(quizCommand instanceof IncorrectCommand);

        Command noteBadCommand = menuParser.parseCommand(topicList, "note wrong");
        assertTrue(noteBadCommand instanceof IncorrectCommand);

        Command quizBadCommand = menuParser.parseCommand(topicList, "quiz t/OOP n/1 s/-1");
        assertTrue(quizBadCommand instanceof IncorrectCommand);

        badCommand = menuParser.parseCommand(topicList, "wrong command all the way");
        assertTrue(badCommand instanceof IncorrectCommand);

        quizBadCommand = menuParser.parseCommand(topicList, "quiz s/20 tnoslash n/1");
        assertTrue(quizBadCommand instanceof IncorrectCommand);

        quizBadCommand = menuParser.parseCommand(topicList, "quiz tnoslash s/20  n/1");
        assertTrue(quizBadCommand instanceof IncorrectCommand);

        quizBadCommand = menuParser.parseCommand(topicList, "quiz t/OOP bad n/1");
        assertTrue(quizBadCommand instanceof IncorrectCommand);

        quizBadCommand = menuParser.parseCommand(topicList, "quiz n/1 bad t/OOP");
        assertTrue(quizBadCommand instanceof IncorrectCommand);

        noteBadCommand = menuParser.parseCommand(topicList, "quiz n/1 bad t/OOP");
        assertTrue(noteBadCommand instanceof IncorrectCommand);
    }

    @Test
    void menuParser_correctStringInput_correctInput() {
        BookmarkList bookmarks = createTestBookmarkList();
        TopicList topicList = createTestTopicList();
        MenuParser menuParser = new MenuParser(bookmarks);

        Command quizCommand = menuParser.parseCommand(topicList, "quiz n/2 s/20 t/OOP");
        assertTrue(quizCommand instanceof QuizCommand);

        quizCommand = menuParser.parseCommand(topicList, "quiz s/20 t/OOP n/2");
        assertTrue(quizCommand instanceof QuizCommand);

        quizCommand = menuParser.parseCommand(topicList, "quiz t/OOP n/2 s/20");
        assertTrue(quizCommand instanceof QuizCommand);

        quizCommand = menuParser.parseCommand(topicList, "quiz s/20 n/2 t/OOP");
        assertTrue(quizCommand instanceof QuizCommand);

        quizCommand = menuParser.parseCommand(topicList, "quiz n/2 t/OOP s/20");
        assertTrue(quizCommand instanceof QuizCommand);

        quizCommand = menuParser.parseCommand(topicList, "quiz t/OOP s/20 n/2");
        assertTrue(quizCommand instanceof QuizCommand);

        Command aboutCommand = menuParser.parseCommand(topicList, "about");
        assertTrue(aboutCommand instanceof AboutCommand);

        Command helpCommand = menuParser.parseCommand(topicList, "help");
        assertTrue(helpCommand instanceof HelpCommand);

        Command topicsCommand = menuParser.parseCommand(topicList, "topics");
        assertTrue(topicsCommand instanceof TopicsCommand);

        Command textbookCommand = menuParser.parseCommand(topicList, "textbook");
        assertTrue(textbookCommand instanceof TextbookCommand);

        Command bookmarkCommand = menuParser.parseCommand(topicList, "bookmark");
        assertTrue(bookmarkCommand instanceof BookmarkCommand);

        Command noteCommand = menuParser.parseCommand(topicList, "note list");
        assertTrue(noteCommand instanceof NoteCommand);

        noteCommand = menuParser.parseCommand(topicList, "note add");
        assertTrue(noteCommand instanceof NoteCommand);

        noteCommand = menuParser.parseCommand(topicList, "note delete");
        assertTrue(noteCommand instanceof NoteCommand);

        Command exitCommand = menuParser.parseCommand(topicList, "exit");
        assertTrue(exitCommand instanceof ExitCommand);

        Command statsCommand = menuParser.parseCommand(topicList, "stats");
        assertTrue(statsCommand instanceof StatsCommand);
    }

    @Test
    void menuParser_nullTopicList_expectAssertionErrorThrown() {
        BookmarkList bookmarks = createTestBookmarkList();
        TopicList topicList = null;
        MenuParser menuParser = new MenuParser(bookmarks);

        assertThrows(AssertionError.class, () -> {
            menuParser.parseCommand(topicList, "stats");
        });
    }
}
