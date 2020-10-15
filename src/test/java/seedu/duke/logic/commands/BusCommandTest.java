package seedu.duke.logic.commands;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import seedu.duke.exceptions.CustomException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class BusCommandTest {

    @Test
    public void busCommandConstructor_empty_exceptionThrown() throws CustomException {
        assertThrows(CustomException.class, (Executable) new BusCommand(" "));
    }

    @Test
    public void busCommandConstructor_invalidBusStop_exceptionThrown() throws CustomException {
        assertThrows(CustomException.class, (Executable) new BusCommand("NTU North Spine"));
    }
}
