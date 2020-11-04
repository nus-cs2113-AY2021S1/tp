package seedu.revised.command.topiccommand;

import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.command.flashcardcommand.FlashcardCommand;
import seedu.revised.command.flashcardcommand.ListAllFlashcardCommand;
import seedu.revised.exception.topicexception.NoTopicException;
import seedu.revised.list.SubjectList;
import seedu.revised.parser.FlashcardParser;
import seedu.revised.ui.Ui;

public class AccessTopicCommand extends TopicCommand {
    private String fullcommand;
    private SubjectList subjectList;

    public AccessTopicCommand(String fullcommand) {
        this.fullcommand = fullcommand;
    }

    public void execute(Subject subject) throws NoTopicException {
        String[] message = this.fullcommand.split(" ",2);
        Topic gotoTopic = null;
        if (message.length == 1 || message[1].isEmpty()) {
            throw new NoTopicException(Ui.TOPIC_NOT_FOUND_EXCEPTION);
        }
        for (Topic topic : subject.getTopics().getList()) {
            if (topic.getTitle().equals(message[1].strip())) {
                gotoTopic = topic;
                break;
            }
        }
        if (gotoTopic == null) {
            throw new NoTopicException(Ui.TOPIC_NOT_FOUND_EXCEPTION);
        }

        goToTopic(gotoTopic);
    }

    private void goToTopic(Topic topic) {
        Ui.printGoToTopic(topic);
        boolean isTopicExit = false;
        while (!isTopicExit) {
            try {
                String fullCommand = Ui.readCommand();
                FlashcardCommand c = FlashcardParser.parse(fullCommand);
                isTopicExit = c.isExit();
                if (c instanceof ListAllFlashcardCommand) {
                    ((ListAllFlashcardCommand) c).setSubjectList(subjectList);
                }
                c.execute(topic);
            } catch (NumberFormatException e) {
                Ui.printErrorMsg(Ui.INDEX_FORMAT_EXCEPTION);
            } catch (IndexOutOfBoundsException e) {
                Ui.printErrorMsg(Ui.INDEX_OUT_OF_BOUND_EXCEPTION);
            } catch (Exception e) {
                Ui.printError(e);
            }
        }
        Ui.printBackToTopicsAndTasks();
    }

    public void setSubjectList(SubjectList subjectList) {
        this.subjectList = subjectList;
    }

    public boolean isExit() {
        return false;
    }
}
