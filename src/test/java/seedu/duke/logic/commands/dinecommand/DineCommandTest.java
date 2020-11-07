package seedu.duke.logic.commands.dinecommand;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.CustomException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DineCommandTest {

    @Test
    public void executeCommand_emptyInput_expectException() {
        String input = "";
        DineCommand com = new DineCommand(input);
        assertThrows(CustomException.class, com::executeCommand);
    }

    @Test
    public void executeCommand_validInput_expectNoException() {
        String input = "science";
        DineCommand com = new DineCommand(input);
        assertDoesNotThrow(com::executeCommand);
    }

}