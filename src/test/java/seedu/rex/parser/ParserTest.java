package seedu.rex.parser;

import org.junit.jupiter.api.Test;
import seedu.rex.commands.ExitCommand;
import seedu.rex.data.exception.RexException;
import seedu.rex.data.hospital.Doctor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    void parse_bye_returnsExitCommand() throws RexException {
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
    }

    @Test
    void parse_invalidInput_expectException() {
        assertThrows(RexException.class, () -> Parser.parse("exit"));
    }

    @Test
    void readDoctor_doctorTan_returnsDoctorTan() {
        Doctor doctor = new Doctor("Tan");
        assertEquals(Parser.readDoctor("Tan").getName(), doctor.getName());
    }
}
