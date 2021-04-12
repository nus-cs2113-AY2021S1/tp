package seedu.hdbuy.ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TermDefinitionTest {

    @Test void getDefinition() {
        String[] terms = {"filter <attribute> <value>", "list", "clear", "find", "sort <order>", "save <index>",
            "remove <index>", "shortlist", "help", "exit"};

        for (String term : terms) {
            assertNotNull(TermDefinition.getDefinition(term));
        }
    }

    @Test void negativeTest() {
        assertEquals("Invalid definition", TermDefinition.getDefinition("random"));
        assertEquals("Invalid example.", TermDefinition.getExample("random"));
    }
}