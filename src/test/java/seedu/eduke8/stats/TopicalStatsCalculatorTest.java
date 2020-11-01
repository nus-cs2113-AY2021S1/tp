package seedu.eduke8.stats;

import org.junit.jupiter.api.Test;
import seedu.eduke8.Eduke8Test;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.question.Question;
import seedu.eduke8.question.QuestionList;
import seedu.eduke8.topic.Topic;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TopicalStatsCalculatorTest extends Eduke8Test {
    private static final int NUMBER_OF_QUESTIONS_IN_TEST_TOPIC = 5;
    private static final int NUMBER_OF_QUESTIONS_ATTEMPTED = 3;
    private static final int NUMBER_OF_QUESTIONS_CORRECTLY_ANSWERED = 2;
    private static final int NUMBER_OF_QUESTIONS_WITH_HINT_USED = 1;


    @Test
    void topicalStatsCalculatorConstructor_nullTopicArgument_expectsAssertionError() {
        assertThrows(AssertionError.class, () -> {
            TopicalStatsCalculator topicalStatsCalculator = new TopicalStatsCalculator(null);
        });
    }

    @Test
    void calculateTopicalQuestionsAttemptCount_threeAttemptedQuestions_returnsCountOfThree() {
        // 5 questions with three attempted, two correct, one correct question had hint shown, a total of 3 points
        Topic topic = createAttemptedTopic();

        TopicalStatsCalculator topicalStatsCalculator = new TopicalStatsCalculator(topic);

        assertEquals(NUMBER_OF_QUESTIONS_ATTEMPTED, topicalStatsCalculator.calculateTopicalQuestionsAttemptCount());
    }

    @Test
    void calculateTopicalQuestionsCorrectCount_twoCorrectlyAnsweredQuestions_returnsCountOfTwo() {
        // 5 questions with three attempted, two correct, one correct question had hint shown, a total of 3 points
        Topic topic = createAttemptedTopic();

        TopicalStatsCalculator topicalStatsCalculator = new TopicalStatsCalculator(topic);

        assertEquals(NUMBER_OF_QUESTIONS_CORRECTLY_ANSWERED,
                topicalStatsCalculator.calculateTopicalQuestionsCorrectCount());
    }

    @Test
    void calculateTopicalHintUsage_oneHintUsed_returnsCountOfOne() {
        // 5 questions with three attempted, two correct, one correct question had hint shown, a total of 3 points
        Topic topic = createAttemptedTopic();

        TopicalStatsCalculator topicalStatsCalculator = new TopicalStatsCalculator(topic);

        assertEquals(NUMBER_OF_QUESTIONS_WITH_HINT_USED,
                topicalStatsCalculator.calculateTopicalHintUsage());
    }

    @Test
    void calculateTopicalPointsEarned_twoCorrectlyAnsweredQuestionsWithOneHintUsed_returnsCorrectCount() {
        // 5 questions with three attempted, two correct, one correct question had hint shown, a total of 3 points
        Topic topic = createAttemptedTopic();

        TopicalStatsCalculator topicalStatsCalculator = new TopicalStatsCalculator(topic);

        // Points for one correct question without hint shown and one correct question with hint shown
        int pointsForTwoCorrectQuestionsOneHintUsed = calculatePointsForTwoCorrectQuestionsOneHintUsed();

        assertEquals(pointsForTwoCorrectQuestionsOneHintUsed, topicalStatsCalculator.calculateTopicalPointsEarned());
    }

    @Test
    void calculateTopicalPointsAvailable_fiveQuestions_returnsCorrectCount() {
        // 5 questions with three attempted, two correct, one correct question had hint shown, a total of 3 points
        Topic topic = createAttemptedTopic();

        TopicalStatsCalculator topicalStatsCalculator = new TopicalStatsCalculator(topic);

        assertEquals(NUMBER_OF_QUESTIONS_IN_TEST_TOPIC * POINTS_PER_QUESTION,
                topicalStatsCalculator.calculateTopicalPointsAvailable());
    }

    @Test
    void calculateTopicalProgressionPercentage_inputTestTopic_returnsCorrectPercentage() {
        // 5 questions with three attempted, two correct, one correct question had hint shown, a total of 3 points
        Topic topic = createAttemptedTopic();

        TopicalStatsCalculator topicalStatsCalculator = new TopicalStatsCalculator(topic);

        // Points for one correct question without hint shown and one correct question with hint shown
        int pointsForTwoCorrectQuestionsOneHintUsed = calculatePointsForTwoCorrectQuestionsOneHintUsed();

        // Total points the user can earn from all 5 questions in the topic
        int totalPointsAvailable = POINTS_PER_QUESTION  * NUMBER_OF_QUESTIONS_IN_TEST_TOPIC;

        // Percentage of progression the user can expect when he answered two out of six questions correctly
        int expectedPercentage = (int) (((double) pointsForTwoCorrectQuestionsOneHintUsed
                / totalPointsAvailable) * 100);

        assertEquals(expectedPercentage, topicalStatsCalculator.calculateTopicalProgressionPercentage());
    }


    // Creates one topic with 5 questions, three questions attempted, two questions correctly answered,
    // where one of the two correctly answered questions is with hint shown, totalling to 3 points earned.
    private Topic createAttemptedTopic() {
        Question question1 = createTestQuestion(PLACEHOLDER_QUESTION_ONE_DESCRIPTION);
        Question question2 = createTestQuestion(PLACEHOLDER_QUESTION_TWO_DESCRIPTION);
        Question question3 = createTestQuestion(PLACEHOLDER_QUESTION_THREE_DESCRIPTION);

        question1.markAsShown();
        question2.markAsShown();
        question3.markAsShown();

        question1.markAsAnsweredCorrectly();
        question2.markAsAnsweredCorrectly();

        question1.getHint().getDescription();

        Question question4 = createTestQuestion(PLACEHOLDER_QUESTION_FOUR_DESCRIPTION);
        Question question5 = createTestQuestion(PLACEHOLDER_QUESTION_FIVE_DESCRIPTION);

        ArrayList<Displayable> questions = new ArrayList<>(
                Arrays.asList(question1, question2, question3, question4, question5)
        );
        QuestionList questionList = new QuestionList(questions);
        Topic topic1 = new Topic(PLACEHOLDER_TOPIC_ONE_DESCRIPTION, questionList);

        return topic1;
    }

    private int calculatePointsForTwoCorrectQuestionsOneHintUsed() {
        return POINTS_FOR_CORRECT_ANSWER_WITHOUT_HINT_SHOWN + POINTS_FOR_CORRECT_ANSWER_WITH_HINT_SHOWN;
    }
}