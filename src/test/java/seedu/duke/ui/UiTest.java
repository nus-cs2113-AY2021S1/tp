package seedu.duke.ui;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class UiTest {

    @Test
    void getCommand_success(){
        String command = "/routemap";
        InputStream in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);
        String actualOutput = Ui.getCommand();
        assertEquals("/routemap",actualOutput);
    }
}