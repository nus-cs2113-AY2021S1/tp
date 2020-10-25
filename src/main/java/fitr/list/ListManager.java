package fitr.list;

import fitr.Exercise;
import fitr.Food;
import fitr.Goal;
import fitr.storage.StorageManager;

import java.io.IOException;

public class ListManager {
    private final ExerciseList exerciseList;
    private final FoodList foodList;
    private final GoalList goalList;

    public ListManager(StorageManager storageManager) throws IOException {
        exerciseList = new ExerciseList(storageManager.loadExerciseList());
        foodList = new FoodList(storageManager.loadFoodList());
        goalList = new GoalList(storageManager.loadGoalList());
    }

    public void addExercise(Exercise exercise) {
        exerciseList.addExercise(exercise);
    }

    public Exercise getExercise(int index) {
        return exerciseList.getExercise(index);
    }

    public void deleteExercise(int index) {
        exerciseList.deleteExercise(index);
    }

    public ExerciseList getExerciseList() {
        return exerciseList;
    }

    public void clearExerciseList() {
        exerciseList.clearList();
    }

    public void addFood(Food food) {
        foodList.addFood(food);
    }

    public Food getFood(int index) {
        return foodList.getFood(index);
    }

    public void deleteFood(int index) {
        foodList.deleteFood(index);
    }

    public FoodList getFoodList() {
        return foodList;
    }

    public void clearFoodList() {
        foodList.clearList();
    }

    public void addGoal(Goal goal) {
        goalList.addGoal(goal);
    }

    public Goal getGoal(int index) {
        return goalList.getGoal(index);
    }

    public void deleteGoal(int index) {
        goalList.deleteGoal(index);
    }

    public GoalList getGoalList() {
        return goalList;
    }

    public void clearGoalList() {
        goalList.clearList();
    }
}
