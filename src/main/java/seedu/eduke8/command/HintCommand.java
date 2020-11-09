package seedu.eduke8.command;

import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.hint.Hint;
import seedu.eduke8.ui.Ui;

/**
 * A specific command that displays a question's hint during a quiz.
 */
public class HintCommand extends Command {
    private Hint hint;

    public HintCommand(Hint hint) {
        super();
        assert hint != null;

        this.hint = hint;
    }

    @Override
    public void execute(DisplayableList displayableList, Ui ui) {
        ui.printHint(hint);

        assert hint.wasShown();
    }
}
