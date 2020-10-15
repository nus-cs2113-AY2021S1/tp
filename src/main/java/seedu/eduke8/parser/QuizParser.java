package seedu.eduke8.parser;

import seedu.eduke8.command.AnswerCommand;
import seedu.eduke8.command.Command;
import seedu.eduke8.command.HintCommand;
import seedu.eduke8.command.IncorrectCommand;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.option.Option;
import seedu.eduke8.question.Question;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuizParser implements Parser {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private Question question;

    public void setQuestion(Question question) {
        assert question != null;

        this.question = question;
    }

    @Override
    public Command parseCommand(DisplayableList optionList, String userInput) {
        if ("hint".equals(userInput)) {
            return new HintCommand(question.getHint());
        }
        
        try {
            ArrayList<Displayable> options = optionList.getInnerList();
            int chosenIndex = Integer.parseInt(userInput) - 1;
            Option chosenOption = (Option) options.get(chosenIndex);

            return new AnswerCommand(chosenOption, question);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "A non-number was given when answering question.");
            return new IncorrectCommand("Please choose the answer by index");
        } catch (IndexOutOfBoundsException e) {
            LOGGER.log(Level.WARNING, "An index out of bounds was given when answering question.");
            return new IncorrectCommand("Please choose only 1, 2, 3 or 4");
        }
    }
}
