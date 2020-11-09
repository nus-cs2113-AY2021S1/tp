package seedu.eduke8.stats;

import seedu.eduke8.common.Displayable;
import seedu.eduke8.question.Question;
import seedu.eduke8.question.QuestionList;
import seedu.eduke8.topic.Topic;

import java.util.ArrayList;


public class StatsCalculator {
    protected static final int POINTS_PER_QUESTION = 2;
    protected static final int POINTS_FOR_CORRECT_ANSWER_WITHOUT_HINT_SHOWN = 2;
    protected static final int POINTS_FOR_CORRECT_ANSWER_WITH_HINT_SHOWN = 1;

    protected ArrayList<Displayable> topics;

    public StatsCalculator(ArrayList<Displayable> topics) {
        assert topics != null;
        this.topics = topics;
    }

    // For TopicalStatsCalculator
    public StatsCalculator() {
    }


    protected int calculatePointsEarnedForQuestion(Question question) {
        assert question != null;

        if (!question.wasAnsweredCorrectly()) {
            return 0;
        } else if (question.wasAnsweredCorrectly() && question.wasHintShown()) {
            return POINTS_FOR_CORRECT_ANSWER_WITH_HINT_SHOWN;
        } else {
            return POINTS_FOR_CORRECT_ANSWER_WITHOUT_HINT_SHOWN;
        }
    }

    /**
     * Given the user's progress and the available progress, calculates the percentage of progress for the user.
     * User progress is expected to be 0 or more, and total available progress is expected to always be bigger than 0
     * User progress is also expected to always be lesser than or equal to the total available progress.
     *
     * @return Percentage of progression of the user given his or her progress and the total available progress.
     */
    protected int calculateProgressionPercentage(int userProgress, int totalAvailableProgress) {
        assert userProgress >= 0;
        assert totalAvailableProgress > 0;
        assert userProgress <= totalAvailableProgress;

        double progressionLevel = (double) userProgress / totalAvailableProgress;

        int progressionLevelPercentage = (int) (progressionLevel * 100);

        return progressionLevelPercentage;
    }

    protected int calculateTotalQuestionCount() {
        int totalQuestionCount = 0;

        for (Displayable topic : topics) {
            QuestionList topicQuestionList = ((Topic) topic).getQuestionList();
            totalQuestionCount += topicQuestionList.getCount();
        }

        return totalQuestionCount;
    }
}
