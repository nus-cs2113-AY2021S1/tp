package seedu.dietbook.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UiHelperTest {

    private UiHelper uiHelper;

    @BeforeEach
    public void setUp() {
        uiHelper = new UiHelper();
    }

    @Test
    void isEmptyString_nullInput_expectAssertionError() {
        assertThrows(AssertionError.class, () -> uiHelper.isEmptyString(null));
    }

    @Test
    void isEmptyString_stringWithNoLeadingOrTrailingSpaces_returnsFalse() {
        assertFalse(uiHelper.isEmptyString("food"));
    }

    @Test
    void isEmptyString_StringWithLeadingSpaces_returnsTrue() {
        assertTrue(uiHelper.isEmptyString(""));
    }

    @Test
    void isEmptyString_StringWithTrailingSpaces_returnsTrue() {
        assertTrue(uiHelper.isEmptyString("   "));
    }

    @Test
    void isEmptyString_StringWithLeadingAndTrailingSpaces_returnsFalse() {
        assertFalse(uiHelper.isEmptyString("    food    "));
    }
}
