package fitr.ui;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static fitr.ui.Ui.read;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UiTest {
    @Test
    public void readUserInput() {
        Ui uiText = new Ui();

        String input = "add 5";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertEquals("add 5", uiText.read());
    }
}