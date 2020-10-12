package seedu.duke.filters;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.FilterCommandException;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FilterCommandSlicerTest {

    @Test
    public void getTargetedWordType_adjectiveFilterRecognition_arrayOfAdjectiveType()
            throws FilterCommandException {
        String[] expected = {"adjective"};
        String[] actual = FilterCommandSlicer.getTargetedWordType("filter by\\type -adjective");
        assertEquals(1, actual.length);
        assertTrue(Arrays.equals(expected, actual));
    }

    @Test
    public void getTargetedWordType_nounAndVerbFilterRecognition_arrayOfNounAndVerbElements()
            throws FilterCommandException {
        String[] expected = {"noun", "verb"};
        String[] actual = FilterCommandSlicer.getTargetedWordType("filter by\\type -verb -noun");
        assertEquals(2, actual.length);
        assertTrue(Arrays.equals(expected, actual));
    }

    @Test
    public void getTargetedWordType_allTypeFilterRecognition_arrayOfAllWordType()
            throws FilterCommandException {
        String[] expected = {"noun", "verb", "adjective"};
        String[] actual = FilterCommandSlicer.getTargetedWordType("filter by\\type -adjective -verb -noun");
        assertEquals(3, actual.length);
        assertTrue(Arrays.equals(expected, actual));
    }

    @Test
    public void getTargetedWordType_noWordTypeFound_filterCommandExceptionThrown() {
        assertThrows(FilterCommandException.class,
                () -> FilterCommandSlicer.getTargetedWordType("filter by\\type -adverb -preposition"));
    }

    @Test
    public void getTargetedStringTags_oneStringTagNoSpace_success() throws FilterCommandException {
        String[] expected = {"cs"};
        String[] actual = FilterCommandSlicer.getTargetedStringTags("filter by\\contain -cs");
        assertEquals(1, actual.length);
        assertTrue(Arrays.equals(expected, actual));
    }

    @Test
    public void getTargetedStringTags_oneStringTagHaveSpace_success() throws FilterCommandException {
        String[] expected = {"cs 2113 t"};
        String[] actual = FilterCommandSlicer.getTargetedStringTags("filter by\\start -cs 2113 t");
        assertEquals(1, actual.length);
        assertTrue(Arrays.equals(expected, actual));
    }

    @Test
    public void getTargetedStringTags_twoStringTagsNoSpace_success() throws FilterCommandException {
        String[] expected = {"cs 2113 t", "cs 2101"};
        String[] actual = FilterCommandSlicer.getTargetedStringTags("filter by\\start -cs 2113 t -cs 2101");
        assertEquals(2, actual.length);
        assertTrue(Arrays.equals(expected, actual));
    }

    @Test
    public void getTargetedStringTags_twoStringTagsHaveSpace_success() throws FilterCommandException {
        String[] expected = {"cs2113t", "cs2101"};
        String[] actual = FilterCommandSlicer.getTargetedStringTags("filter by\\contain -cs2113t -cs2101");
        assertEquals(2, actual.length);
        assertTrue(Arrays.equals(expected, actual));
    }

    @Test
    public void getTargetedStringTags_noStringTagProvided_filterCommandExceptionThrown() {
        assertThrows(FilterCommandException.class,
                () -> FilterCommandSlicer.getTargetedStringTags("filter by\\start - "));
    }
}