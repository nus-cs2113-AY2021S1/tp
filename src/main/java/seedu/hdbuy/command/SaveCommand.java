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

    public SaveCommand(int index) {
        this.index = index;
    }

    @Override public void execute() {
        try {
            Unit targetUnit = SearchedUnits.getUnit(index);
            ShortList.addToShortList(targetUnit);
            HdBuyLogger.info("Saved unit to shortlist: " + targetUnit.toString());
            TextUi.showSavedShortlistUnit(targetUnit.toString());
        } catch (DuplicateUnitException e) {
            TextUi.showDuplicateUnit(e);
        } catch (NoSearchException e) {
            TextUi.showDoSearchPrompt(e);
        } catch (InvalidIndexException e) {
            TextUi.showInvalidIndex(e);
        }
    }
}
