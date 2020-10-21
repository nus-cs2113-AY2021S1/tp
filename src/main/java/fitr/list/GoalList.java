package fitr.list;

import fitr.Goal;

import java.util.ArrayList;

public class GoalList {
    private final ArrayList<Goal> goalList;

    public GoalList() {
        this(new ArrayList<>());
    }

    public GoalList(ArrayList<Goal> goalList) {
        this.goalList = goalList;
    }

    public void addGoal(Goal goal) {
        goalList.add(goal);
    }

    public Goal deleteGoal(int index) {
        return goalList.remove(index);
    }

    public Goal getGoal(int index) {
        return goalList.get(index);
    }

    public int getSize() {
        return goalList.size();
    }

    public void clearList() {
        goalList.clear();
    }
}
