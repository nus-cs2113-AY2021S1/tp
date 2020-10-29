package seedu.eduke8.stats;

import seedu.eduke8.common.Displayable;
import seedu.eduke8.topic.Topic;
import seedu.eduke8.topic.TopicList;
import seedu.eduke8.ui.Ui;

import java.util.ArrayList;


public class Stats {

    private UserStatsCalculator userStatsCalculator;

    private ArrayList<Displayable> topics;

    public Stats(TopicList topicList) {
        topics = topicList.getInnerList();
        assert topics != null;
        userStatsCalculator = new UserStatsCalculator(topics);
    }

    /**
     * Displays user's statistics for past quiz attempts. Overall statistics for all the past quiz
     * attempts will be printed out first, followed by topic-level statistics.
     */
    public void showStatsToUser(Ui ui) {
        ui.printPointSystemRules();
        showPointsEarned(ui);
        showTotalProgression(ui);
        showTopicalProgression(ui);
    }

    private void showPointsEarned(Ui ui) {
        ui.printPointsEarned(userStatsCalculator.calculateTotalPointsEarned(),
               userStatsCalculator.calculateTotalPointsAvailable());
    }


    private void showTotalProgression(Ui ui) {
        ui.printTotalProgression(userStatsCalculator.calculateOverallProgressionPercentage(),
                userStatsCalculator.isProgressionOverHalf());
    }

    private void showTopicalProgression(Ui ui) {
        ui.printTopicProgressionHeader();

        // Loop through the different topics to get the stats from each of them individually
        for (Displayable topic : topics) {
            TopicalStatsCalculator specificTopicStatsCalculator = new TopicalStatsCalculator((Topic) topic);

            ui.printTopicDescriptionForStats(topic);

            ui.printTopicCompletionLevel(
                    specificTopicStatsCalculator.calculateTopicalQuestionsAttemptCount(),
                    specificTopicStatsCalculator.getTopicQuestionsCount());

            ui.printTopicAccuracyLevel(specificTopicStatsCalculator.calculateTopicalQuestionsCorrectCount(),
                    specificTopicStatsCalculator.calculateTopicalQuestionsAttemptCount());

            ui.printTopicalHintUsage(specificTopicStatsCalculator.calculateTopicalHintUsage());

            ui.printTopicalPoints(specificTopicStatsCalculator.calculateTopicalPointsEarned(),
                    specificTopicStatsCalculator.calculateTopicalPointsAvailable(),
                    specificTopicStatsCalculator.calculateTopicalProgressionPercentage());
        }
    }
}
