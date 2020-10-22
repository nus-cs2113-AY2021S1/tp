package seedu.eduke8.question;

import org.junit.jupiter.api.Test;
import seedu.eduke8.Eduke8Test;
import seedu.eduke8.exception.Eduke8Exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class QuizQuestionsManagerTest extends Eduke8Test {

    private static final int TOPIC_QUESTIONS_COUNT = 3;
    private static final int QUIZ_QUESTIONS_COUNT = 2;
    private static final int QUIZ_QUESTIONS_COUNT_MAX = 3;


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
    void quizQuestionsManagerConstructor_threeQuizQuestionsFromThreeTopicQuestions_returnsCountOfTwo()
            throws Eduke8Exception {
        QuestionList topicQuestionList = createQuestionListWithThreeUniqueQuestions();

        // Creating a quiz with 3 questions selected from a total of 3 questions from the topic
        QuizQuestionsManager quizQuestionsManager =
                new QuizQuestionsManager(QUIZ_QUESTIONS_COUNT_MAX, topicQuestionList.getInnerList());

        assertEquals(QUIZ_QUESTIONS_COUNT_MAX, quizQuestionsManager.getQuizQuestionsCount());
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
}
