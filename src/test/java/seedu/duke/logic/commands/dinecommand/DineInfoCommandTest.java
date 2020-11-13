package seedu.duke.logic.commands.dinecommand;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.CustomException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


class DineInfoCommandTest {

    @Test
    public void executeCommand_emptyInput_expectException() {
        String input = "";
        DineInfoCommand com = new DineInfoCommand(input);
        assertThrows(CustomException.class, com::executeCommand);
    }

    @Test
    public void executeCommand_validInput_expectNoException() {
        String input = "arise";
        DineInfoCommand com = new DineInfoCommand(input);
        assertDoesNotThrow(com::executeCommand);
    }

}
