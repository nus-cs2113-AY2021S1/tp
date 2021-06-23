package logic.commands.diet.dietmanager;

import logic.commands.CommandResult;
import org.junit.jupiter.api.Test;
import storage.diet.DietStorage;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ui.workout.workoutmanager.WorkoutManagerUi.CLEAR_ABORTED;

//@@author CFZeon
public class DietSessionClearTest {
    private static final String EMPTY_STRING = "";

    @Test
    void testExecute_inputEmptyArguments_NullStorage_throwsNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> {
            new DietSessionClear().execute(EMPTY_STRING, (DietStorage) null);
        });
    }

    @Test
    void testExecute_inputEmptyArguments_throwsNoSuchElementException() {
        DietStorage dietStorage = new DietStorage();
        assertThrows(NoSuchElementException.class, () -> {
            new DietSessionClear().execute(EMPTY_STRING, dietStorage);
        });
    }

    @Test
    void testExecute_inputNotYes_success() {
        DietStorage dietStorage = new DietStorage();
        System.setIn(new ByteArrayInputStream("not Yes".getBytes()));
        CommandResult commandResult = new DietSessionClear().execute(EMPTY_STRING, dietStorage);
        assertEquals(CLEAR_ABORTED, commandResult.getFeedbackMessage());
    }
}
