package parser;

import exception.InvalidInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {
    @Test
    public void parse_addCommandInvalidArgs_expectException() {
        Parser parser = new Parser();
        final String[] inputs = {
                "add",
                "add ",
                "add wrong args format",
                // no question and answer separator prefix
                "add q:When is v1.0 due? a:15 Oct 2020",
                // no question prefix
                "add When is v1.0 due? | a:15 Oct 2020",
                // no answer prefix
                "add q:When is v1.0 due? | 15 Oct 2020",
        };
        for (String input : inputs) {
            assertThrows(InvalidInputException.class, () -> parser.parse(input));
        }
    }

    @Test
    public void parse_listCommandWithArgs_expectException() {
        Parser parser = new Parser();
        final String input = "list args";
        assertThrows(InvalidInputException.class, () -> parser.parse(input));
    }

    @Test
    public void parse_exitCommandWithArgs_expectException() {
        Parser parser = new Parser();
        final String input = "exit args";
        assertThrows(InvalidInputException.class, () -> parser.parse(input));
    }
}