package seedu.duke.filters;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.FilterCommandException;

import static org.junit.jupiter.api.Assertions.*;

class FilterTypeTest {

    @Test
    public void getTypeOfFilter_wordTypeFilter_wordType() throws FilterCommandException {
        FilterType expected = FilterType.WORD_TYPE;
        FilterType actual = FilterType.getTypeOfFilter("filter by\\type -noun -adjective");
        assertEquals(expected, actual);
    }

    @Test
    public void getTypeOfFilter_startingStringFilter_startingString() throws FilterCommandException {
        FilterType expected = FilterType.STARTING_STRING;
        FilterType actual = FilterType.getTypeOfFilter("filter by\\start -nho -e");
        assertEquals(expected, actual);
    }

    @Test
    public void getTypeOfFilter_includedStringFilter_includingString() throws FilterCommandException {
        FilterType expected = FilterType.INCLUDING_STRING;
        FilterType actual = FilterType.getTypeOfFilter("filter by\\include -th -ao");
        assertEquals(expected, actual);
    }

    @Test
    public void getTypeOfFilter_invalidTypeFilter_filterCommandExceptionThrown() {
        assertThrows(FilterCommandException.class, () -> FilterType.getTypeOfFilter("filter by\\abcd -xyz -jkl"));
    }

}