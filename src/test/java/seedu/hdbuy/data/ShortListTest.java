package seedu.hdbuy.data;

import org.junit.jupiter.api.Test;
import seedu.hdbuy.common.Unit;
import seedu.hdbuy.common.exception.DuplicateUnitException;
import seedu.hdbuy.common.exception.InvalidIndexException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

class ShortListTest {

    @Test void modifyShortlist() {
        ArrayList<Unit> units = ShortList.getShortListedUnits();
        assertNotNull(units);

        Unit unit = new Unit("JURONG WEST", "4 ROOM", 429000, 990, " 82 years 06 months", "664A JURONG WEST ST 64",
                1026083864);
        try {
            ShortList.addToShortList(unit);
        } catch (DuplicateUnitException e) {
            fail(); //not supposed to have duplicates
        }
        int max = ShortList.getShortListedUnits().size();
        assertEquals(unit.toString(), ShortList.getShortListedUnits().get(max - 1).toString());

        try {
            Unit removedUnit = ShortList.removeFromShortList(max);
            assertNotNull(removedUnit);
            assertEquals(unit.toString(), removedUnit.toString());
            assertEquals(max - 1, ShortList.getShortListedUnits().size());
        } catch (InvalidIndexException e) {
            fail();
        }
    }
}