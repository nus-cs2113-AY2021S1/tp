package seedu.eduke8.command;

import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.exception.Eduke8Exception;
import seedu.eduke8.option.Option;
import seedu.eduke8.option.OptionList;
import seedu.eduke8.question.Question;
import seedu.eduke8.ui.Ui;

public class AnswerCommand extends Command {
    private Option option;
    private Question question;

    public AnswerCommand(Option option, Question question) {
        super();
        assert option != null && question != null;

        this.option = option;
        this.question = question;
    }

    @Override
    public void execute(DisplayableList optionList, Ui ui) {
        String explanation = question.getExplanation().getDescription();

        if (option.isCorrectAnswer()) {
            ui.printAnswerIsCorrect(explanation);
            question.markAsAnsweredCorrectly();
            assert question.wasAnsweredCorrectly();
        } else {
            try {
                int correctOptionNumber = ((OptionList) optionList).findCorrectOptionIndex() + 1;
                ui.printAnswerIsWrong(correctOptionNumber, explanation);
            } catch (Eduke8Exception e) {
                ui.printError(e.getMessage());
            }
        }
    }
}
