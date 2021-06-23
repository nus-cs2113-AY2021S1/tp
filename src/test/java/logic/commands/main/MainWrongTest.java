package logic.commands.main;

import exceptions.InvalidCommandWordException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ui.CommonUi.EMPTY_STRING;

//@@author tienkhoa16
class MainWrongTest {

    @Test
    void testExecute_inputNullArguments_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> {
            new MainWrong().execute(null);
        });
    }

    @Test
    void testExecute_inputEmptyArguments_returnSuccess() {
        assertThrows(InvalidCommandWordException.class, () -> {
            new MainWrong().execute(EMPTY_STRING);
        });
    }
}
