package seedu.eduke8.stats;

import seedu.eduke8.common.Displayable;
import seedu.eduke8.question.Question;
import seedu.eduke8.question.QuestionList;
import seedu.eduke8.topic.Topic;


public class TopicalStatsCalculator extends StatsCalculator {

    private QuestionList topicQuestionList;

    public TopicalStatsCalculator(Topic topic) {
        this.topicQuestionList = topic.getQuestionList();
    }


    // check each qn and see if they are done
    public int calculateTopicalQuestionsAttemptCount() {
        int questionAttemptCount = 0;
        for (Displayable question : topicQuestionList.getInnerList()) {
            if (question.wasShown()) {
                questionAttemptCount++;
            }
        }
        return questionAttemptCount;
    }

    public int getTopicQuestionsCount() {
        return topicQuestionList.getCount();
    }

    // check each qn and see if they are correct
    public int calculateTopicalQuestionsCorrectCount() {
        int questionCorrectCount = 0;
        for (Displayable question : topicQuestionList.getInnerList()) {
            if (((Question) question).wasAnsweredCorrectly()) {
                questionCorrectCount++;
            }
        }
        return questionCorrectCount;
    }

    // check each qn and calculate number of hints used
    public int calculateTopicalHintUsage() {
        int hintUsageCount = 0;
        for (Displayable question : topicQuestionList.getInnerList()) {
            if (((Question) question).wasHintShown()) {
                hintUsageCount++;
            }
        }
        return hintUsageCount;
    }


    // The 2 methods below maybe could just extend from StatsCalulator
    public int calculateTopicalPointsEarned() {
        int pointsEarned = 0;
        for (Displayable question : topicQuestionList.getInnerList()) {
            pointsEarned += calculatePointsEarnedForQuestion((Question) question);
        }
        return pointsEarned;
    }

    public int calculateTopicalPointsAvailable() {
        int pointsAvailable = topicQuestionList.getCount() * POINTS_PER_QUESTION;

        return pointsAvailable;
    }


    public int calculateTopicalProgressionPercentage() {
        return calculateProgressionLevelPercentage(calculateTopicalPointsEarned(),
                calculateTopicalPointsAvailable());
    }
}
