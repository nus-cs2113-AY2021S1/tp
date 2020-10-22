package seedu.eduke8.explanation;

import org.junit.jupiter.api.Test;
import seedu.eduke8.Eduke8Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExplanationTest extends Eduke8Test {

    @Test
    void getExplanationDescription_explanationDescription_returnsExplanationDescription() {
        Explanation explanation = new Explanation(PLACEHOLDER_EXPLANATION_DESCRIPTION);

        assertEquals(PLACEHOLDER_EXPLANATION_DESCRIPTION, explanation.getDescription());
    }
}
