package fitr.list;

import fitr.goal.Goal;
import fitr.user.User;

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

    public ArrayList<Goal> getGoalList() {
        return goalList;
    }

    public GoalList reformatGoalList(GoalList goalList, FoodList foodList, ExerciseList exerciseList, User user) {
        GoalList completedGoalList = new GoalList();
        GoalList incompletedGoalList = new GoalList();

        for (int i = 0; i < goalList.getSize(); i++) {
            if (goalList.getGoal(i).getStatus(goalList.getGoal(i), foodList, exerciseList, user).equals("✓")) {
                completedGoalList.addGoal(goalList.getGoal(i));
            } else {
                incompletedGoalList.addGoal(goalList.getGoal(i));
            }
        }

        goalList.clearList();

        for (int i = 0; i < incompletedGoalList.getSize(); i++) {
            goalList.addGoal(incompletedGoalList.getGoal(i));
        }

        for (int i = 0; i < completedGoalList.getSize(); i++) {
            goalList.addGoal(completedGoalList.getGoal(i));
        }

        return goalList;
    }
}
