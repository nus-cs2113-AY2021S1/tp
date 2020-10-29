package seedu.eduke8.command;

import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.exception.Eduke8Exception;
import seedu.eduke8.option.OptionList;
import seedu.eduke8.question.Question;
import seedu.eduke8.ui.Ui;

public class IncompleteCommand extends Command {
    private Question question;

    public IncompleteCommand(Question question) {
        super();

        this.question = question;
    }

    @Override
    public void execute(DisplayableList optionList, Ui ui) {
        String explanation = question.getExplanation().getDescription();
        try {
            int correctOptionNumber = ((OptionList) optionList).findCorrectOptionIndex() + 1;
            ui.printIncompleteAnswer(correctOptionNumber, explanation);
        } catch (Eduke8Exception e) {
            ui.printError(e.getMessage());
        }

    }
}
