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

    // Gets the count of the questionList * 2
    public int calculateTotalPointsAvailable() {
        int totalPointsAvailable = calculateTotalQuestionCount() * POINTS_PER_QUESTION;

        return totalPointsAvailable;
    }

    public int calculateOverallProgressionPercentage() {
        return calculateProgressionPercentage(calculateTotalPointsEarned(),
                calculateTotalPointsAvailable());
    }

    // Determines if progression is over 50%, print diff messages for each section
    public boolean isProgressionOverHalf() {
        return calculateOverallProgressionPercentage() >= 50;
    }
}
