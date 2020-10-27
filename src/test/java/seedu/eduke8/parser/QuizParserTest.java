package seedu.eduke8.parser;

import org.junit.jupiter.api.Test;
import seedu.eduke8.Eduke8Test;
import seedu.eduke8.bookmark.BookmarkList;
import seedu.eduke8.command.AnswerCommand;
import seedu.eduke8.command.Command;
import seedu.eduke8.command.HintCommand;
import seedu.eduke8.command.IncorrectCommand;
import seedu.eduke8.option.OptionList;
import seedu.eduke8.question.Question;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class QuizParserTest extends Eduke8Test {

    @Test
    public void quizParser_wrongStringInput_expectIncorrectCommandReturn() {
        BookmarkList bookmarks = createTestBookmarkList();
        OptionList optionList = createTestOptionList();
        QuizParser quizParser = new QuizParser(bookmarks);

        Command badCommand = quizParser.parseCommand(optionList, "one");
        assertTrue(badCommand instanceof IncorrectCommand);

        badCommand = quizParser.parseCommand(optionList, "back");
        assertTrue(badCommand instanceof IncorrectCommand);
    }

    @Test
    public void quizParser_correctStringInput_success() {
        BookmarkList bookmarks = createTestBookmarkList();
        OptionList optionList = createTestOptionList();
        QuizParser quizParser = new QuizParser(bookmarks);
        Question question = createTestQuestion(PLACEHOLDER_QUESTION_ONE_DESCRIPTION);

        quizParser.setQuestion(question);
        Command resultCommand = quizParser.parseCommand(optionList, "hint");
        assertTrue(resultCommand instanceof HintCommand);

        resultCommand = quizParser.parseCommand(optionList, "1");
        assertTrue(resultCommand instanceof AnswerCommand);

    }
}

