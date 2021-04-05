package seedu.hdbuy.command;

import seedu.hdbuy.common.HdBuyLogger;
import seedu.hdbuy.common.Unit;
import seedu.hdbuy.common.exception.InvalidIndexException;
import seedu.hdbuy.data.ShortList;
import seedu.hdbuy.ui.TextUi;

public class RemoveCommand extends Command {

    protected int index;

    public RemoveCommand(int index) {
        this.index = index;
    }

    @Override public void execute() {
        try {
            Unit removedUnit = ShortList.removeFromShortList(index);
            HdBuyLogger.info("Removed: " + removedUnit.toString());
            TextUi.showRemovedShortlistUnit(removedUnit.toString());
        } catch (InvalidIndexException e) {
            HdBuyLogger.error("Unable to remove at index");
            TextUi.showInvalidIndex(e);
        }
    }
}
