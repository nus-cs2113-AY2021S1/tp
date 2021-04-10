package seedu.hdbuy.data;

import java.util.ArrayList;

import seedu.hdbuy.common.HdBuyLogger;
import seedu.hdbuy.common.Unit;
import seedu.hdbuy.common.exception.InvalidIndexException;
import seedu.hdbuy.common.exception.NoFlatsException;
import seedu.hdbuy.common.exception.NoSearchException;

public class SearchedUnits {

    private static ArrayList<Unit> units;

    public static ArrayList<Unit> getSearchedUnits() throws NoSearchException {
        if (units == null) {
            HdBuyLogger.error("Did not perform search before");
            throw new NoSearchException();
        }
        return units;
    }

    public static void addToResult(Unit unit) {
        if (units == null) {
            units = new ArrayList<>();
        }
        units.add(unit);
    }

    public static Unit getUnit(int index) throws InvalidIndexException, NoSearchException {
        try {
            return getSearchedUnits().get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            HdBuyLogger.error("Invalid index");
            throw new InvalidIndexException(Integer.toString(index));
        }
    }

    public static void clearSearchedUnits() {
        units = new ArrayList<>();
    }

    public static void sortMapByPrice(boolean isAscending) throws NoSearchException, NoFlatsException {
        if (getSearchedUnits().isEmpty()) {
            throw new NoFlatsException();
        }
        getSearchedUnits().sort((unit1, unit2) -> {
            if (isAscending) {
                return unit1.getPrice() - unit2.getPrice();
            } else {
                return unit2.getPrice() - unit1.getPrice();
            }
        });
    }
}
