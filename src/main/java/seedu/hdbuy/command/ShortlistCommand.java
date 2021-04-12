package seedu.hdbuy.command;

import seedu.hdbuy.common.Unit;
import seedu.hdbuy.common.exception.NoFlatsException;
import seedu.hdbuy.data.ShortList;
import seedu.hdbuy.ui.TextUi;

import java.util.ArrayList;

public class ShortlistCommand extends Command {

    @Override public void execute() {
        try {
            ArrayList<Unit> units = ShortList.getShortListedUnits();
            if (units.isEmpty()) {
                throw new NoFlatsException();
            } else {
                TextUi.showUnits(units);
            }
        } catch (NoFlatsException e) {
            TextUi.showExceptionMessage(e);
        }
    }
}
