package fitr.goal;

import org.junit.jupiter.api.Test;

import static fitr.common.DateManager.getCurrentDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GoalTest {

    @Test
    public void getDescription_validGoal_success() {
        Goal tempGoal = new Goal(getCurrentDate(), "exercise", "run more");
        assertEquals("run more", tempGoal.getDescription());
    }

    @Test
    public void getGoalType_validGoal_success() {
        Goal tempGoal = new Goal(getCurrentDate(), "food", "Y", "eat clean");
        assertEquals("food", tempGoal.getGoalType());
    }
}