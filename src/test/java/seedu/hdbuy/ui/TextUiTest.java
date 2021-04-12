package seedu.hdbuy.ui;

import org.junit.jupiter.api.Test;

import seedu.hdbuy.command.HelpCommand;

class TextUiTest {

    @Test
    void showHelp() {
        HelpCommand helpCommand = new HelpCommand();
        helpCommand.execute();
    }
}