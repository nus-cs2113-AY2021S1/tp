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
        userStatsCalculator = new UserStatsCalculator(topicList);
    }

    public void showStatsToUser(Ui ui) {
        ui.printPointSystemRules();
        showPointsEarned(ui);
        showTotalProgression(ui);
        showTopicalProgression(ui);
    }

    private void showPointsEarned(Ui ui) {
        ui.showPointsEarned(userStatsCalculator.calculateTotalPointsEarned(),
               userStatsCalculator.calculateTotalPointsAvailable());
    }


    private void showTotalProgression(Ui ui) {
        ui.showTotalProgression(userStatsCalculator.calculateOverallProgressionLevelPercentage(),
                userStatsCalculator.isProgressionOverHalf());
    }

    private void showTopicalProgression(Ui ui) {
        ui.printTopicProgressionHeader();

        // Loop through the different topics to get the stats from each of them individually
        for (Displayable topic : topics) {
            TopicalStatsCalculator specificTopicStatsCalculator = new TopicalStatsCalculator((Topic) topic);

            ui.printTopicDescriptionForStats(topic);

            ui.showTopicalQuestionsCompletionLevel(
                    specificTopicStatsCalculator.calculateTopicalQuestionsAttemptCount(),
                    specificTopicStatsCalculator.getTopicQuestionsCount());

            ui.showTopicAccuracyLevel(specificTopicStatsCalculator.calculateTopicalQuestionsCorrectCount(),
                    specificTopicStatsCalculator.calculateTopicalQuestionsAttemptCount());

            ui.showTopicalHintUsage(specificTopicStatsCalculator.calculateTopicalHintUsage());

            ui.showTopicalPoints(specificTopicStatsCalculator.calculateTopicalPointsEarned(),
                    specificTopicStatsCalculator.calculateTopicalPointsAvailable(),
                    specificTopicStatsCalculator.calculateTopicalProgressionPercentage());
        }
    }
}
