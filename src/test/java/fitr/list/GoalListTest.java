package fitr.list;

import fitr.goal.Goal;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static fitr.common.DateManager.getCurrentDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GoalListTest {

    @Test
    public void addGoalToEmptyList_validGoal_success() {
        GoalList goalList = new GoalList();
        goalList.addGoal(new Goal(getCurrentDate(), "exercise", "run 5km"));
        assertEquals(1, goalList.getSize());
    }

    @Test
    public void addGoalToNonEmptyList_validGoal_success() {
        GoalList goalList = new GoalList(getTestGoalList());
        goalList.addGoal(new Goal(getCurrentDate(), "exercise", "run 5km"));
        assertEquals(3, goalList.getSize());
    }

    private ArrayList<Goal> getTestGoalList() {
        ArrayList<Goal> goalList = new ArrayList<>();
        goalList.add(new Goal(getCurrentDate(), "food", "<2000"));
        goalList.add(new Goal(getCurrentDate(), "exercise", ">3800"));
        return goalList;
    }
}