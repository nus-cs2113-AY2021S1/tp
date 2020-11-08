package fitr.goal;

import fitr.exception.FitrException;
import fitr.exception.UpperBoundLessThanException;
import fitr.exception.UpperBoundMoreThanException;
import org.junit.jupiter.api.Test;
import static fitr.goal.FormatGoal.formatGoal;
import static fitr.common.DateManager.getCurrentDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormatGoalTest {
    @Test
    public void formatGoalTest() throws FitrException, UpperBoundMoreThanException, UpperBoundLessThanException {
        Goal finalGoal = formatGoal(getCurrentDate(), "exercise", "do triceps dips");
        assertEquals("do triceps dips", finalGoal.description);
        assertEquals("exercise", finalGoal.goalType);
    }
}
