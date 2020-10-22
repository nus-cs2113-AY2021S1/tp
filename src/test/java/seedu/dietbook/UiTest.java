package seedu.dietbook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UiTest {

    private Ui ui;

    @BeforeEach
    public void setUp() {
        ui = new Ui();
    }

    @Test
    void stringDateTime_nullInput_expectAssertionError() {
        assertThrows(AssertionError.class, () -> ui.stringDateTime(null));
    }

    @Test
    void stringDateTime_LocalDateTime_returnsStringOfLocalDateTime() {
        LocalDateTime dateTime = LocalDateTime.parse("2020-10-21T23:59");
        assertEquals("21 Oct 2020 2359",ui.stringDateTime(dateTime));
    }

    @Test
    void stringDateTime_LocalDateTimeWithSeconds_returnsStringOfLocalDateTimeWithoutSeconds() {
        LocalDateTime dateTime = LocalDateTime.parse("2020-10-21T23:59:22");
        assertEquals("21 Oct 2020 2359",ui.stringDateTime(dateTime));
    }

    @Test
    void trimStringGetLength_nullInput_expectAssertionError() {
        assertThrows(AssertionError.class, () -> ui.trimStringGetLength(null));
    }

    @Test
    void trimStringGetLength_stringWithNoLeadingOrTrailingSpaces_returnsLengthFour() {
        assertEquals(4, ui.trimStringGetLength("food"));
    }

    @Test
    void trimStringGetLength_StringWithLeadingSpaces_returnsLengthFour() {
        assertEquals(4, ui.trimStringGetLength("    food"));
    }

    @Test
    void trimStringGetLength_StringWithTrailingSpaces_returnsLengthFour() {
        assertEquals(4, ui.trimStringGetLength("food    "));
    }

    @Test
    void trimStringGetLength_StringWithLeadingAndTrailingSpaces_returnsLengthFour() {
        assertEquals(4, ui.trimStringGetLength("    food    "));
    }

    @Test
    void trimString_nullInput_expectAssertionError() {
        assertThrows(AssertionError.class, () -> ui.trimString(null));
    }

    @Test
    void trimString_StringWithLeadingSpaces_returnsStringWithoutLeadingSpaces() {
        assertEquals("food", ui.trimString("    food"));
    }

    @Test
    void trimString_StringWithTrailingSpaces_returnsStringWithoutTrailingSpaces() {
        assertEquals("food", ui.trimString("food    "));
    }

    @Test
    void trimString_StringWithLeadingAndTrailingSpaces_returnsStringWithoutLeadingAndTrailingSpaces() {
        assertEquals("food", ui.trimString("    food    "));
    }

    @Test
    void trimString_StringWithNoLeadingAndTrailingSpaces_returnsString() {
        assertEquals("food", ui.trimString("food"));
    }
}
