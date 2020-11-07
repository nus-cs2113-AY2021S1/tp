package fitr.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class AddGoalCommandTest {
    @Test
    public void testAddFoodExit() {
        Command addGoal = new AddGoalCommand("burn 500 cal");
        assertFalse(addGoal.isExit());
    }
}
