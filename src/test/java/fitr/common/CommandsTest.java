package fitr.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandsTest {
    @Test
    public void commandsTest_correctResult() {
        assertEquals("bye",Commands.COMMAND_BYE);
        assertEquals("food", Commands.COMMAND_FOOD);
        assertEquals("exercise", Commands.COMMAND_EXERCISE);
        assertEquals("goal", Commands.COMMAND_GOAL);
        assertEquals("help", Commands.COMMAND_HELP);
        assertEquals("recommend", Commands.COMMAND_RECOMMEND);
        assertEquals("view", Commands.COMMAND_VIEW);
        assertEquals("delete", Commands.COMMAND_DELETE);
        assertEquals("edit", Commands.COMMAND_EDIT);
    }
}
