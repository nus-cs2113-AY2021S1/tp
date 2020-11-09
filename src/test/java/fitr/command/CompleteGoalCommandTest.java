package fitr.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class CompleteGoalCommandTest {
    @Test
    public void completeGoalCommandTest() {
        Command completeGoal = new CompleteGoalCommand("goal 2");
        assertFalse(completeGoal.isExit());
    }
}
