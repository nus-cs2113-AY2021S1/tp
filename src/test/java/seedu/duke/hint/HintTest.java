package seedu.duke.hint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HintTest {

    @Test
    void getHintDescription_hintDescription_returnsHintDescription() {
        String inputHintDescription = "Please check the textbook page 88";
        Hint hint = new Hint(inputHintDescription);

        assertEquals(inputHintDescription, hint.getHintDescription());
    }
}