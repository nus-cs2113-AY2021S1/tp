package seedu.revised.command.flashcardcommand;

import seedu.revised.card.Flashcard;
import seedu.revised.card.Topic;
import seedu.revised.exception.flashcardexception.InvalidFlashcardException;
import seedu.revised.exception.flashcardexception.RepeatedFlashcardException;
import seedu.revised.ui.Ui;


public class AddFlashcardCommand extends FlashcardCommand {
    private final String fullCommand;

    public AddFlashcardCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Adds an instance of Flashcard in the list <code>Topic.flashcards</code>.
     *
     * @param topic the <code>Topic</code> instance of the Topic class for the user to append to
     * @throws RepeatedFlashcardException If the program detects the flashcard the user inputs already exists
     *                                    in the program
     * @throws InvalidFlashcardException If the program does not detect any flashcard question or answer in user input
     */
    public void execute(Topic topic) throws RepeatedFlashcardException, InvalidFlashcardException {
        String[] tokens = fullCommand.split(" ", 2);
        String question = "";
        String answer = "";

        try {
            String content = tokens[1].strip();
            String[] contentTokens = content.split(";", 2);
            question = contentTokens[0].strip();
            answer = contentTokens[1].strip();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidFlashcardException(Ui.INVALID_FLASHCARD_EXCEPTION);
        }

        if (question.isEmpty() || answer.isEmpty()) {
            throw new InvalidFlashcardException(Ui.INVALID_FLASHCARD_EXCEPTION);
        }

        for (Flashcard flashcard : topic.getFlashcards()) {
            if (flashcard.getQuestion().equals(question) && flashcard.getAnswer().equals(answer)) {
                throw new RepeatedFlashcardException(Ui.REPEATED_FLASHCARD_EXCEPTION);
            }
        }
        Flashcard t = new Flashcard(question, answer);
        topic.addFlashcard(t);
        Ui.printFlashcard(t, topic.getFlashcards());
    }

    /**
     * Checks whether the the user exits the program.
     *
     * @return <code>true</code> If user exits the program
     */
    public boolean isExit() {
        return false;
    }
}
