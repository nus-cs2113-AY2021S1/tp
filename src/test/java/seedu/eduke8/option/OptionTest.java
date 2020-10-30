package seedu.eduke8.option;

import org.junit.jupiter.api.Test;
import seedu.eduke8.Eduke8Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionTest extends Eduke8Test {
    @Test
    void getDescription_placeholderOptionDescription_returnsOptionDescription() {
        Option option = new Option(PLACEHOLDER_OPTION_ONE_DESCRIPTION);

        assertEquals(PLACEHOLDER_OPTION_ONE_DESCRIPTION, option.getDescription());
    }

    @Test
    void wasShown_placeholderOptionDescription_expectsTrue() {
        Option option = new Option(PLACEHOLDER_OPTION_ONE_DESCRIPTION);

        assertFalse(option.wasShown());
        option.getDescription();
        assertTrue(option.wasShown());
    }

    @Test
    void markAsCorrectAnswer_optionMarkedAsCorrectOption_expectsTrue() {
        Option option = new Option(PLACEHOLDER_OPTION_ONE_DESCRIPTION);

        assertFalse(option.isCorrectAnswer());
        option.markAsCorrectAnswer();
        assertTrue(option.isCorrectAnswer());
    }
}
