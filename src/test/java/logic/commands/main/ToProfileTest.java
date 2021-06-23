package logic.commands.main;

import exceptions.SchwarzeneggerException;
import logic.commands.CommandResult;
import logic.commands.ExecutionResult;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ui.CommonUi.EMPTY_STRING;

//@@author tienkhoa16
class ToProfileTest {

    @Test
    void testExecute_inputNullArguments_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> {
            new ToProfile().execute(null);
        });
    }

    @Test
    void testExecute_inputEmptyArguments_returnSuccess() throws SchwarzeneggerException {
        System.setIn(new ByteArrayInputStream("end".getBytes()));
        CommandResult result = new ToProfile().execute(EMPTY_STRING);
        System.setIn(System.in);
        assertEquals(ExecutionResult.OK, result.getStatus());
    }
}
