package seedu.eduke8.stats;


import seedu.eduke8.common.Displayable;
import seedu.eduke8.question.Question;
import seedu.eduke8.question.QuestionList;
import seedu.eduke8.topic.Topic;

import java.util.ArrayList;


public class UserStatsCalculator extends StatsCalculator {

    public UserStatsCalculator(ArrayList<Displayable> topics) {
        super(topics);
    }


    /**
     * Iterates through the attempted questions in all topics and calculate the points earned by the user
     * based on the scoring system of E-Duke-8.
     *
     * @return Points earned, from all topics, by the user from correct answers in past quiz attempts.
     */
    public int calculateTotalPointsEarned() {
        int totalPointsGained = 0;

        for (Displayable topic : topics) {
            QuestionList topicQuestionList = ((Topic) topic).getQuestionList();

            ArrayList<Displayable> questions = topicQuestionList.getInnerList();

            for (int i = 0; i < questions.size(); i++) {
                Question question = (Question) questions.get(i);
                totalPointsGained += calculatePointsEarnedForQuestion(question);
            }
        }
        return totalPointsGained;
    }

    /**
     * Calculates the total possible amount of points that the user can earn from all of the questions available
     * in all of the topics available in E-Duke-8, based on the scoring system.
     *
     * @return Total available points the user can earn from all of the questions from all topics.
     */
    public int calculateTotalPointsAvailable() {
        int totalPointsAvailable = calculateTotalQuestionCount() * POINTS_PER_QUESTION;

        return totalPointsAvailable;
    }

    /**
     * Calculates the percentage of progress in E-Duke-8 based on the points earned by the user and
     * the total possible amount of points he / she could have earned from all the questions in E-Duke-8.
     *
     * @return A percentage indicating user's progress in E-Duke-8.
     */
    public int calculateOverallProgressionPercentage() {
        return calculateProgressionPercentage(calculateTotalPointsEarned(),
                calculateTotalPointsAvailable());
    }

    public boolean isProgressionOverHalf() {
        return calculateOverallProgressionPercentage() >= 50;
    }
}
