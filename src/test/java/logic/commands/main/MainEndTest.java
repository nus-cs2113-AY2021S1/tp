package logic.commands.main;

import exceptions.EndException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ui.CommonUi.EMPTY_STRING;

//@@author tienkhoa16
class MainEndTest {

    @Test
    void testExecute_inputNullArguments_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> {
            new MainEnd().execute(null);
        });
    }

    @Test
    void testExecute_inputEmptyArguments_ValidStorage_throwsEndException() {
        assertThrows(EndException.class, () -> {
            new MainEnd().execute(EMPTY_STRING);
        });
    }
}
