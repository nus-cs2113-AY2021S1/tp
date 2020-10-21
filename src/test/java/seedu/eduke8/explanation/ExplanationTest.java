package seedu.eduke8.explanation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExplanationTest {

    @Test
    void getExplanationDescription_explanationDescription_returnsExplanationDescription() {
        String inputExplanationDescription = "Option A gives the best explanation out of all.";
        Explanation explanation = new Explanation(inputExplanationDescription);

        assertEquals(inputExplanationDescription, explanation.getDescription());
    }
}
