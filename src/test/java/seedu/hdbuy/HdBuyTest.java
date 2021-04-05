package seedu.hdbuy;

import org.junit.jupiter.api.Test;

import seedu.hdbuy.common.exception.InvalidIndexException;
import seedu.hdbuy.common.exception.InvalidParameterException;
import seedu.hdbuy.parser.CommandEvaluator;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HdBuyTest {

    @Test public void parserTest() {
        try {
            assertEquals(0, CommandEvaluator.extractInfo("filter"));
        } catch (InvalidParameterException e) {
            assertEquals("You must enter the correct number of parameters.", e.getMessage());
        } catch (InvalidIndexException e) {
            // isValidIndex is working
        }
        try {
            assertEquals(0, CommandEvaluator.extractInfo("filter location"));
        } catch (InvalidParameterException e) {
            assertEquals("You must enter the correct number of parameters.", e.getMessage());
        } catch (InvalidIndexException e) {
            // isValidIndex is working
        }
    }
}
