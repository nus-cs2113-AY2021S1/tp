package seedu.eduke8.quiz;

import seedu.eduke8.exception.Eduke8Exception;
import seedu.eduke8.ui.Ui;

public interface Quiz {
    void startQuiz(Ui ui) throws Eduke8Exception;

}
