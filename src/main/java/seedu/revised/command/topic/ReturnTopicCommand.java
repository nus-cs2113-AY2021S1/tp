package seedu.revised.command.topic;

import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.command.flashcard.FlashcardCommand;
import seedu.revised.exception.FailedParseException;
import seedu.revised.exception.flashcard.NoFlashcardException;
import seedu.revised.exception.flashcard.RepeatedFlashcardException;
import seedu.revised.exception.topic.NoTopicException;
import seedu.revised.parser.FlashcardParser;
import seedu.revised.ui.Ui;

public class ReturnTopicCommand extends TopicCommand {
    private String fullcommand;

    public ReturnTopicCommand(String fullcommand) {
        this.fullcommand = fullcommand;
    }

    public Topic execute(Subject subject) throws NoTopicException {
        String[] message = this.fullcommand.split(" ");
        if (message[1].isEmpty()) {
            throw new NoTopicException(Ui.printNoTopicError());
        }
        for (Topic topic : subject.getTopics().getList()) {
            if (topic.getTitle().equals(message[1])) {
                return topic;
            }
        }
        throw new NoTopicException(Ui.printNoTopicError());
    }

    public void goToTopic(Topic topic) {
        Ui.printGoToTopic(topic);
        boolean isTopicExit = false;
        while (!isTopicExit) {
            try {
                String fullCommand = Ui.readCommand();
                FlashcardCommand c = FlashcardParser.parse(fullCommand);
                isTopicExit = c.isExit();
                c.execute(topic);
            } catch (NoFlashcardException e) {
                System.out.println(e.getMessage());
            } catch (RepeatedFlashcardException e) {
                System.out.println(e.getMessage());
            } catch (FailedParseException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(Ui.printIndexError());
            } catch (IndexOutOfBoundsException e) {
                System.out.println(Ui.printOutOfBoundsError());
            }
        }
        Ui.printBackToTopicsAndTasks();
    }

    public boolean isExit() {
        return false;
    }
}
