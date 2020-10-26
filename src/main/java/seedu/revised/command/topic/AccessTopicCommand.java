package seedu.revised.command.topic;

import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.command.flashcard.FlashcardCommand;
import seedu.revised.exception.topic.NoTopicException;
import seedu.revised.parser.FlashcardParser;
import seedu.revised.list.TaskList;
import seedu.revised.ui.Ui;

public class AccessTopicCommand extends TopicCommand {
    private String fullcommand;
    private TaskList tasks;

    public AccessTopicCommand(String fullcommand) {
        this.fullcommand = fullcommand;
    }

    public void execute(Subject subject) throws NoTopicException {
        String[] message = this.fullcommand.split(" ", 2);
        Topic gotoTopic = null;
        if (message[1].isEmpty()) {
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

    public boolean isExit() {
        return false;
    }
}
