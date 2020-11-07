package seedu.duke.logic.commands.buscommand;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.CustomException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class BusCommandTest {

    @Test
    void busCommandConstructor_invalidStop_expectException() {
        String input = "NTU North Spine";
        try {
            BusCommand command = new BusCommand(input);
        } catch (CustomException e) {
            assertEquals("Oh no! I do not understand which bus stop you are looking for.", e.toString());
        }

    }

    @Test
    void busCommandConstructor_inputBlank_expectException() {
        String input = " ";
        try {
            BusCommand command = new BusCommand(input);
        } catch (CustomException e) {
            assertEquals("Oh no! I do not understand which bus stop you are looking for.", e.toString());
        }
    }

    @Test
    void busCommandConstructor_inputSpellingError_expectException() {
        String input = "musuem";
        try {
            BusCommand command = new BusCommand(input);
        } catch (CustomException e) {
            assertEquals("Possible Locs shown", e.toString());
        }
    }

    @Test
    void executeCommand_inputValidStopRandomCaps_success() throws CustomException {
        String input = "KeNt RiDge";
        BusCommand command = new BusCommand(input);
        assertDoesNotThrow(()->command.executeCommand());
    }

    @Test
    void executeCommand_inputValidStopExactCaps_success() throws CustomException {
        String input = "University Town";
        BusCommand command = new BusCommand(input);
        assertDoesNotThrow(()->command.executeCommand());
    }

}
