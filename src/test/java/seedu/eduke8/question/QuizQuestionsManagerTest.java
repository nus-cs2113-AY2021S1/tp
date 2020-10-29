package seedu.eduke8.question;

import org.junit.jupiter.api.Test;
import seedu.eduke8.Eduke8Test;
import seedu.eduke8.exception.Eduke8Exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class QuizQuestionsManagerTest extends Eduke8Test {

    private static final int TOPIC_QUESTIONS_COUNT = 3;
    private static final int QUIZ_QUESTIONS_COUNT = 3;

    // Within acceptable range or questions, positive partition
    @Test
    void quizQuestionsManagerConstructor_threeQuizQuestionsFromThreeTopicQuestions_returnsCountOfThree()
            throws Eduke8Exception {
        QuestionList topicQuestionList = createTestQuestionList();

        // Creating a quiz with 3 questions selected from a total of 3 questions from the topic
        QuizQuestionsManager quizQuestionsManager =
                new QuizQuestionsManager(QUIZ_QUESTIONS_COUNT, topicQuestionList.getInnerList());

        assertEquals(QUIZ_QUESTIONS_COUNT, quizQuestionsManager.getQuizQuestionsCount());
    }


    // Below acceptable range of questions, negative partition
    @Test
    void quizQuestionsManagerConstructor_zeroQuizQuestions_expectsException() {
        QuestionList topicQuestionList = createTestQuestionList();

        assertThrows(Eduke8Exception.class, () -> {
            QuizQuestionsManager quizQuestionsManager =
                    new QuizQuestionsManager(0, topicQuestionList.getInnerList());
        });
    }

    // Above acceptable range of questions, negative partition
    @Test
    void quizQuestionsManagerConstructor_numberOfQuizQuestionsExceedNumberOfTopicQuestions_expectsException() {
        QuestionList topicQuestionList = createTestQuestionList();

        assertThrows(Eduke8Exception.class, () -> {
            QuizQuestionsManager quizQuestionsManager =
                    new QuizQuestionsManager(TOPIC_QUESTIONS_COUNT + 1, topicQuestionList.getInnerList());
        });
    }


    @Test
    void getNextQuestion_currentQuestionNumberAtZero_returnsCurrentQuestionNumberAtOne()
            throws Eduke8Exception {
        QuestionList topicQuestionList = createTestQuestionList();

        // Creating a quiz with 2 questions selected from a total of 3 questions from the topic
        QuizQuestionsManager quizQuestionsManager =
                new QuizQuestionsManager(QUIZ_QUESTIONS_COUNT, topicQuestionList.getInnerList());

        quizQuestionsManager.getNextQuestion();

        assertEquals(1, quizQuestionsManager.getCurrentQuestionNumber());
    }

    @Test
    void areAllQuestionsAnswered_fullyAnsweredQuiz_expectsTrue() throws Eduke8Exception {
        QuestionList topicQuestionList = createTestQuestionList();

        // Creating a quiz with 3 questions selected from a total of 3 questions from the topic
        QuizQuestionsManager quizQuestionsManager =
                new QuizQuestionsManager(QUIZ_QUESTIONS_COUNT, topicQuestionList.getInnerList());

        quizQuestionsManager.getNextQuestion();     // Displays first question to user
        quizQuestionsManager.getNextQuestion();     // Displays second question to user
        quizQuestionsManager.getNextQuestion();     // Displays last question to user

        assertTrue(quizQuestionsManager.areAllQuestionsAnswered());
    }

    @Test
    void areAllQuestionsAnswered_quizWithUnansweredQuestions_expectsFalse() throws Eduke8Exception {
        QuestionList topicQuestionList = createTestQuestionList();

        // Creating a quiz with 3 questions selected from a total of 3 questions from the topic
        QuizQuestionsManager quizQuestionsManager =
                new QuizQuestionsManager(QUIZ_QUESTIONS_COUNT, topicQuestionList.getInnerList());

        quizQuestionsManager.getNextQuestion();     // Only display the first question out of 3 questions

        assertFalse(quizQuestionsManager.areAllQuestionsAnswered());
    }
}
