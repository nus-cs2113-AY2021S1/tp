package seedu.eduke8.hint;

import org.junit.jupiter.api.Test;
import seedu.eduke8.Eduke8Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HintTest extends Eduke8Test {

    @Test
    void getHintDescription_hintDescription_returnsHintDescription() {
        Hint hint = new Hint(PLACEHOLDER_HINT_DESCRIPTION);

        assertEquals(PLACEHOLDER_HINT_DESCRIPTION, hint.getDescription());
    }

    @Test
    void wasShown_hintShownToUser_expectsTrue() {
        Hint hint = new Hint(PLACEHOLDER_HINT_DESCRIPTION);

        assertFalse(hint.wasShown());
        // Once hint is shown to user, it is automatically marked as shown.
        hint.getDescription();
        assertTrue(hint.wasShown());
    }
}