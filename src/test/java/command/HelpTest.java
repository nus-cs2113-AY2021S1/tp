package command;

import org.junit.jupiter.api.Test;
import ui.Printer;

class HelpTest {
    Printer printer;
    HelpCommand command;

    public HelpTest() {
        printer = new Printer();
        command = new HelpCommand(printer);
    }

    @Test
    void help_extraChars_pass() {
        command.execute();
    }
}
