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
    }

    @Test
    void menuParser_wrongStringInputNoTime_expectIncorrectCommand2() {
        BookmarkList bookmarks = createTestBookmarkList();
        TopicList topicList = createTestTopicList();
        MenuParser menuParser = new MenuParser(bookmarks);

        Command quizCommand = menuParser.parseCommand(topicList, "quiz t/OOP n/2");
        assertTrue(quizCommand instanceof IncorrectCommand);
    }

    @Test
    void menuParser_wrongStringInputNoTime_expectIncorrectCommand3() {
        BookmarkList bookmarks = createTestBookmarkList();
        TopicList topicList = createTestTopicList();
        MenuParser menuParser = new MenuParser(bookmarks);

        Command noteBadCommand = menuParser.parseCommand(topicList, "note wrong");
        assertTrue(noteBadCommand instanceof IncorrectCommand);
    }

    @Test
    void menuParser_wrongStringInputNoTime_expectIncorrectCommand4() {
        BookmarkList bookmarks = createTestBookmarkList();
        TopicList topicList = createTestTopicList();
        MenuParser menuParser = new MenuParser(bookmarks);

        Command quizBadCommand = menuParser.parseCommand(topicList, "quiz t/OOP n/1 s/-1");
        assertTrue(quizBadCommand instanceof IncorrectCommand);
    }

    @Test
    void menuParser_wrongStringInputNoTime_expectIncorrectCommand5() {
        BookmarkList bookmarks = createTestBookmarkList();
        TopicList topicList = createTestTopicList();
        MenuParser menuParser = new MenuParser(bookmarks);

        Command badCommand = menuParser.parseCommand(topicList, "wrong command all the way");
        assertTrue(badCommand instanceof IncorrectCommand);
    }

    @Test
    void menuParser_wrongStringInputNoTime_expectIncorrectCommand6() {
        BookmarkList bookmarks = createTestBookmarkList();
        TopicList topicList = createTestTopicList();
        MenuParser menuParser = new MenuParser(bookmarks);

        Command quizBadCommand = menuParser.parseCommand(topicList, "quiz s/20 tnoslash n/1");
        assertTrue(quizBadCommand instanceof IncorrectCommand);
    }

    @Test
    void menuParser_wrongStringInputNoTime_expectIncorrectCommand7() {
        BookmarkList bookmarks = createTestBookmarkList();
        TopicList topicList = createTestTopicList();
        MenuParser menuParser = new MenuParser(bookmarks);

        Command quizBadCommand = menuParser.parseCommand(topicList, "quiz tnoslash s/20  n/1");
        assertTrue(quizBadCommand instanceof IncorrectCommand);
    }

    @Test
    void menuParser_wrongStringInputNoTime_expectIncorrectCommand8() {
        BookmarkList bookmarks = createTestBookmarkList();
        TopicList topicList = createTestTopicList();
        MenuParser menuParser = new MenuParser(bookmarks);

        Command quizBadCommand = menuParser.parseCommand(topicList, "quiz t/OOP bad n/1");
        assertTrue(quizBadCommand instanceof IncorrectCommand);
    }

    @Test
    void menuParser_wrongStringInputNoTime_expectIncorrectCommand9() {
        BookmarkList bookmarks = createTestBookmarkList();
        TopicList topicList = createTestTopicList();
        MenuParser menuParser = new MenuParser(bookmarks);

        Command quizBadCommand = menuParser.parseCommand(topicList, "quiz n/1 bad t/OOP");
        assertTrue(quizBadCommand instanceof IncorrectCommand);
    }


    @Test
    void menuParser_wrongStringInputNoTime_expectIncorrectCommand10() {
        BookmarkList bookmarks = createTestBookmarkList();
        TopicList topicList = createTestTopicList();
        MenuParser menuParser = new MenuParser(bookmarks);

        Command noteBadCommand = menuParser.parseCommand(topicList, "quiz n/1 bad t/OOP");
        assertTrue(noteBadCommand instanceof IncorrectCommand);
    }

    @Test
    void menuParser_correctStringInput_expectQuizCommand1() {
        BookmarkList bookmarks = createTestBookmarkList();
        TopicList topicList = createTestTopicList();
        MenuParser menuParser = new MenuParser(bookmarks);

        Command quizCommand = menuParser.parseCommand(topicList, "quiz n/2 s/20 t/OOP");
        assertTrue(quizCommand instanceof QuizCommand);
    }

    @Test
    void menuParser_correctStringInput_expectQuizCommand2() {
        BookmarkList bookmarks = createTestBookmarkList();
        TopicList topicList = createTestTopicList();
        MenuParser menuParser = new MenuParser(bookmarks);

        Command quizCommand = menuParser.parseCommand(topicList, "quiz s/20 t/OOP n/2");
        assertTrue(quizCommand instanceof QuizCommand);
    }

    @Test
    void menuParser_correctStringInput_expectQuizCommand3() {
        BookmarkList bookmarks = createTestBookmarkList();
        TopicList topicList = createTestTopicList();
        MenuParser menuParser = new MenuParser(bookmarks);

        Command quizCommand = menuParser.parseCommand(topicList, "quiz t/OOP n/2 s/20");
        assertTrue(quizCommand instanceof QuizCommand);
    }

    @Test
    void menuParser_correctStringInput_expectQuizCommand4() {
        BookmarkList bookmarks = createTestBookmarkList();
        TopicList topicList = createTestTopicList();
        MenuParser menuParser = new MenuParser(bookmarks);

        Command quizCommand = menuParser.parseCommand(topicList, "quiz s/20 n/2 t/OOP");
        assertTrue(quizCommand instanceof QuizCommand);
    }

    @Test
    void menuParser_correctStringInput_expectQuizCommand5() {
        BookmarkList bookmarks = createTestBookmarkList();
        TopicList topicList = createTestTopicList();
        MenuParser menuParser = new MenuParser(bookmarks);

        Command quizCommand = menuParser.parseCommand(topicList, "quiz n/2 t/OOP s/20");
        assertTrue(quizCommand instanceof QuizCommand);
    }

    @Test
    void menuParser_correctStringInput_expectQuizCommand6() {
        BookmarkList bookmarks = createTestBookmarkList();
        TopicList topicList = createTestTopicList();
        MenuParser menuParser = new MenuParser(bookmarks);

        Command quizCommand = menuParser.parseCommand(topicList, "quiz t/OOP s/20 n/2");
        assertTrue(quizCommand instanceof QuizCommand);
    }

    @Test
    void menuParser_correctStringInput_expectAboutCommand() {
        BookmarkList bookmarks = createTestBookmarkList();
        TopicList topicList = createTestTopicList();
        MenuParser menuParser = new MenuParser(bookmarks);

        Command aboutCommand = menuParser.parseCommand(topicList, "about");
        assertTrue(aboutCommand instanceof AboutCommand);
    }

    @Test
    void menuParser_correctStringInput_expectHelpCommand() {
        BookmarkList bookmarks = createTestBookmarkList();
        TopicList topicList = createTestTopicList();
        MenuParser menuParser = new MenuParser(bookmarks);

        Command helpCommand = menuParser.parseCommand(topicList, "help");
        assertTrue(helpCommand instanceof HelpCommand);
    }

    @Test
    void menuParser_correctStringInput_expectTopicsCommand() {
        BookmarkList bookmarks = createTestBookmarkList();
        TopicList topicList = createTestTopicList();
        MenuParser menuParser = new MenuParser(bookmarks);

        Command topicsCommand = menuParser.parseCommand(topicList, "topics");
        assertTrue(topicsCommand instanceof TopicsCommand);
    }

    @Test
    void menuParser_correctStringInput_expectTextbookCommand() {
        BookmarkList bookmarks = createTestBookmarkList();
        TopicList topicList = createTestTopicList();
        MenuParser menuParser = new MenuParser(bookmarks);

        Command textbookCommand = menuParser.parseCommand(topicList, "textbook");
        assertTrue(textbookCommand instanceof TextbookCommand);
    }

    @Test
    void menuParser_correctStringInput_expectBookmarkCommand() {
        BookmarkList bookmarks = createTestBookmarkList();
        TopicList topicList = createTestTopicList();
        MenuParser menuParser = new MenuParser(bookmarks);

        Command bookmarkCommand = menuParser.parseCommand(topicList, "bookmark");
        assertTrue(bookmarkCommand instanceof BookmarkCommand);
    }

    @Test
    void menuParser_correctStringInput_expectNoteCommand() {
        BookmarkList bookmarks = createTestBookmarkList();
        TopicList topicList = createTestTopicList();
        MenuParser menuParser = new MenuParser(bookmarks);

        Command noteCommand = menuParser.parseCommand(topicList, "note list");
        assertTrue(noteCommand instanceof NoteCommand);
    }

    @Test
    void menuParser_correctStringInput_expectNoteCommand1() {
        BookmarkList bookmarks = createTestBookmarkList();
        TopicList topicList = createTestTopicList();
        MenuParser menuParser = new MenuParser(bookmarks);

        Command noteCommand = menuParser.parseCommand(topicList, "note add");
        assertTrue(noteCommand instanceof NoteCommand);
    }

    @Test
    void menuParser_correctStringInput_expectNoteCommand2() {
        BookmarkList bookmarks = createTestBookmarkList();
        TopicList topicList = createTestTopicList();
        MenuParser menuParser = new MenuParser(bookmarks);

        Command noteCommand = menuParser.parseCommand(topicList, "note delete");
        assertTrue(noteCommand instanceof NoteCommand);
    }

    @Test
    void menuParser_correctStringInput_expectExitCommand() {
        BookmarkList bookmarks = createTestBookmarkList();
        TopicList topicList = createTestTopicList();
        MenuParser menuParser = new MenuParser(bookmarks);

        Command exitCommand = menuParser.parseCommand(topicList, "exit");
        assertTrue(exitCommand instanceof ExitCommand);
    }

    @Test
    void menuParser_correctStringInput_expectStatsCommand() {
        BookmarkList bookmarks = createTestBookmarkList();
        TopicList topicList = createTestTopicList();
        MenuParser menuParser = new MenuParser(bookmarks);

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
