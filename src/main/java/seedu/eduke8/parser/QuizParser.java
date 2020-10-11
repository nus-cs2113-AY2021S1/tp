package seedu.eduke8.parser;

import seedu.eduke8.Command;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.option.Option;
import seedu.eduke8.question.Question;
import seedu.eduke8.ui.Ui;

import java.util.ArrayList;

public class QuizParser implements Parser {
    @Override
    public Command parseCommand(DisplayableList optionList, String userInput) {
        // Need to add hints case also
        ArrayList<Displayable> options = optionList.getInnerList();
        int chosenIndex = Integer.parseInt(userInput);
        Option chosenOption = (Option) options.get(chosenIndex);

        // Need to move this execution to Command class and feed this objects into the execute method
        Ui ui = new Ui();
        Question question = null;
        if (chosenOption.isCorrectAnswer()) {
            ui.printAnswerIsCorrect();
            question.markAsAnsweredCorrectly();
        } else {
            ui.printAnswerIsWrong();
        }

        return null; // Return command object instead
    }
}
