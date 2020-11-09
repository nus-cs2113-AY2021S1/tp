package command;

import ui.Printer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class ExitCommandTest {
    Printer printer;
    ExitCommand command;

    public ExitCommandTest() {
        printer = new Printer();
        command = new ExitCommand(printer);
    }

    @Test
    void help_extraChars_pass() {
        command.execute();
        assertEquals(true, command.isExitCommand);
    }
}
