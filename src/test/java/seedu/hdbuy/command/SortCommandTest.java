package seedu.hdbuy.command;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import seedu.hdbuy.common.Unit;
import seedu.hdbuy.common.exception.InvalidIndexException;
import seedu.hdbuy.common.exception.NoFlatsException;
import seedu.hdbuy.common.exception.NoSearchException;
import seedu.hdbuy.data.SearchedUnits;
import seedu.hdbuy.parser.CommandType;

import static org.junit.jupiter.api.Assertions.*;

class SortCommandTest {

    @Test void sortExecuteTest() {
        Unit textUnit1 = new Unit("JURONG WEST", "4 ROOM", 429000, 990,
                " 82 years 06 months", "664A JURONG WEST ST 64", 1026083864);
        Unit textUnit2 = new Unit("JURONG WEST", "4 ROOM", 429001, 990,
                " 82 years 06 months", "664A JURONG WEST ST 64", 1026083864);
        Unit textUnit3 = new Unit("JURONG WEST", "4 ROOM", 429002, 990,
                " 82 years 06 months", "664A JURONG WEST ST 64", 1026083864);

        try {
            SearchedUnits.clearSearchedUnits();
            SearchedUnits.addToResult(textUnit2);
            SearchedUnits.addToResult(textUnit1);
            SearchedUnits.addToResult(textUnit3);

            assertFalse(SearchedUnits.getSearchedUnits().isEmpty());
        } catch (NoSearchException e) {
            fail();
        }

        try {
            SortCommand sortCommandAsc = new SortCommand(CommandType.SORT_ASC);
            sortCommandAsc.execute();
            assertEquals(textUnit1.toString(), Objects.requireNonNull(SearchedUnits.getUnit(1)).toString());
            assertEquals(textUnit3.toString(), Objects.requireNonNull(SearchedUnits.getUnit(3)).toString());

            SortCommand sortCommandDesc = new SortCommand(CommandType.SORT_DESC);
            sortCommandDesc.execute();
            assertEquals(textUnit3.toString(), Objects.requireNonNull(SearchedUnits.getUnit(1)).toString());
            assertEquals(textUnit1.toString(), Objects.requireNonNull(SearchedUnits.getUnit(3)).toString());

            SearchedUnits.clearSearchedUnits();
            assertTrue(SearchedUnits.getSearchedUnits().isEmpty());
            sortCommandAsc.execute();
        } catch (NoSearchException | InvalidIndexException e) {
            fail();
        }
    }
}