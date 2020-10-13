package seedu.duke.command.topiccommand;

import seedu.duke.card.Subject;
import seedu.duke.card.Topic;
import seedu.duke.exception.NoSubjectException;
import seedu.duke.ui.Ui;

public class ResultTopicCommand extends TopicCommand {
    private String fullcommand;

    public ResultTopicCommand(String fullcommand) {
        this.fullcommand = fullcommand;

    }

    public Topic execute(Subject subject) throws NoSubjectException {


        String[] message = this.fullcommand.split(" ");
        Topic quizTopic = null;
        for (Topic topic : subject.getTopics().getList()) {
            if (topic.toString().contains(message[1])) {
                quizTopic = topic;
            }
        }
        if (quizTopic == null) {
            throw new NoSubjectException();
        }
        Ui.printTopicResults(quizTopic);
        return null;
    }
}
