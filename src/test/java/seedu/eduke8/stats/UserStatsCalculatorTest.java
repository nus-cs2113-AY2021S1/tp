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

class UserStatsCalculatorTest extends Eduke8Test {
    private static final int NUMBER_OF_TEST_TOPICS = 2;
    private static final int NUMBER_OF_QUESTIONS_IN_TEST_TOPIC = 3;


    @Test
    void calculateTotalPointsEarned_twoCorrectlyAnsweredQuestionsAmongTopics_returnsCorrectScore() {

        ArrayList<Displayable> topics = createTwoTopicsWithTwoOfSixQuestionsAnsweredCorrectly();

        UserStatsCalculator userStatsCalculator = new UserStatsCalculator(topics);

        int pointsForTwoCorrectAnswersWithoutHint = calculatePointsForTwoCorrectAnswersWithoutHint();

        assertEquals(pointsForTwoCorrectAnswersWithoutHint, userStatsCalculator.calculateTotalPointsEarned());
    }

    // Tests calculateTotalPointsAvailable() as well
    @Test
    void calculateOverallProgressionPercentage_twoCorrectlyAnsweredQuestionsOutOfSix_returnsCorrectPercentage() {

        ArrayList<Displayable> topics = createTwoTopicsWithTwoOfSixQuestionsAnsweredCorrectly();

        UserStatsCalculator userStatsCalculator = new UserStatsCalculator(topics);

        int pointsForTwoCorrectAnswersWithoutHint = calculatePointsForTwoCorrectAnswersWithoutHint();

        // Total points the user can earn from two topics of three questions each
        int totalPointsAvailable = POINTS_PER_QUESTION * NUMBER_OF_TEST_TOPICS * NUMBER_OF_QUESTIONS_IN_TEST_TOPIC;

        // Percentage of progression the user can expect when he answered two out of six questions correctly
        int expectedPercentage = (int) (((double) pointsForTwoCorrectAnswersWithoutHint
                / totalPointsAvailable) * 100);

        assertEquals(expectedPercentage, userStatsCalculator.calculateOverallProgressionPercentage());

    }


    // Creates two topics with 3 questions each, but with only one topic
    // having 2 questions correctly answered without hint, totalling to 4 points
    private ArrayList<Displayable> createTwoTopicsWithTwoOfSixQuestionsAnsweredCorrectly() {
        Question question1 = createTestQuestion(PLACEHOLDER_QUESTION_ONE_DESCRIPTION);
        Question question2 = createTestQuestion(PLACEHOLDER_QUESTION_TWO_DESCRIPTION);
        Question question3 = createTestQuestion(PLACEHOLDER_QUESTION_THREE_DESCRIPTION);

        question1.markAsAnsweredCorrectly();
        question2.markAsAnsweredCorrectly();

        ArrayList<Displayable> questions = new ArrayList<>(
                Arrays.asList(question1, question2, question3)
        );
        QuestionList questionList = new QuestionList(questions);
        Topic topic1 = new Topic(PLACEHOLDER_TOPIC_ONE_DESCRIPTION, questionList);

        // Creates another topic with 3 unanswered questions
        Topic topic2 = createTestTopic(PLACEHOLDER_TOPIC_TWO_DESCRIPTION);

        ArrayList<Displayable> topics = new ArrayList<>(
                Arrays.asList(topic1, topic2)
        );

        return topics;
    }

    private int calculatePointsForTwoCorrectAnswersWithoutHint() {
        return 2 * POINTS_FOR_CORRECT_ANSWER_WITHOUT_HINT_SHOWN;
    }
}