package seedu.hdbuy.command;

import java.util.ArrayList;

import seedu.hdbuy.common.Unit;
import seedu.hdbuy.common.exception.NoFlatsException;
import seedu.hdbuy.data.ShortList;
import seedu.hdbuy.ui.TextUi;

public class ShortlistCommand extends Command {

    @Override public void execute() {
        try {
            ArrayList<Unit> units = ShortList.getShortListedUnits();
            if (units.isEmpty()) {
                throw new NoFlatsException();
            } else {
                TextUi.showShortListUnits(units);
            }
        } catch (NoFlatsException e) {
            TextUi.showExceptionMessage(e);
        }
    }
}
