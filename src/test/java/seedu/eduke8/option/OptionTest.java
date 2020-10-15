package seedu.eduke8.option;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OptionTest {
    private static final String PLACEHOLDER_OPTION_DESCRIPTION = "Option";

    @Test
    void getDescription_placeholderOptionDescription_returnsOptionDescription() {
        Option option = new Option(PLACEHOLDER_OPTION_DESCRIPTION);

        assertEquals(PLACEHOLDER_OPTION_DESCRIPTION, option.getDescription());
    }

    @Test
    void wasShown_placeholderOptionDescription_expectsTrue() {
        Option option = new Option(PLACEHOLDER_OPTION_DESCRIPTION);

        String optionDescription = option.getDescription();
        assertTrue(option.wasShown());
    }

    @Test
    void markAsCorrectAnswer_optionMarkedAsCorrectOption_expectsTrue() {
        Option option = new Option(PLACEHOLDER_OPTION_DESCRIPTION);

        option.markAsCorrectAnswer();
        assertTrue(option.isCorrectAnswer());
    }
}
