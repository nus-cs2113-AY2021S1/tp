package seedu.eduke8;

import org.json.simple.parser.ParseException;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.question.Question;
import seedu.eduke8.question.QuestionList;
import seedu.eduke8.storage.TopicsStorage;
import seedu.eduke8.topic.Topic;
import seedu.eduke8.topic.TopicList;
import seedu.eduke8.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.eduke8.exception.ExceptionMessages.ERROR_STORAGE_FAIL;

public class StatsCalculator {
    protected static final String DATA_PATH = "data/main/topics.json";
    protected static final int POINTS_PER_QUESTION = 2;
    protected static final int POINTS_FOR_CORRECT_ANSWER_WITHOUT_HINT = 2;
    protected static final int POINTS_FOR_CORRECT_ANSWER_WITH_HINT = 1;
    protected static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    protected TopicsStorage topicsStorage;
    protected Ui ui;

    protected TopicList topicList;
    protected ArrayList<Displayable> topics;

    public StatsCalculator() {
        topicsStorage = new TopicsStorage(DATA_PATH);
        ui = new Ui();

        try {
            topicList = new TopicList(topicsStorage.load());
        } catch (ParseException | IOException e) {
            ui.printError(ERROR_STORAGE_FAIL);
            LOGGER.log(Level.WARNING, "Error reading or writing local files.");
        }

        topics  = topicList.getInnerList();
    }

    protected int calculatePointsEarnedForQuestion(Question question) {
        if (!question.wasAnsweredCorrectly()) {
            return 0;
        } else if (question.wasAnsweredCorrectly() && question.wasHintShown()) {
            return POINTS_FOR_CORRECT_ANSWER_WITH_HINT;
        } else {
            return POINTS_FOR_CORRECT_ANSWER_WITHOUT_HINT;
        }
    }

    protected int calculateProgressionLevelPercentage(int userProgress, int total) {
        int progressionLevel = userProgress / total;

        int progressionLevelPercentage = progressionLevel * 100;

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
