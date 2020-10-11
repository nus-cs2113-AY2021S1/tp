package seedu.rex.commands;

import org.junit.jupiter.api.Test;
import seedu.rex.data.exception.RexException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandTest {

    @Test
    void isInteger_456456_returnsTrue() {
        RetrieveCommand retrieveCommand = new RetrieveCommand("");
        assertTrue(retrieveCommand.isInteger("456456"));
    }

    @Test
    void extractNric_retrieveS9123456D_returnsS9123456DString() {
        RetrieveCommand retrieveCommand = new RetrieveCommand("");
        try {
            assertEquals(retrieveCommand.extractNric("retrieve S9123456D", "retrieve"), "S9123456D");
        } catch (RexException e) {
            e.printStackTrace();
        }
    }

    @Test
    void extractNric_retrieveS91D_expectException() {
        RetrieveCommand retrieveCommand = new RetrieveCommand("");
        assertThrows(RexException.class, () -> retrieveCommand.extractNric("retrieve S91D", "retrieve"));
    }
}