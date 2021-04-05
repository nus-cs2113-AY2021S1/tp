package seedu.hdbuy.parser;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.hdbuy.command.Command;
import seedu.hdbuy.command.DefaultCommand;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {

    @Test void parsePositiveTest() {
        List<String> testInputs = new ArrayList<>(Arrays.asList(
                "filter location jurong",
                "sort asc",
                "sort desc",
                "find",
                "clear",
                "list",
                "help",
                "shortlist",
                "save 1",
                "remove 1",
                "exit"
        ));
        for (String input : testInputs) {
            Command command = Parser.parse(input);
            assertFalse(command instanceof DefaultCommand);
        }
    }

    @Test void parseNegativeTest() {
        List<String> testInputs = new ArrayList<>(Arrays.asList(
                "qkwjdwqdq",
                "filter lease_remaining 101",
                "filter lease_remaining -101",
                " ",
                ""
        ));
        for (String input : testInputs) {
            Command command = Parser.parse(input);
            assertTrue(command instanceof DefaultCommand);
        }
    }
}