package seedu.duke.controller.command;

import seedu.duke.model.Model;

public class ListQuizCommand extends Command {
    @Override
    public void execute(Model model) {
        model.getQuizManager().listQuiz();
    }
}
