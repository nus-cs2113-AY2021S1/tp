package seedu.eduke8.command;

import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.ui.Ui;

public class TextbookCommand extends Command {
    @Override
    public void execute(DisplayableList displayableList, Ui ui) {
        ui.printTextbook();
    }
}
