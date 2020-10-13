package seedu.duke.command.flashcardcommand;

import seedu.duke.card.Flashcard;
import seedu.duke.card.Topic;
import seedu.duke.exception.NoSubjectException;
import seedu.duke.exception.RepeatedSubjectException;

public class AddFlashcardCommand extends FlashcardCommand {
    private final String fullCommand;

    public AddFlashcardCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public Flashcard execute(Topic topic) throws NoSubjectException, RepeatedSubjectException {
        int startOfMessage = 4;
        int endOfQuestion = (fullCommand.indexOf(";"));
        int startOfAnswer = endOfQuestion + 2;
        int endOfMessage = fullCommand.length();
        if (endOfMessage <= startOfMessage) {
            throw new NoSubjectException();
        }
        String question = fullCommand.substring(startOfMessage, endOfQuestion);
        String answer = fullCommand.substring(startOfAnswer, endOfMessage);
        if (question.isEmpty() || answer.isEmpty()) {
            throw new NoSubjectException();
        }
        for (Flashcard flashcard : topic.getFlashcards()) {
            if (flashcard.getQuestion().equals(question) && flashcard.getAnswer().equals(answer)) {
                throw new RepeatedSubjectException();
            }
        }
        Flashcard t = new Flashcard(question, answer);
        topic.addFlashcard(t);
        t.printFlashcard(topic.getFlashcards());
        return t;
    }
    public boolean isExit() {
        return false;
    }
}
