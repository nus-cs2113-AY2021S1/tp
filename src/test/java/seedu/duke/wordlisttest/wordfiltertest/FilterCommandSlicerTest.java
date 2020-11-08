package seedu.duke.wordlisttest.wordfiltertest;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.*;
import seedu.duke.wordlist.wordfilter.FilterCommandSlicer;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FilterCommandSlicerTest {

    @Test
    public void getTargetedWordType_adjectiveFilterRecognition_arrayOfAdjectiveType()
            throws FilterWordsInvalidWordType, FilterMissingTargetWordTypeException {
        String[] expected = {"adjective"};
        String[] actual = FilterCommandSlicer
                .getTargetedWordTypes("filter words -continue by\\type -adjective");
        assertEquals(1, actual.length);
        assertTrue(Arrays.equals(expected, actual));
    }

    @Test
    public void getTargetedWordType_nounAndVerbFilterRecognition_arrayOfNounAndVerbElements()
            throws FilterWordsInvalidWordType, FilterMissingTargetWordTypeException {
        String[] expected = {"verb", "noun"};
        String[] actual = FilterCommandSlicer.getTargetedWordTypes("filter words by\\type -verb -noun");
        assertEquals(2, actual.length);
        assertTrue(Arrays.equals(expected, actual));
    }

    @Test
    public void getTargetedWordType_allTypeFilterRecognition_arrayOfAllWordType()
            throws FilterWordsInvalidWordType, FilterMissingTargetWordTypeException {
        String[] expected = {"adjective", "verb", "noun"};
        String[] actual = FilterCommandSlicer.getTargetedWordTypes("filter words by\\type -adjective -verb -noun");
        assertEquals(3, actual.length);
        assertTrue(Arrays.equals(expected, actual));
    }

    @Test
    public void getTargetedWordType_noWordTypeFound_filterCommandExceptionThrown() {
        assertThrows(FilterWordsInvalidWordType.class,
            () -> FilterCommandSlicer.getTargetedWordTypes("filter words by\\type -adverb -preposition"));
    }

    @Test
    public void getTargetedStringTags_oneStringTagNoSpace_success()
            throws FilterEmptyStringTagException, FilterMissingTargetStringsTagException {
        String[] expected = {"cs"};
        String[] actual = FilterCommandSlicer.getTargetedStringTags("filter words by\\contain -cs");
        assertEquals(1, actual.length);
        assertTrue(Arrays.equals(expected, actual));
    }

    @Test
    public void getTargetedStringTags_oneStringTagHaveSpace_success()
            throws FilterEmptyStringTagException, FilterMissingTargetStringsTagException {
        String[] expected = {"cs 2113 t"};
        String[] actual = FilterCommandSlicer.getTargetedStringTags("filter words by\\start -cs 2113 t");
        assertEquals(1, actual.length);
        assertTrue(Arrays.equals(expected, actual));
    }

    @Test
    public void getTargetedStringTags_twoStringTagsNoSpace_success()
            throws FilterEmptyStringTagException, FilterMissingTargetStringsTagException {
        String[] expected = {"cs 2113 t", "cs 2101"};
        String[] actual = FilterCommandSlicer
                .getTargetedStringTags("filter words by\\start -cs 2113 t -cs 2101");
        assertEquals(2, actual.length);
        assertTrue(Arrays.equals(expected, actual));
    }

    @Test
    public void getTargetedStringTags_twoStringTagsHaveSpace_success()
            throws FilterEmptyStringTagException, FilterMissingTargetStringsTagException{
        String[] expected = {"cs2113t", "cs2101"};
        String[] actual = FilterCommandSlicer
                .getTargetedStringTags("filter words by\\contain -cs2113t -cs2101");
        assertEquals(2, actual.length);
        assertTrue(Arrays.equals(expected, actual));
    }

    @Test
    public void getTargetedStringTags_noStringTagProvided_filterCommandExceptionThrown() {
        assertThrows(FilterEmptyStringTagException.class,
            () -> FilterCommandSlicer.getTargetedStringTags("filter words by\\start - "));
    }

}