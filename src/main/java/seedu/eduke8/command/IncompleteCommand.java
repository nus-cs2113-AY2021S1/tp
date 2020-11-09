package seedu.eduke8.command;

import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.exception.Eduke8Exception;
import seedu.eduke8.option.OptionList;
import seedu.eduke8.question.Question;
import seedu.eduke8.ui.Ui;

/**
 * A specific command that prints out messages if a user is unable to finish a question in a quiz by the time limit.
 */
public class IncompleteCommand extends Command {
    private Question question;
    private int userTimer;

    public IncompleteCommand(Question question, int userTimer) {
        super();

        this.question = question;
        this.userTimer = userTimer;
    }

    @Override
    public void execute(DisplayableList optionList, Ui ui) {
        String explanation = question.getExplanation().getDescription();
        try {
            int correctOptionNumber = ((OptionList) optionList).findCorrectOptionIndex() + 1;
            ui.printIncompleteAnswer(correctOptionNumber, explanation, userTimer);
        } catch (Eduke8Exception e) {
            ui.printError(e.getMessage());
        }
    }
}
