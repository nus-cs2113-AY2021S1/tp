package seedu.eduke8.command;

import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.hint.Hint;
import seedu.eduke8.ui.Ui;

public class HintCommand extends Command {
    private Hint hint;

    public HintCommand(Hint hint) {
        this.hint = hint;
    }

    @Override
    public void execute(DisplayableList displayableList, Ui ui) {
        ui.printHint(hint);
    }
}
