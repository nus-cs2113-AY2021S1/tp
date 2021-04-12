package seedu.hdbuy.data;

import seedu.hdbuy.common.HdBuyLogger;
import seedu.hdbuy.common.Unit;
import seedu.hdbuy.common.exception.DuplicateUnitException;
import seedu.hdbuy.common.exception.InvalidIndexException;
import seedu.hdbuy.storage.StorageManager;

import java.util.ArrayList;

public class ShortList {

    private static ArrayList<Unit> units;

    /**
     * Read from the .TXT file to access the units shortlisted by the user.
     *
     * @return An array list of Unit objects.
     */
    public static ArrayList<Unit> getShortListedUnits() {
        if (units == null) {
            units = new ArrayList<>();
            StorageManager.read();
        }
        return units;
    }

    /**
     * This method will add the unit requested by the user to the shortlist.
     *
     * @param unit The Unit object requested by the user to be added into the shortlist.
     * @throws DuplicateUnitException An exception triggered by adding an already added unit into the shortlist.
     */
    public static void addToShortList(Unit unit) throws DuplicateUnitException {
        for (Unit shortlisted : getShortListedUnits()) {
            if (shortlisted.getId() == unit.getId()) {
                throw new DuplicateUnitException();
            }
        }
        getShortListedUnits().add(unit);
        StorageManager.write();
    }

    /**
     * This method will remove the unit requested by the user from the shortlist.
     *
     * @param index The index of the unit to be removed from the shortlist.
     * @return The removed Unit object.
     * @throws InvalidIndexException An exception triggered by specifying an out-of-bound index.
     */
    public static Unit removeFromShortList(int index) throws InvalidIndexException {
        try {
            Unit removedUnit = getShortListedUnits().remove(index - 1);
            StorageManager.write();
            return removedUnit;
        } catch (IndexOutOfBoundsException e) {
            HdBuyLogger.error("Unable to remove unit from shortlist, invalid index");
            throw new InvalidIndexException(Integer.toString(index));
        }
    }
}
