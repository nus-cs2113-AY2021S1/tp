package seedu.revised.command.flashcard;

import seedu.revised.card.Flashcard;
import seedu.revised.card.Topic;
import seedu.revised.exception.FailedParseException;
import seedu.revised.exception.flashcard.InvalidFlashcardException;
import seedu.revised.exception.flashcard.RepeatedFlashcardException;
import seedu.revised.exception.flashcard.NoFlashcardException;
import seedu.revised.ui.Ui;


public class AddFlashcardCommand extends FlashcardCommand {
    private final String fullCommand;


    public AddFlashcardCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

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

    public boolean isExit() {
        return false;
    }
}
