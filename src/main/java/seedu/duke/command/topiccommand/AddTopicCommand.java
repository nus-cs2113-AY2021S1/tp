package seedu.duke.command.topiccommand;

import seedu.duke.card.Subject;
import seedu.duke.card.SubjectList;
import seedu.duke.card.Topic;
import seedu.duke.card.TopicList;
import seedu.duke.exception.NoSubjectException;
import seedu.duke.exception.RepeatedSubjectException;
import seedu.duke.ui.Ui;

/**
 * Adds an instance of the <code>Topic</code> class into a <code>TopicList</code>.
 */
public class AddTopicCommand extends TopicCommand {
    private String fullCommand;

    public AddTopicCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public Topic execute(Subject subject) throws NoSubjectException, RepeatedSubjectException {
        int startOfMessage = 4;
        int endOfMessage = fullCommand.length();
        TopicList topicList = subject.getTopics();
        if (endOfMessage <= startOfMessage) {
            throw new NoSubjectException();
        }
        String title = fullCommand.substring(startOfMessage,endOfMessage);
        if (title.isEmpty()) {
            throw new NoSubjectException();
        }
        for (Topic topic : topicList.getList()) {
            if (topic.getTitle().equals(title)) {
                throw new RepeatedSubjectException();
            }
        }
        Topic t = new Topic(title);
        topicList.add(t);
        t.printTopic(topicList);
        return t;
    }

    /**
     * Checks whether the the user exits the program.
     * @return <code>true</code> if user exits the program
     */
    public boolean isExit() {
        return false;
    }
}
