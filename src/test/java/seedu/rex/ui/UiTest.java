package seedu.rex.ui;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class UiTest {

    @Test
    void readCommand_hello_returnsHelloString() {

        String input = "hello";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertEquals("hello", new Ui().readCommand());
    }
}