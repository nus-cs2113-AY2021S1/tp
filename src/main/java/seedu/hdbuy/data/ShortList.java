package seedu.hdbuy.data;

import java.util.ArrayList;

import seedu.hdbuy.common.HdBuyLogger;
import seedu.hdbuy.common.Unit;
import seedu.hdbuy.storage.StorageManager;

public class ShortList {

    private static ArrayList<Unit> units;

    public static ArrayList<Unit> getShortListedUnits() {
        if (units == null) {
            units = new ArrayList<>();
            StorageManager.read();
        }
        return units;
    }

    public static void addToShortList(Unit unit) {
        getShortListedUnits().add(unit);
        StorageManager.write();
    }

    public static Unit removeFromShortList(int index) {
        try {
            Unit removedUnit = getShortListedUnits().remove(index - 1);
            StorageManager.write();
            return removedUnit;
        } catch (IndexOutOfBoundsException e) {
            HdBuyLogger.error("Unable to remove unit from shortlist, invalid index");
            return null;
        }
    }
}
