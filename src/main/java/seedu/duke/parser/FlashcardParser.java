package seedu.duke.parser;

import seedu.duke.command.flashcardcommand.*;

public class FlashcardParser {

	/**
	 * Parses the inputs provided by the user.
	 *
	 * @param fullCommand input by the user
	 * @return returns a command instance to execute a command
	 */
	public static FlashcardCommand parse(String fullCommand) {
		if (fullCommand.equals("bye")) {
			return new ExitFlashcardCommand();
		} else if (fullCommand.equals("list")) {
			return new ListFlashcardCommand();
		} else if (fullCommand.startsWith("add")) {
			return new AddFlashcardCommand(fullCommand);
		} else if (fullCommand.startsWith("delete ")) {
			return new DeleteFlashcardCommand(fullCommand);
		} else if (fullCommand.startsWith("flashcard")) {
			return new ReturnFlashcardCommand(fullCommand);
		} else {
			return new SorryFlashcardCommand();
		}
	}
}
