package seedu.hdbuy.data;

import seedu.hdbuy.common.HdBuyLogger;
import seedu.hdbuy.common.Unit;
import seedu.hdbuy.common.exception.InvalidIndexException;
import seedu.hdbuy.common.exception.NoFlatsException;
import seedu.hdbuy.common.exception.NoSearchException;

import java.util.ArrayList;

public class SearchedUnits {

    private static ArrayList<Unit> units;

    /**
     * Return an array list of Unit objects.
     * If the user hasn't performed the search command before this, an error will be thrown.
     *
     * @return An array list of Unit objects.
     * @throws NoSearchException An exception error triggered by accessing this method without performing search
     *                           command before.
     */
    public static ArrayList<Unit> getSearchedUnits() throws NoSearchException {
        if (units == null) {
            HdBuyLogger.error("Did not perform search before");
            throw new NoSearchException();
        }
        return units;
    }

    /**
     * This method add the Unit object to the array list of Unit objects.
     *
     * @param unit Unit object requested by the user's search.
     */
    public static void addToResult(Unit unit) {
        if (units == null) {
            units = new ArrayList<>();
        }
        units.add(unit);
    }

    /**
     * This method will return the Unit object requested by the user.
     * An exception will be thrown if the index of the unit requested by the user is out of bounds.
     *
     * @param index The index of the Unit object in the array list of Unit objects.
     * @return The unit object requested by the user through the index.
     * @throws InvalidIndexException The exception triggered by an index out of bound.
     * @throws NoSearchException     The exception triggered by not performing search beforehand.
     */
    public static Unit getUnit(int index) throws InvalidIndexException, NoSearchException {
        try {
            return getSearchedUnits().get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            HdBuyLogger.error("Invalid index");
            throw new InvalidIndexException(Integer.toString(index));
        }
    }

    /**
     * This method will clear the array list of Unit objects.
     */
    public static void clearSearchedUnits() {
        units = new ArrayList<>();
    }

    /**
     * This method will sort the results by price.
     *
     * @param isAscending Decides if the results will be sorted by ascending price or descending price.
     * @throws NoSearchException An exception triggered by not performing search beforehand.
     * @throws NoFlatsException  An exception triggered by not being able to find units that fulfilled the user's
     *                           filter.
     */
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
