package seedu.duke.wordlist.wordfilter;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.wordexceptions.FilterCommandException;
import seedu.duke.exceptions.wordexceptions.FilterTypeTagMissingException;
import seedu.duke.wordlist.wordfilter.FilterType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FilterTypeTest {

    @Test
    public void getTypeOfFilter_wordTypeFilter_wordType() throws FilterCommandException, FilterTypeTagMissingException {
        FilterType expected = FilterType.WORD_TYPE;
        FilterType actual = FilterType.getTypeOfFilter("filter words by\\type -noun -adjective");
        assertEquals(expected, actual);
    }

    @Test
    public void getTypeOfFilter_startingStringFilter_startingString()
            throws FilterCommandException, FilterTypeTagMissingException {
        FilterType expected = FilterType.STARTING_STRING;
        FilterType actual = FilterType.getTypeOfFilter("filter words by\\start -nho -e");
        assertEquals(expected, actual);
    }

    @Test
    public void getTypeOfFilter_includedStringFilter_includingString()
            throws FilterCommandException, FilterTypeTagMissingException {
        FilterType expected = FilterType.INCLUDING_STRING;
        FilterType actual = FilterType.getTypeOfFilter("filter words by\\include -th -ao");
        assertEquals(expected, actual);
    }

    @Test
    public void getTypeOfFilter_invalidTypeFilter_filterCommandExceptionThrown() {
        assertThrows(FilterCommandException.class, () -> FilterType.getTypeOfFilter("filter words by\\abcd -xyz -jkl"));
    }

}