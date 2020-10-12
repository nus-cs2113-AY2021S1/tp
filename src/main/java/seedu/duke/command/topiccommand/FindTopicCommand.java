package seedu.duke.command.topiccommand;

import seedu.duke.card.Subject;
import seedu.duke.card.SubjectList;
import seedu.duke.card.Topic;
import seedu.duke.card.TopicList;
import seedu.duke.command.subjectcommand.SubjectCommand;
import seedu.duke.command.taskcommand.FindTaskCommand;
import seedu.duke.ui.Ui;

public class FindTopicCommand extends TopicCommand {
    private String fullcommand;

    public FindTopicCommand(String fullcommand) {
        this.fullcommand = fullcommand;
    }

    public Topic execute(Subject subject) {
        TopicList topicList = subject.getTopics();
        String[] message = this.fullcommand.split(" ");
        Ui.printFindTopic(topicList,message[1]);
        FindTaskCommand findTaskCommand = new FindTaskCommand(this.fullcommand);
        findTaskCommand.execute(subject.getTasks());
        return null;
    }

    /**
     * Checks whether the the user exits the program.
     * @return <code>true</code> if user exits the program
     */
    public boolean isExit() {
        return false;
    }
}
