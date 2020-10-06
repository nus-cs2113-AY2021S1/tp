package seedu.rex.parser;

import org.junit.jupiter.api.Test;
import seedu.rex.commands.ExitCommand;
import seedu.rex.data.exception.RexException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    void parse_bye_returnsExitCommand() throws RexException {
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
    }

    @Test
    void parse_invalidInput_expectException() {
        assertThrows(RexException.class, () -> Parser.parse("exit"));
    }
}
