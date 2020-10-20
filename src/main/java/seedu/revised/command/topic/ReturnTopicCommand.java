package seedu.revised.command.topic;

import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.command.flashcard.FlashcardCommand;
import seedu.revised.exception.FailedParseException;
import seedu.revised.exception.flashcard.RepeatedFlashcardException;
import seedu.revised.exception.flashcard.*;
import seedu.revised.exception.subject.NoSubjectException;
import seedu.revised.parser.FlashcardParser;
import seedu.revised.task.TaskList;
import seedu.revised.ui.Ui;

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
            throw new NoSubjectException(Ui.printNoSubjectError());
        }
        for (Topic topic : subject.getTopics().getList()) {
            if (topic.getTitle().equals(message[1])) {
                return topic;
            }
        }
        throw new NoSubjectException(Ui.printNoSubjectError());
    }

    public void goToTopic(Topic topic, Subject subject) {
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
