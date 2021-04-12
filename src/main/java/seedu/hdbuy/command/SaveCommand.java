package seedu.hdbuy.command;

import seedu.hdbuy.common.HdBuyLogger;
import seedu.hdbuy.common.Unit;
import seedu.hdbuy.common.exception.DuplicateUnitException;
import seedu.hdbuy.common.exception.InvalidIndexException;
import seedu.hdbuy.common.exception.NoSearchException;
import seedu.hdbuy.data.SearchedUnits;
import seedu.hdbuy.data.ShortList;
import seedu.hdbuy.ui.TextUi;

public class SaveCommand extends Command {

    protected int index;

    /**
     * Gets the index of the Unit object the user requested to save.
     *
     * @param index The index of the Unit object in the array list of fetched units.
     */
    public SaveCommand(int index) {
        this.index = index;
    }

    @Override public void execute() {
        try {
            Unit targetUnit = SearchedUnits.getUnit(index);
            ShortList.addToShortList(targetUnit);
            HdBuyLogger.info("Saved unit to shortlist: " + targetUnit.toString());
            TextUi.showSavedShortlistUnit(targetUnit.toString());
        } catch (DuplicateUnitException | NoSearchException | InvalidIndexException e) {
            TextUi.showExceptionMessage(e);
        }
    }
}
