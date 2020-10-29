package seedu.eduke8.stats;

import seedu.eduke8.common.Displayable;
import seedu.eduke8.question.Question;
import seedu.eduke8.question.QuestionList;
import seedu.eduke8.topic.Topic;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;


public class TopicalStatsCalculator extends StatsCalculator {

    private QuestionList topicQuestionList;

    public TopicalStatsCalculator(Topic topic) {
        assert topic != null;
        this.topicQuestionList = topic.getQuestionList();
    }

    /**
     * Returns the number of questions attempted by the user from previous quizzes for the particular topic.
     *
     * @return Number of questions attempted by the user for a particular topic.
     */
    public int calculateTopicalQuestionsAttemptCount() {
        return getTopicalAttemptedQuestions().size();
    }

    /**
     * Iterates through the questions in the topic and returns an ArrayList of questions
     * that have been shown to the user before in previous quizzes.
     *
     * @return Number of questions attempted by the user for a particular topic.
     */
    public ArrayList<Displayable> getTopicalAttemptedQuestions() {
        return (ArrayList<Displayable>) topicQuestionList.getInnerList().stream()
                .filter(Displayable::wasShown)
                .collect(toList());
    }

    public int getTopicQuestionsCount() {
        return topicQuestionList.getCount();
    }

    /**
     * Iterates through the attempted questions for a particular topic and calculate
     * the number of questions that are marked as correct.
     *
     * @return Number of questions correctly answered by the user for a particular topic.
     */
    public int calculateTopicalQuestionsCorrectCount() {
        ArrayList<Displayable> attemptedQuestions = getTopicalAttemptedQuestions();
        int questionCorrectCount = 0;
        for (Displayable question : attemptedQuestions) {
            if (((Question) question).wasAnsweredCorrectly()) {
                questionCorrectCount++;
            }
        }
        return questionCorrectCount;
    }


    /**
     * Iterates through the attempted questions for a particular topic and calculate the total number
     * of hints used for them from previous quiz attempts.
     *
     * @return Number of hints used by the user in the quizzes the user has done before.
     */
    public int calculateTopicalHintUsage() {
        ArrayList<Displayable> attemptedQuestions = getTopicalAttemptedQuestions();
        int hintUsageCount = 0;
        for (Displayable question : attemptedQuestions) {
            if (((Question) question).wasHintShown()) {
                hintUsageCount++;
            }
        }
        return hintUsageCount;
    }

    /**
     * Iterates through the attempted questions in a topic and calculate the points earned by the user
     * based on the scoring system of E-Duke-8.
     *
     * @return Points earned, for a particular topic, by the user from correct answers in past quiz attempts.
     */
    public int calculateTopicalPointsEarned() {
        ArrayList<Displayable> attemptedQuestions = getTopicalAttemptedQuestions();
        int pointsEarned = 0;
        for (Displayable question : attemptedQuestions) {
            pointsEarned += calculatePointsEarnedForQuestion((Question) question);
        }
        return pointsEarned;
    }

    /**
     * Calculates the total possible amount of points that the user can earn from all of the questions available
     * in a topic, based on the scoring system of E-Duke-8.
     *
     * @return Total available points the user can earn from all of the questions in a certain topic.
     */
    public int calculateTopicalPointsAvailable() {
        int pointsAvailable = getTopicQuestionsCount() * POINTS_PER_QUESTION;

        return pointsAvailable;
    }


    /**
     * Calculates the percentage of progress of a particular topic based on the points earned by the user and
     * the total possible amount of points he / she could have earned.
     *
     * @return A percentage indicating user's progress of a certain topic.
     */
    public int calculateTopicalProgressionPercentage() {
        return calculateProgressionPercentage(calculateTopicalPointsEarned(),
                calculateTopicalPointsAvailable());
    }
}
