package seedu.hdbuy.command;

import org.junit.jupiter.api.Test;

class DefaultCommandTest {

    @Test void execute() {
        DefaultCommand defaultCommand = new DefaultCommand();
        defaultCommand.execute();
    }
}