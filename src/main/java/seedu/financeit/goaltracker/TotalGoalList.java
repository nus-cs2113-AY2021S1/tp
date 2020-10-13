package seedu.financeit.goaltracker;

import java.util.ArrayList;
import seedu.financeit.common.Goal;

public class TotalGoalList {
    private ArrayList<Goal> goal = new ArrayList<>();

    public void addGoal(Goal newGoal) {
        this.goal.add(newGoal);
    }

    public ArrayList<Goal> getGoal() {
        return this.goal;
    }

    public int getListSize() {
        return this.goal.size();
    }

}
