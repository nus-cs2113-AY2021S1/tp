package seedu.hdbuy.parser;

import org.junit.jupiter.api.Test;
import seedu.hdbuy.command.Command;
import seedu.hdbuy.command.DefaultCommand;
import seedu.hdbuy.common.Unit;
import seedu.hdbuy.common.exception.EmptyInputException;
import seedu.hdbuy.data.SearchedUnits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {

    @Test void parsePositiveTest() {
        List<String> testInputs = new ArrayList<>(Arrays
            .asList("filter location jurong", "sort asc", "sort desc", "find", "clear", "list", "help", "shortlist",
                "save 1", "remove 1", "exit"));
        for (String input : testInputs) {
            try {
                Command command = Parser.parse(input);
                assertFalse(command instanceof DefaultCommand);
            } catch (EmptyInputException e) {
                fail();
            }
        }
    }

    @Test void parseNegativeTest() {
        List<String> testInputs = new ArrayList<>(Arrays
            .asList("qkwjdwqdq", "filter lease_remaining 101", "filter lease_remaining -101", "remove", "find xxx",
                "clear xxx", "help xxx", "exit xxx", "list xxx", "save", "sort", "shortlist xxx", "clear xxx"));
        for (String input : testInputs) {
            try {
                Command command = Parser.parse(input);
                assertTrue(command instanceof DefaultCommand);
            } catch (EmptyInputException e) {
                fail();
            }
        }
    }

    @Test void parseEdgeTest() {
        List<String> testInputs = new ArrayList<>(Arrays.asList(" ", ""));

        for (String input : testInputs) {
            try {
                Parser.parse(input);
                fail();
            } catch (EmptyInputException e) {
                // continue
            }
        }
        assertTrue(true);
    }

    @Test void parseIndexTest() {
        Unit unit =
            new Unit("JURONG WEST", "4 ROOM", 429000, 990, " 82 years 06 months", "664A JURONG WEST ST 64", 222222);
        SearchedUnits.clearSearchedUnits();
        SearchedUnits.addToResult(unit);

        try {
            Parser.parse("save 101");
        } catch (EmptyInputException e) {
            fail();
        }
    }
}