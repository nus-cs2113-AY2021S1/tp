package seedu.revised.command.flashcardcommand;

import seedu.revised.card.Flashcard;
import seedu.revised.card.Topic;
import seedu.revised.command.subjectcommand.DeleteSubjectCommand;
import seedu.revised.ui.Ui;

import java.util.logging.Logger;

public class DeleteFlashcardCommand extends FlashcardCommand {
    private static final Logger logger = Logger.getLogger(DeleteFlashcardCommand.class.getName());
    private String fullCommand;

    public DeleteFlashcardCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Deletes an instance of Flashcard in the list <code>Topic.flashcards</code>.
     *
     * @param topic the <code>Topic</code> instance of the Topic class for the user to delete from
     */
    public void execute(Topic topic) throws NumberFormatException {
        logger.info("Begin checking string command to get the flashcard to be deleted.");
        String[] message = this.fullCommand.split(" ");
        int number = Integer.parseInt(message[1]);
        Flashcard flashcard = topic.getFlashcards().get(number - 1);
        topic.getFlashcards().remove(number - 1);
        Ui.printFlashcardDelete(flashcard, topic.getFlashcards().size());
        logger.info("Finished deleting the flashcard to be deleted.");
        logger.fine(String.format("The flashcard is %s; %s", flashcard.getQuestion(), flashcard.getAnswer()));
    }

    /**
     * Checks whether the the user exits the program.
     *
     * @return <code>true</code> if user exits the program
     */
    public boolean isExit() {
        return false;
    }
}

