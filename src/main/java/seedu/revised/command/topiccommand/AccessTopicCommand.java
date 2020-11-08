package seedu.revised.command.topiccommand;

import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.command.flashcardcommand.FlashcardCommand;
import seedu.revised.command.flashcardcommand.ListAllFlashcardCommand;
import seedu.revised.command.subjectcommand.AddSubjectCommand;
import seedu.revised.exception.topicexception.NoTopicException;
import seedu.revised.list.SubjectList;
import seedu.revised.parser.FlashcardParser;
import seedu.revised.ui.Ui;

import java.util.logging.Logger;

public class AccessTopicCommand extends TopicCommand {
    private static final Logger logger = Logger.getLogger(AddSubjectCommand.class.getName());
    private final String fullCommand;
    private SubjectList subjectList;

    public AccessTopicCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Adds an instance of the <code>Subject</code> class into a <code>SubjectList</code>.
     *
     * @param subject The subject that the user is currently looking at
     * @throws NoTopicException Thrown if the command is in the wrong syntax
     */
    public void execute(Subject subject) throws NoTopicException {
        logger.info("Begin checking command to get the title of the topic to be added.");
        String[] message = this.fullCommand.split(" ",2);
        Topic t = null;
        if (message.length == 1 || message[1].isEmpty()) {
            throw new NoTopicException(Ui.NO_TOPIC_EXCEPTION);
        }
        for (Topic topic : subject.getTopics().getList()) {
            if (topic.getTitle().equals(message[1].strip())) {
                t = topic;
                break;
            }
        }
        if (t == null) {
            throw new NoTopicException(Ui.TOPIC_NOT_FOUND_EXCEPTION);
        }
        assert t.getTitle().equals(message[1].strip());
        goToTopic(t);
    }

    /**
     * Accesses a topic, handles exceptions thrown by topic-level commands.
     *
     * @param topic Topic that is accessed
     */
    private void goToTopic(Topic topic) {
        logger.info("Begin accessing a Topic to get Topic details");
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
        logger.info("Finished access into a topic, going back to subject.");
    }

    public void setSubjectList(SubjectList subjectList) {
        this.subjectList = subjectList;
    }

    public boolean isExit() {
        return false;
    }
}
