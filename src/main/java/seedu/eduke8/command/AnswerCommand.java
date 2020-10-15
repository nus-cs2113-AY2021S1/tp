package seedu.eduke8.command;

import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.option.Option;
import seedu.eduke8.question.Question;
import seedu.eduke8.ui.Ui;

public class AnswerCommand extends Command {
    private Option option;
    private Question question;

    public AnswerCommand(Option option, Question question) {
        assert option != null && question != null;

        this.option = option;
        this.question = question;
    }

    @Override
    public void execute(DisplayableList optionList, Ui ui) {
        int correctOption; 

        if (option.isCorrectAnswer()) {
            ui.printAnswerIsCorrect();
            question.markAsAnsweredCorrectly();
            assert question.wasAnsweredCorrectly();
        } else {
            correctOption = findCorrectOption(optionList);
            ui.printAnswerIsWrong(correctOption);
            assert !question.wasAnsweredCorrectly();
        }
    }

    private int findCorrectOption(DisplayableList optionList) {
        for(int i=0; i<optionList.getCount(); i++) {
            Option option = (Option) optionList.getInnerList().get(i);
            if (option.isCorrectAnswer()) {
                return (i+1);
            }
        }
        return 0; //needs to be fixed
    }
}
