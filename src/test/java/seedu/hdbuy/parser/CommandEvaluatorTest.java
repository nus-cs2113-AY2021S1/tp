package seedu.hdbuy.parser;

import org.junit.jupiter.api.Test;
import seedu.hdbuy.common.CommandKey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

class CommandEvaluatorTest {

    @Test void filterNegativeTest() {
        List<String> testInputs = new ArrayList<>(
                Arrays.asList("filter location", "filter", "filter type 7 room", "filter type", "filter type 10room",
                        "filter lease_remaining woodlands", "filter lease_remaining 100", "filter lease_remaining 99.0",
                        "filter lease_remaining -1", "filter lease_remaining", "   filter    location", "   filter",
                        "   filter   type   7    room", "    filter    type   4_room", "filter type 4_room",
                        "filter type $*^#*&%@%", "   filter    type    ", "   filter   type    10room",
                        "   filter    lease_remaining    woodlands", "filter lease_remaining $*^#*&%@%",
                        "   filter    lease_remaining    100", "   filter    lease_remaining    60   50   ",
                        "   filter     lease_remaining    99.0", "   filter   lease_remaining     -1",
                        "     filter     lease_remaining"));
        negativeTest(testInputs);
    }

    @Test void filterPositiveTest() {
        List<String> testInputs = new ArrayList<>(
                Arrays.asList("filter location ang mo kio", "  filter    location   ang   mo   kio   ",
                        "filter location bishan", "   filter    location    bishan  ", "   filter type 4 room",
                        "   filter    type    4    room", "   filter    type    executive",
                        "   filter    type    4    room", "filter type 4 room", "filter    type    4    room",
                        "filter    type    executive", "filter    type    4    room",
                        "   filter    lease_remaining    50   ", "filter lease_remaining 50",
                        "filter lease_remaining 0", "filter lease_remaining 99"));
        positiveTest(testInputs);
    }

    @Test void sortNegativeTest() {
        List<String> testInputs = new ArrayList<>(
                Arrays.asList("sort", "   sort   ", "sort gg", "  sort   gg  ", "sort asc desc", "  sort    asc   desc",
                        "  sort    )$(#)(*@  "));
        negativeTest(testInputs);
    }

    @Test void sortPositiveTest() {
        List<String> testInputs =
                new ArrayList<>(Arrays.asList("sort asc", "   sort  asc   ", "sort desc", "  sort   desc  "));
        positiveTest(testInputs);
    }

    @Test void editPositiveTest() {
        List<String> testInputs =
                new ArrayList<>(Arrays.asList("save 1", "   save  1   ", "remove 1", "  remove   1  "));
        positiveTest(testInputs);
    }

    @Test void editNegativeTest() {
        List<String> testInputs = new ArrayList<>(
                Arrays.asList("save qowidno", "   save  kqwdbqk  ", "remove qwdionwq", "  remove   oqwdiqw  ", "save",
                        "   save    ", "remove", "  remove     ", "save 1.0", "   save  1.0  ", "remove 1.0",
                        "  remove   1.0  ", "  remove   #(#()&@(@&  ", "  save   #(#()&@(@&  "));
        negativeTest(testInputs);
    }

    private void negativeTest(List<String> testInputs) {
        for (String input : testInputs) {
            try {
                CommandKey output = CommandEvaluator.extractInfo(input);
                assertNull(output);
            } catch (Exception e) {
                // continue
            }
        }
    }

    private void positiveTest(List<String> testInputs) {
        for (String input : testInputs) {
            try {
                CommandEvaluator.extractInfo(input);
            } catch (Exception e) {
                fail();
            }
        }
    }
}