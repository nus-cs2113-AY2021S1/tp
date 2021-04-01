package seedu.hdbuy.command;

import seedu.hdbuy.common.HdBuyLogger;
import seedu.hdbuy.common.Unit;
import seedu.hdbuy.data.ShortList;
import seedu.hdbuy.data.UserInput;
import seedu.hdbuy.ui.TextUi;

public class RemoveCommand extends Command {

    protected int index;

    public RemoveCommand(int index) {
        this.index = index;
    }

    @Override public void execute(UserInput userInput) {
        Unit removedUnit = ShortList.removeFromShortList(index);
        if (removedUnit != null) {
            HdBuyLogger.info("Removed: " + removedUnit.toString());
            TextUi.showRemovedShortlistUnit(removedUnit.toString());
        } else {
            HdBuyLogger.error("Unable to remove at index");
            TextUi.showInvalidIndex();
        }
    }
}
