package seedu.duke.command.flashcardcommand;

import seedu.duke.card.Flashcard;
import seedu.duke.card.Subject;
import seedu.duke.card.Topic;
import seedu.duke.exception.NoSubjectException;
import seedu.duke.exception.RepeatedSubjectException;

public class FlashcardCommand {
	public Flashcard execute(Subject subject) throws NoSubjectException, RepeatedSubjectException {
		return null;
	}

	public boolean isExit() {
		return false;
	}
}
