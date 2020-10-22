package seedu.eduke8.hint;

import org.junit.jupiter.api.Test;
import seedu.eduke8.Eduke8Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HintTest extends Eduke8Test {

    @Test
    void getHintDescription_hintDescription_returnsHintDescription() {
        Hint hint = new Hint(PLACEHOLDER_HINT_DESCRIPTION);

        assertEquals(PLACEHOLDER_HINT_DESCRIPTION, hint.getDescription());
    }
}