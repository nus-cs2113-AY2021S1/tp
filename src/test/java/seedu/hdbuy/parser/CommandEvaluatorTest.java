package seedu.hdbuy.parser;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.hdbuy.common.CommandKey;
import seedu.hdbuy.common.HdBuyLogger;
import seedu.hdbuy.common.Unit;
import seedu.hdbuy.common.exception.InvalidIndexException;
import seedu.hdbuy.common.exception.InvalidParameterException;
import seedu.hdbuy.storage.UnitDecoder;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

class CommandEvaluatorTest {

    @Test void filterTest() {
        List<String> testInputs = new ArrayList<String>(Arrays.asList(
                "filter location",
                "filter",
                "filter type 7 room",
                "filter type",
                "filter type 10room",
                "filter lease_remaining woodlands",
                "filter lease_remaining 100",
                "filter lease_remaining 99.0",
                "filter lease_remaining -1",
                "filter lease_remaining"
        ));
        for (String input : testInputs) {
            try {
                CommandKey output = CommandEvaluator.extractInfo(input);
                assertNull(output);
            } catch (InvalidParameterException | InvalidIndexException e) {
                // continue
            }
        }
    }

    @Test void trimTest() {
        List<String> testInputs = new ArrayList<String>(Arrays.asList(
                "filter location woodlands",
                "filter     location    woodlands",
                "filter location ang mo kio",
                "filter location   ang    mo    kio",
                "sort asc",
                "sort   asc   ",
                "sort desc     ",
                "save 1",
                "save   1   ",
                "save 1    ",
                "save   1",
                "remove 1",
                "remove   1   ",
                "remove 1    ",
                "remove   1",
                "save 0",
                "save -1",
                "save 101",
                "save 50.5"
        ));

        for (String input : testInputs) {
            try {
                CommandEvaluator.extractInfo(input);
            } catch (InvalidParameterException e) {
                fail();
            } catch (InvalidIndexException e) {
                String index = e.getIndex();
                if (index.equals("-1") || index.equals("0") || index.equals("101") || index.equals("50.5")) {
                    continue;
                }
                fail();
            }
        }
    }
}