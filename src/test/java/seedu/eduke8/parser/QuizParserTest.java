package seedu.eduke8.parser;

import org.junit.jupiter.api.Test;
import seedu.eduke8.command.AnswerCommand;
import seedu.eduke8.command.Command;
import seedu.eduke8.command.HintCommand;
import seedu.eduke8.command.IncorrectCommand;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.explanation.Explanation;
import seedu.eduke8.hint.Hint;
import seedu.eduke8.option.Option;
import seedu.eduke8.option.OptionList;
import seedu.eduke8.question.Question;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class QuizParserTest {

    @Test
    public void quizParser_wrongStringInput_expectIncorrectCommandReturn() {
        ArrayList<Displayable> optionsArrayList = new ArrayList<>();
        OptionList optionList = new OptionList(optionsArrayList);
        QuizParser quizParser = new QuizParser();

        Command badCommand = quizParser.parseCommand(optionList, "one");
        assertTrue(badCommand instanceof IncorrectCommand);

        badCommand = quizParser.parseCommand(optionList, "back");
        assertTrue(badCommand instanceof IncorrectCommand);
    }

    @Test
    public void quizParser_correctStringInput_success() {
        Hint hint = new Hint("description");s
        ArrayList<Displayable> optionsArrayList = new ArrayList<>();
        OptionList optionList = new OptionList(optionsArrayList);
        Explanation explanation = new Explanation("explanation");
        QuizParser quizParser = new QuizParser();
        Question question = new Question("description", optionList, hint, explanation);

        quizParser.setQuestion(question);
        Command resultCommand = quizParser.parseCommand(optionList, "hint");
        assertTrue(resultCommand instanceof HintCommand);

        optionsArrayList.add(new Option("1"));
        // optionList.add(new Option("1"));
        optionList = new OptionList(optionsArrayList);
        quizParser.setQuestion(question);
        resultCommand = quizParser.parseCommand(optionList, "1");
        assertTrue(resultCommand instanceof AnswerCommand);

    }
}

