package parser;

import access.Access;
import exception.InvalidInputException;
import org.junit.jupiter.api.Test;

import static common.Messages.CHAPTER;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HistoryCommandParserTest {
    private HistoryCommandParser parser = new HistoryCommandParser();

    @Test
    public void parse_editHistoryInvalidCommandFormat_expectException() {
        final String[] inputs = {
                "history wrong args format",
                // not date format
                "history 20-10-2020",
                // not yyyy-mm-dd but dd-mm-yyyy
                "history 10-20-2020",
                // not yyyy-mm-dd but mm-dd-yyyy
                "history 1",
        };
        for (String input : inputs) {
            assertThrows(InvalidInputException.class, () -> parser.parse(input));
        }
    }
}
