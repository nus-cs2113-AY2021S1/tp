package seedu.rex.ui;

import org.junit.jupiter.api.Test;
import seedu.rex.data.exception.RexException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UiTest {

    @Test
    void readCommand_hello_returnsHelloString() throws RexException {

        String input = "hello";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertEquals("hello", new Ui().readCommand());
    }

    @Test
    void checkInputNotBlank_blankInput_expectException()  {

        assertThrows(RexException.class, () -> new Ui().checkInputNotBlank(""));
    }

    @Test
    void checkInputNotBlank_string_expectNoException()  {

        assertDoesNotThrow(() -> new Ui().checkInputNotBlank("a"));
    }
}