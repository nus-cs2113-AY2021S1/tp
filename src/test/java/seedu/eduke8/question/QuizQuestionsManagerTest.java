package seedu.eduke8.question;

import org.junit.jupiter.api.Test;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.exception.Eduke8Exception;
import seedu.eduke8.hint.Hint;
import seedu.eduke8.option.Option;
import seedu.eduke8.option.OptionList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


class QuizQuestionsManagerTest {

    private static final String PLACEHOLDER_QUESTION_ONE_DESCRIPTION = "First question description.";
    private static final String PLACEHOLDER_QUESTION_TWO_DESCRIPTION = "Second question description.";
    private static final String PLACEHOLDER_QUESTION_THREE_DESCRIPTION = "Second question description.";
    private static final String PLACEHOLDER_HINT_DESCRIPTION = "Please check the textbook page 88";
    private static final int TOPIC_QUESTIONS_COUNT = 3;
    private static final int QUIZ_QUESTIONS_COUNT = 2;


    // This test tests for getQuizQuestionsCount() method too
    @Test
    void quizQuestionsManagerConstructor_twoQuizQuestionsFromThreeTopicQuestions_returnsCountOfTwo()
            throws Eduke8Exception {
        QuestionList topicQuestionList = createQuestionListWithThreeUniqueQuestions();

        // Creating a quiz with 2 questions selected from a total of 3 questions from the topic
        QuizQuestionsManager quizQuestionsManager =
                new QuizQuestionsManager(QUIZ_QUESTIONS_COUNT, topicQuestionList.getInnerList());

        assertEquals(QUIZ_QUESTIONS_COUNT, quizQuestionsManager.getQuizQuestionsCount());
    }

    @Test
    void quizQuestionsManagerConstructor_lessThanZeroNumberOfQuizQuestions_expectsException() {
        QuestionList topicQuestionList = createQuestionListWithThreeUniqueQuestions();

        assertThrows(Eduke8Exception.class, () -> {
            QuizQuestionsManager quizQuestionsManager =
                    new QuizQuestionsManager(-1, topicQuestionList.getInnerList());
        });
    }

    // Number of quiz questions requested by user exceeds the number of questions in the topic
    @Test
    void quizQuestionsManagerConstructor_invalidNumberOfQuizQuestions_expectsException() {
        QuestionList topicQuestionList = createQuestionListWithThreeUniqueQuestions();

        assertThrows(Eduke8Exception.class, () -> {
            QuizQuestionsManager quizQuestionsManager =
                    new QuizQuestionsManager(TOPIC_QUESTIONS_COUNT + 1, topicQuestionList.getInnerList());
        });
    }


    // This test tests for getCurrentQuestionNumber() method too
    @Test
    void getNextQuestion_currentQuestionNumberAtZero_returnsCurrentQuestionNumberAtOne()
            throws Eduke8Exception {
        QuestionList topicQuestionList = createQuestionListWithThreeUniqueQuestions();

        // Creating a quiz with 2 questions selected from a total of 3 questions from the topic
        QuizQuestionsManager quizQuestionsManager =
                new QuizQuestionsManager(QUIZ_QUESTIONS_COUNT, topicQuestionList.getInnerList());

        quizQuestionsManager.getNextQuestion();
        assertEquals(1, quizQuestionsManager.getCurrentQuestionNumber());
    }

    @Test
    void areAllQuestionsAnswered_fullyAnsweredQuiz_expectsTrue() throws Eduke8Exception {
        QuestionList topicQuestionList = createQuestionListWithThreeUniqueQuestions();

        // Creating a quiz with 2 questions selected from a total of 3 questions from the topic
        QuizQuestionsManager quizQuestionsManager =
                new QuizQuestionsManager(QUIZ_QUESTIONS_COUNT, topicQuestionList.getInnerList());

        quizQuestionsManager.getNextQuestion();     // Displays first question to user
        quizQuestionsManager.getNextQuestion();     // Displays second question to user

        assertTrue(quizQuestionsManager.areAllQuestionsAnswered());
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
