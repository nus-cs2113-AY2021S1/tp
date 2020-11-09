package seedu.eduke8.command;

import seedu.eduke8.bookmark.BookmarkList;
import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.exception.Eduke8Exception;
import seedu.eduke8.quiz.SingleTopicQuiz;
import seedu.eduke8.topic.Topic;
import seedu.eduke8.topic.TopicList;
import seedu.eduke8.ui.Ui;

import java.util.logging.Logger;

/**
 * A specific command that manages the construction of quizzes for the user.
 */
public class QuizCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private TopicList topicList;
    private int numOfQuestions;
    private String topicName;
    private Ui ui;
    private BookmarkList bookmarks;
    private int userTimer;

    public QuizCommand(TopicList topicList, int numOfQuestions, String topicName, Ui ui, BookmarkList bookmarks,
                       int userTimer) {
        super();
        assert topicList != null;

        this.topicList = topicList;
        this.numOfQuestions = numOfQuestions;
        this.topicName = topicName;
        this.ui = ui;
        this.bookmarks = bookmarks;
        this.userTimer = userTimer;
    }

    @Override
    public void execute(DisplayableList displayableList, Ui ui) {
        try {
            Topic topic = (Topic) topicList.find(topicName);
            SingleTopicQuiz singleTopicQuiz = new SingleTopicQuiz(topic, numOfQuestions, bookmarks, userTimer);
            singleTopicQuiz.startQuiz(ui);
        } catch (Eduke8Exception e) {
            ui.printError(e.getMessage());
        }
    }
}

