package seedu.hdbuy.command;

import seedu.hdbuy.common.Unit;
import seedu.hdbuy.common.exception.NoFlatsException;
import seedu.hdbuy.common.exception.NoSearchException;
import seedu.hdbuy.data.SearchedUnits;
import seedu.hdbuy.parser.CommandType;
import seedu.hdbuy.ui.TextUi;

import java.util.ArrayList;

public class SortCommand extends Command {
    protected String criteria;

    public SortCommand(String criteria) {
        this.criteria = criteria;
    }

    @Override public void execute() {
        try {
            switch (criteria) {
            case CommandType.SORT_ASC:
                SearchedUnits.sortMapByPrice(true);
                break;
            case CommandType.SORT_DESC:
                SearchedUnits.sortMapByPrice(false);
                break;
            default:
                return;
            }
            ArrayList<Unit> sortedUnits = SearchedUnits.getSearchedUnits();
            TextUi.showUnits(sortedUnits);
        } catch (NoFlatsException | NoSearchException e) {
            TextUi.showExceptionMessage(e);
        }
    }
}