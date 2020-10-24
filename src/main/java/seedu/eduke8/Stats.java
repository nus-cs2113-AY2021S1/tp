package seedu.eduke8;

import seedu.eduke8.common.Displayable;
import seedu.eduke8.topic.Topic;
import seedu.eduke8.ui.Ui;

import java.util.ArrayList;

public class Stats {

    private UserStatsCalculator userStatsCalculator;
    private TopicalStatsCalculator topicalStatsCalculator;

    public Stats() {
        userStatsCalculator = new UserStatsCalculator();
        topicalStatsCalculator = new TopicalStatsCalculator();
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
        // need to loop through all topics and pass into the topicCalculator
        // then loop through and print them one by one.

        ArrayList<Displayable> topics = topicalStatsCalculator.getTopics();
        ui.printTopicProgressionHeader();

        // Loop through the different topics to get the stats from each of them individually
        for (Displayable topic : topics) {
            TopicalStatsCalculator specificTopicStatsCalculator = new TopicalStatsCalculator((Topic) topic);

            ui.printTopicDescriptionForStats(topic);

            ui.showTopicalQuestionsCompletionLevel(
                    specificTopicStatsCalculator.calculateTopicalQuestionsAttemptCount(),
                    specificTopicStatsCalculator.getTopicQuestionsCount());

            // not using this as getting the percentage might cause a divisionByZero exception
           /* ui.showTopicAccuracyLevel(specificTopicStatsCalculator.calculateTopicalQuestionsCorrectCount(),
                    specificTopicStatsCalculator.calculateTopicalQuestionsAttemptCount(),
                    specificTopicStatsCalculator.calculateTopicAccuracyPercentage()); */

            ui.showTopicAccuracyLevel(specificTopicStatsCalculator.calculateTopicalQuestionsCorrectCount(),
                    specificTopicStatsCalculator.calculateTopicalQuestionsAttemptCount());

            ui.showTopicalHintUsage(specificTopicStatsCalculator.calculateTopicalHintUsage());

            ui.showTopicalPoints(specificTopicStatsCalculator.calculateTopicalPointsEarned(),
                    specificTopicStatsCalculator.calculateTopicalPointsAvailable(),
                    specificTopicStatsCalculator.calculateTopicalProgressionPercentage());

        }

    }

}
