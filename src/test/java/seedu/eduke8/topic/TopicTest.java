package seedu.eduke8.topic;

import org.junit.jupiter.api.Test;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.exception.Eduke8Exception;
import seedu.eduke8.hint.Hint;
import seedu.eduke8.option.Option;
import seedu.eduke8.option.OptionList;
import seedu.eduke8.question.Question;
import seedu.eduke8.question.QuestionList;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class TopicTest {

    private static final String QUESTION_ONE_DESCRIPTION = "First Question";
    private static final String QUESTION_TWO_DESCRIPTION = "Second Question";
    private static final String QUESTION_THREE_DESCRIPTION = "Third Question";
    private static final String HINT = "Hint";
    private static final String INPUT = "JUnit: Basic";

    @Test
    void getsTopicDescription_topicDescription_returnsTopicDescription() throws Eduke8Exception {
        String input = INPUT;
        Topic topic = new Topic(input, createQuestionList());

        assertEquals(input, topic.getDescription());
    }

    @Test
    void getsQuestionList_questionList_returnsQuestionList() throws Eduke8Exception {
        String input = INPUT;
        QuestionList questionList = createQuestionList();
        Topic topic = new Topic(input, questionList);

        assertEquals(questionList, topic.getQuestionList());
    }

    private QuestionList createQuestionList() {
        ArrayList<Displayable> questions = new ArrayList<>();
        Question question1 = createTestQuestion(QUESTION_ONE_DESCRIPTION);
        Question question2 = createTestQuestion(QUESTION_TWO_DESCRIPTION);
        Question question3 = createTestQuestion(QUESTION_THREE_DESCRIPTION);

        questions.add(question1);
        questions.add(question2);
        questions.add(question3);

        QuestionList questionList = new QuestionList(questions);
        return questionList;
    }

    private Question createTestQuestion(String description) {
        Option option1 = new Option("test1");
        Option option2 = new Option("test2");
        OptionList optionList = new OptionList();
        optionList.add(option1);
        optionList.add(option2);

        Hint hint = new Hint(HINT);

        Question question = new Question(description, optionList, hint);

        return question;
    }
}
