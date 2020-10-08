package seedu.duke.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.CustomException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ParserTest {

    @Test
    void extractType_unknownCommand_expectException() {
        String command = "Hello";
        Parser p = new Parser(command);
        assertThrows(CustomException.class, () -> p.extractType());
    }

    @Test
    void extractType_exitCommand_returnsFalse() throws CustomException {
        String command = "/exit";
        Parser p = new Parser(command);
        boolean isOngoing = p.extractType();
        assertEquals(isOngoing, false);
    }
}