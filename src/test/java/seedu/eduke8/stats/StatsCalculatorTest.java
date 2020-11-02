package seedu.eduke8.stats;

import org.junit.jupiter.api.Test;
import seedu.eduke8.Eduke8Test;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.question.Question;
import seedu.eduke8.question.QuestionList;
import seedu.eduke8.topic.Topic;
import seedu.eduke8.topic.TopicList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StatsCalculatorTest extends Eduke8Test {
    private static final String PLACEHOLDER_QUESTION_DESCRIPTION = "This is a question description.";
    private static final int HALF_PROGRESS_LEVEL_SCORE = 5;
    private static final int TOTAL_PROGRESS_LEVEL_SCORE = 10;

    private StatsCalculator statsCalculator;

    StatsCalculatorTest() {
        statsCalculator = new StatsCalculator();
    }

    @Test
    void statsCalculatorConstructorWithOneParameter_nullTopicsArgument_expectsAssertionError() {
        assertThrows(AssertionError.class, () -> {
            new StatsCalculator(null);
        });
    }

    @Test
    void calculatePointsEarnedForQuestion_questionIncorrectlyAnswered_returnsZero() {
        Question question = createTestQuestion(PLACEHOLDER_QUESTION_DESCRIPTION);

        assertEquals(POINTS_FOR_INCORRECT_ANSWER, statsCalculator.calculatePointsEarnedForQuestion(question));
    }

    @Test
    void calculatePointsEarnedForQuestion_questionCorrectlyAnsweredWithHintShown_returnsOne() {
        Question question = createTestQuestion(PLACEHOLDER_QUESTION_DESCRIPTION);
        question.markAsAnsweredCorrectly();
        question.getHint().getDescription();

        assertEquals(POINTS_FOR_CORRECT_ANSWER_WITH_HINT_SHOWN,
                statsCalculator.calculatePointsEarnedForQuestion(question));
    }

    @Test
    void calculatePointsEarnedForQuestion_questionCorrectlyAnsweredWithoutHintShown_returnsTwo() {
        Question question = createTestQuestion(PLACEHOLDER_QUESTION_DESCRIPTION);
        question.markAsAnsweredCorrectly();

        assertEquals(POINTS_FOR_CORRECT_ANSWER_WITHOUT_HINT_SHOWN,
                statsCalculator.calculatePointsEarnedForQuestion(question));
    }

    // Negative partition for the calculatePointsEarnedForQuestion method
    @Test
    void calculatePointsEarnedForQuestion_nullQuestionArgument_expectsAssertionError() {
        assertThrows(AssertionError.class, () -> {
            statsCalculator.calculatePointsEarnedForQuestion(null);
        });
    }


    @Test
    void calculateProgressionPercentage_validProgress_returnsPercentageAsFifty() {
        assertEquals(50, statsCalculator.calculateProgressionPercentage(HALF_PROGRESS_LEVEL_SCORE,
                TOTAL_PROGRESS_LEVEL_SCORE));
    }

    @Test
    void calculateProgressionPercentage_invalidUserProgress_expectsAssertionError() {
        assertThrows(AssertionError.class, () -> {
            statsCalculator.calculateProgressionPercentage(-1, TOTAL_PROGRESS_LEVEL_SCORE);
        });
    }

    @Test
    void calculateProgressionPercentage_invalidTotalAvailableProgress_expectsAssertionError() {
        assertThrows(AssertionError.class, () -> {
            statsCalculator.calculateProgressionPercentage(HALF_PROGRESS_LEVEL_SCORE, -1);
        });
    }

    @Test
    void calculateProgressionPercentage_userProgressExceedTotalAvailableProgress_expectsAssertionError() {
        assertThrows(AssertionError.class, () -> {
            statsCalculator.calculateProgressionPercentage(TOTAL_PROGRESS_LEVEL_SCORE + 1,
                    TOTAL_PROGRESS_LEVEL_SCORE);
        });
    }

    @Test
    void calculateTotalQuestionCount_twoTopicsWithThreeQuestionsEach_returnsCorrectCount() {
        StatsCalculator twoTopicsStatsCalculator = new StatsCalculator(createTestTopicList().getInnerList());

        int expectedQuestionCount = 0;
        TopicList testTopicList = createTestTopicList();

        for (Displayable topic : testTopicList.getInnerList()) {
            QuestionList topicQuestionList = ((Topic) topic).getQuestionList();
            expectedQuestionCount += topicQuestionList.getCount();
        }

        assertEquals(expectedQuestionCount, twoTopicsStatsCalculator.calculateTotalQuestionCount());
    }
}