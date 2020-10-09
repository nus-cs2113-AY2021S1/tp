package seedu.duke.command.subjectcommand;

import seedu.duke.card.Subject;
import seedu.duke.card.Topic;
import seedu.duke.card.TopicList;
import seedu.duke.command.taskcommand.TaskCommand;
import seedu.duke.command.topiccommand.ReturnTopicCommand;
import seedu.duke.command.topiccommand.TopicCommand;
import seedu.duke.exception.NoSubjectException;
import seedu.duke.card.SubjectList;
import seedu.duke.exception.RepeatedSubjectException;
import seedu.duke.exception.TaskException;
import seedu.duke.parser.SubjectParser;
import seedu.duke.parser.TaskParser;
import seedu.duke.parser.TopicParser;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class ReturnSubjectCommand extends SubjectCommand {
    private String fullcommand;
    private TaskList taskList;
    private TopicList topicList;

    public ReturnSubjectCommand(String fullcommand) {
        this.fullcommand = fullcommand;
    }

    public Subject execute(SubjectList subjectList) throws NoSubjectException {
        String[] message = this.fullcommand.split(" ");
        //Subject currentSubject = new Subject(message[1]);
        if (message[1].isEmpty()) {
            throw new NoSubjectException();
        }
        for (Subject subject : subjectList.getList()) {
            if (subject.getTitle().equals(message[1])) {
                return subject;
            }
        }
        throw new NoSubjectException();
    }

    public void goToSubject(Subject subject, TaskList tasks) {
        Ui.printGoToSubject(subject);
        topicList = subject.getTopics();
        boolean isSubjectExit = false;
        while (!isSubjectExit) {
            try {
                String fullCommand = Ui.readCommand();
                TopicCommand c = TopicParser.parse(fullCommand);
                if (c instanceof ReturnTopicCommand) {
                    Topic topic = c.execute(subject);
                    ((ReturnTopicCommand) c).goToTopic(topic, subject);
                } else {
                    c.execute(subject);
                }
                isSubjectExit = c.isExit();
                // TODO: implement the storage methods for Topic
//                topics.saveSubject(topicStorage.getFileName());
//            } catch (IOException e) {
//                Ui.printWritingError();
            } catch (NumberFormatException e) {
                Ui.printIndexError();
            } catch (NoSubjectException e) {
                Ui.printNoTopicError();
            } catch (RepeatedSubjectException e) {
                Ui.printRepeatedTopicError();
            } catch (IndexOutOfBoundsException e) {
                Ui.printOutOfBoundsError();
            }
        }
        Ui.printExitToMain();
    }

    public boolean isExit() {
        return false;
    }
}
