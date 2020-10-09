package seedu.duke.command.topiccommand;

import seedu.duke.card.Subject;
import seedu.duke.card.SubjectList;
import seedu.duke.card.Topic;
import seedu.duke.command.taskcommand.TaskCommand;
import seedu.duke.exception.NoSubjectException;
import seedu.duke.exception.TaskException;
import seedu.duke.parser.TaskParser;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

public class ReturnTopicCommand extends TopicCommand {
    private String fullcommand;
    private TaskList tasks;

    public ReturnTopicCommand(String fullcommand) {
        this.fullcommand = fullcommand;
    }

    public Topic execute(Subject subject) throws NoSubjectException {
        String[] message = this.fullcommand.split(" ");
        //Subject currentSubject = new Subject(message[1]);
        if (message[1].isEmpty()) {
            throw new NoSubjectException();
        }
        for (Topic topic : subject.getTopics().getList()) {
            if (topic.getTitle().equals(message[1])) {
                return topic;
            }
        }
        throw new NoSubjectException();
    }

    public void goToTopic(Topic topic, Subject subject) {
        Ui.printGoToTopic(topic);
        boolean isSubjectExit = false;
        while (!isSubjectExit) {
            // TODO: implement the same thing in Duke run in here, but for Tasks instead, Suganda fix this shit please
            String fullCommand = Ui.readCommand();
            TaskCommand c = TaskParser.parse(fullCommand);
            isSubjectExit = c.isExit();
            try {
                c.execute(tasks);
            } catch (TaskException e) {
                Ui.printError();
            }
        }
        Ui.printExitToMain();

    }

    public boolean isExit() {
        return false;
    }
}
