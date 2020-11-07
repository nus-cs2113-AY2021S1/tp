package seedu.eduke8.parser;

import org.junit.jupiter.api.Test;
import seedu.eduke8.Eduke8Test;
import seedu.eduke8.bookmark.BookmarkList;
import seedu.eduke8.command.*;
import seedu.eduke8.option.OptionList;
import seedu.eduke8.question.Question;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class QuizParserTest extends Eduke8Test {

    private OptionList optionList;
    private QuizParser quizParser;

    QuizParserTest() {
        optionList = createTestOptionList();
        BookmarkList bookmarkList = createTestBookmarkList();
        quizParser = new QuizParser(bookmarkList);
        Question question = createTestQuestion(PLACEHOLDER_QUESTION_ONE_DESCRIPTION);
        quizParser.setQuestion(question, TIMER);
    }

    @Test
    void quizParser_wrongStringInput_expectIncorrectCommandReturn1() {
        Command badCommand = quizParser.parseCommand(optionList, "one");
        assertTrue(badCommand instanceof IncorrectCommand);
    }

    @Test
    void quizParser_wrongStringInput_expectIncorrectCommandReturn2() {
        Command badCommand = quizParser.parseCommand(optionList, "back");
        assertTrue(badCommand instanceof IncorrectCommand);
    }

    @Test
    void quizParser_wrongStringInput_expectIncorrectCommandReturn3() {
        Command badCommand = quizParser.parseCommand(optionList, null);
        assertTrue(badCommand instanceof IncompleteCommand);
    }

    @Test
    void quizParser_wrongStringInput_expectIncorrectCommandReturn4() {
        Command badCommand = quizParser.parseCommand(optionList, "6");
        assertTrue(badCommand instanceof IncorrectCommand);
    }

    @Test
    void quizParser_correctStringInput_expectAnswerCommand() {
        Command resultCommand = quizParser.parseCommand(optionList, "1");
        assertTrue(resultCommand instanceof AnswerCommand);
    }

    @Test
    void quizParser_correctStringInput_expectHintCommand() {
        Command resultCommand = quizParser.parseCommand(optionList, "hint");
        assertTrue(resultCommand instanceof HintCommand);
    }

    @Test
    void quizParser_correctStringInput_expectBookmarkCommand() {
        Command resultCommand = quizParser.parseCommand(optionList, "bookmark");
        assertTrue(resultCommand instanceof BookmarkCommand);
    }
}

