package seedu.eduke8.question;

import org.junit.jupiter.api.Test;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.exception.Eduke8Exception;
import seedu.eduke8.hint.Hint;
import seedu.eduke8.option.Option;
import seedu.eduke8.option.OptionList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuizQuestionsManagerTest {

    private static final String PLACEHOLDER_QUESTION_ONE_DESCRIPTION = "First question description.";
    private static final String PLACEHOLDER_QUESTION_TWO_DESCRIPTION = "Second question description.";
    private static final String PLACEHOLDER_QUESTION_THREE_DESCRIPTION = "Second question description.";
    private static final String PLACEHOLDER_HINT_DESCRIPTION = "Please check the textbook page 88";
    private static final int TOPIC_QUESTIONS_COUNT = 3;
    private static final int QUIZ_QUESTIONS_COUNT = 2;


    @Test
    void quizQuestionsManagerConstructor_twoQuizQuestionsFromThreeTopicQuestions_returnsCountOfTwo()
            throws Eduke8Exception {
        QuestionList topicQuestionList = createQuestionListWithThreeUniqueQuestions();

        QuizQuestionsManager quizQuestionsManager =
                new QuizQuestionsManager(QUIZ_QUESTIONS_COUNT, topicQuestionList.getInnerList());

        assertEquals(QUIZ_QUESTIONS_COUNT, quizQuestionsManager.getQuizQuestionsCount());
    }

    @Test
    void getNextQuestion() {
    }

    @Test
    void getCurrentQuestionNumber() {
    }

    @Test
    void areAllQuestionsAnswered() {
    }


    // Creates a question list that holds two questions, each with a unique description
    private QuestionList createQuestionListWithThreeUniqueQuestions() {
        ArrayList<Displayable> questions = new ArrayList<>();
        Question question1 = createTestQuestionWithCustomDescription(PLACEHOLDER_QUESTION_ONE_DESCRIPTION);
        Question question2 = createTestQuestionWithCustomDescription(PLACEHOLDER_QUESTION_TWO_DESCRIPTION);
        Question question3 = createTestQuestionWithCustomDescription(PLACEHOLDER_QUESTION_THREE_DESCRIPTION);

        questions.add(question1);
        questions.add(question2);
        questions.add(question3);

        QuestionList questionList = new QuestionList(questions);
        return questionList;
    }

    // Creates a question with a specific description, 2 standard options and a standard hint
    private Question createTestQuestionWithCustomDescription(String description) {
        Option option1 = new Option("test1");
        Option option2 = new Option("test2");
        OptionList optionList = new OptionList();
        optionList.add(option1);
        optionList.add(option2);

        String inputHintDescription = PLACEHOLDER_HINT_DESCRIPTION;
        Hint hint = new Hint(inputHintDescription);

        Question question = new Question(description, optionList, hint);

        return question;
    }
}