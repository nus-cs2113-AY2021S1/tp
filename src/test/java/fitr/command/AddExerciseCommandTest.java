package fitr.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class AddExerciseCommandTest {
    @Test
    public void testAddExerciseExit() {
        Command addExercise = new AddExerciseCommand("run /500");
        assertFalse(addExercise.isExit());
    }
}
