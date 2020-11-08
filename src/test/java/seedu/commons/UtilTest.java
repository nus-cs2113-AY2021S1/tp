package seedu.commons;

import org.junit.jupiter.api.Test;
import seedu.exceptions.InvalidDatetimeException;
import seedu.exceptions.InvalidPriorityException;
import seedu.task.Priority;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.font.Colors.ANSI_GREEN;
import static seedu.font.Colors.ANSI_RESET;


class UtilTest {

    @Test
    void generatePadStringWithCharAndLength_zeroLength_success() {
        assertEquals("", Util.generatePadStringWithCharAndLength(' ', 0));
        assertEquals("", Util.generatePadStringWithCharAndLength('$', 0));
    }

    @Test
    void generatePadStringWithCharAndLength_nonZeroLength_success() {
        assertEquals("$$$$$$", Util.generatePadStringWithCharAndLength('$', 6));
        assertEquals("----", Util.generatePadStringWithCharAndLength('-', 4));
    }

    @Test
    void generatePadStringWithCharAndLength_negativeLength_success() {
        // Effect of - will be left adjust, which doesnt affect the return value.
        assertEquals("$", Util.generatePadStringWithCharAndLength('$', -1));
        assertEquals("$$", Util.generatePadStringWithCharAndLength('$', -2));
    }

    @Test
    void limitStringWithDots_lengthGreaterThanThree_success() {
        assertEquals("abc...", Util.limitStringWithDots("abcdefg", 6));
        assertEquals("abcdef", Util.limitStringWithDots("abcdef", 6));
        assertEquals("abcdefg", Util.limitStringWithDots("abcdefg", 10));
    }

    @Test
    void limitStringWithDots_lengthEqualThree_success() {
        assertEquals("...", Util.limitStringWithDots("abcdefg", 3));
        assertEquals("abc", Util.limitStringWithDots("abc", 3));
    }

    @Test
    void limitStringWithDots_lengthLessThanThree_success() {
        assertEquals("ab", Util.limitStringWithDots("abcdefg", 2));
        assertEquals("", Util.limitStringWithDots("abcdef", -1));
        assertEquals("", Util.limitStringWithDots("abcdefg", 0));
    }

    @Test
    void putsIntoArray_charArrayLongerThanString_success() {
        String testing = "Testing string!";
        char[] charArray = new char[20];
        Util.putsIntoArray(testing, charArray, 0);
        for (int i = 0; i < testing.length(); i++) {
            assertEquals(testing.charAt(i), charArray[i]);
        }
    }

    @Test
    void putsIntoArray_charArrayEqualToString_success() {
        String testing = "Testing";
        char[] charArray = new char[7];
        Util.putsIntoArray(testing, charArray, 0);
        for (int i = 0; i < testing.length(); i++) {
            assertEquals(testing.charAt(i), charArray[i]);
        }
    }

    @Test
    void putsIntoArray_charArrayShorterThanString_IndexOutOfBoundExceptionThrown() {
        String testing = "Testing string!";
        char[] charArray = new char[10];
        try {
            Util.putsIntoArray(testing, charArray, 0);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        }
    }

    @Test
    void putsIntoArrayWithCentralise_charArrayLongerThanString_success() {
        String testing = "Testing string!";
        char[] charArray = new char[20];
        Util.putsIntoArrayWithCentralise(testing, charArray, 0, 19);
        for (int i = 0; i < testing.length(); i++) {
            assertEquals(testing.charAt(i), charArray[i + 2]);
        }
    }

    @Test
    void putsIntoArrayWithCentralise_charArrayEqualToString_success() {
        String testing = "Testing";
        char[] charArray = new char[7];
        Util.putsIntoArrayWithCentralise(testing, charArray, 0, 6);
        for (int i = 0; i < testing.length(); i++) {
            assertEquals(testing.charAt(i), charArray[i]);
        }
    }

    @Test
    void putsIntoArrayWithCentralise_charArrayShorterThanString_IndexOutOfBoundExceptionThrown() {
        String testing = "Testing string!";
        char[] charArray = new char[10];
        try {
            Util.putsIntoArrayWithCentralise(testing, charArray, 0, 9);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        }
    }

    @Test
    void dateStringToDate_correctFormat_success() throws InvalidDatetimeException {
        LocalDate expected = LocalDate.of(2020, 11, 11);
        LocalDate actual = Util.dateStringToDate("11-11-2020");
        assertEquals(expected, actual);
    }

    @Test
    void dateStringToDate_invalidFormat_InvalidDatetimeExceptionThrown() {
        try {
            Util.dateStringToDate("111-11-2020");
            fail();
        } catch (InvalidDatetimeException e) {
            assertTrue(true);
        }
    }

    @Test
    void timeStringToTime_correctFormat_success() throws InvalidDatetimeException {
        LocalTime expected = LocalTime.of(22,0);
        LocalTime actual = Util.timeStringToTime("2200");
        assertEquals(expected, actual);
    }

    @Test
    void timeStringToTime_invalidFormat_InvalidDatetimeExceptionThrown() {
        try {
            Util.timeStringToTime("2500");
            fail();
        } catch (InvalidDatetimeException e) {
            assertTrue(true);
        }
    }

    @Test
    void priorityStringToPriority_correctFormat_success() throws InvalidPriorityException {
        Priority expected = Priority.HIGH;
        Priority actual = Util.priorityStringToPriority("3");
        assertEquals(expected, actual);
    }

    @Test
    void priorityStringToPriority_invalidPriority_InvalidPriorityExceptionThrown() {
        try {
            Util.priorityStringToPriority("0");
            fail();
        } catch (InvalidPriorityException e) {
            assertTrue(true);
        }
    }

    @Test
    void dateToString_success() {
        String expected = "";
        String actual = Util.dateToString(null);
        assertEquals(expected, actual);

        expected = " 11-11-2020";
        actual = Util.dateToString(LocalDate.of(2020,11,11));
        assertEquals(expected, actual);
    }

    @Test
    void timeToString_success() {
        String expected = "";
        String actual = Util.timeToString(null);
        assertEquals(expected, actual);

        expected = " 2000";
        actual = Util.timeToString(LocalTime.of(20,0));
        assertEquals(expected, actual);
    }

    @Test
    void priorityToString_success() {
        String expected = " " + ANSI_GREEN + "LOW" + ANSI_RESET;
        String actual = Util.priorityToString(Priority.LOW);
        assertEquals(expected, actual);
    }
}