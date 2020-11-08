package fitr.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeleteCommandTest {
    @Test
    public void completeGoalCommandTest() {
        Command deleteCommand = new DeleteCommand("food 1");
        assertFalse(deleteCommand.isExit());
    }
}
