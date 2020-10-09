package seedu.eduke8;

import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.ui.Ui;


public interface Command {
    void execute(DisplayableList displayableList, Ui ui);

    boolean isExit();

}
