package seedu.eduke8.stats;


import seedu.eduke8.common.Displayable;
import seedu.eduke8.question.Question;
import seedu.eduke8.question.QuestionList;
import seedu.eduke8.topic.Topic;

import java.util.ArrayList;


public class UserStatsCalculator extends StatsCalculator {

    public UserStatsCalculator() {
        super();
    }

    // idea is to get the list of topics from topicList, iterate each topic and access their questionList
    // Then in each questionList, iterate through each question and see if wasAnsweredCorrectly and if
    // hint was shown. Answered correction without hint = +2 points, else with hint shown = +1 point
    // can use topicalStatsCalculator method to help
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

    // just get the count of the questionList * 2
    public int calculateTotalPointsAvailable() {
        int totalPointsAvailable = calculateTotalQuestionCount() * POINTS_PER_QUESTION;

        return totalPointsAvailable;
    }

    public int calculateOverallProgressionLevelPercentage() {
        return calculateProgressionLevelPercentage(calculateTotalPointsEarned(),
                calculateTotalPointsAvailable());
    }

    // determines if progression is over 50%, print diff messages
    public boolean isProgressionOverHalf() {
        return calculateOverallProgressionLevelPercentage() >= 50;
    }
}
