package seedu.rex.commands;

import org.junit.jupiter.api.Test;
import seedu.rex.data.exception.RexException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandTest {

    @Test
    void isInteger_456456_returnsTrue() {
        RetrievePatientCommand retrievePatientCommand = new RetrievePatientCommand("");
        assertTrue(retrievePatientCommand.isInteger("456456"));
    }

    @Test
    void isInteger_string_returnsFalse() {
        RetrievePatientCommand retrievePatientCommand = new RetrievePatientCommand("");
        assertFalse(retrievePatientCommand.isInteger("string"));
    }

    @Test
    void extractNric_retrieveS9123456D_returnsS9123456DString() {
        RetrievePatientCommand retrievePatientCommand = new RetrievePatientCommand("");
        try {
            assertEquals(retrievePatientCommand.extractNric("retrieve S9123456D", "retrieve"), "S9123456D");
        } catch (RexException e) {
            e.printStackTrace();
        }
    }

    @Test
    void extractNric_retrieveS91D_expectException() {
        RetrievePatientCommand retrievePatientCommand = new RetrievePatientCommand("");
        assertThrows(RexException.class, () -> retrievePatientCommand.extractNric("retrieve S91D", "retrieve"));
    }
}