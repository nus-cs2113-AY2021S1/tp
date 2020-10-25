package seedu.financeit.datatrackers.goaltracker;

import java.util.ArrayList;
import seedu.financeit.data.Goal;

/**
 * This class is the arraylist of the class Goal.
 * @class Goal
 */
public class TotalGoalList {
    private ArrayList<Goal> goals = new ArrayList<>();

    /**
     * This will add new goal to the arraylist.
     * @param newGoal new Goal contains (amt,cat,month)
     */
    public void addGoal(Goal newGoal) {
        this.goals.add(newGoal);
    }

    /**
     * This will return the whole arraylist.
     * @return
     */
    public ArrayList<Goal> getGoal() {
        return this.goals;
    }

    /**
     * This is to get the total size of the arraylist.
     * @return
     */
    public int getListSize() {
        return this.goals.size();
    }

}
