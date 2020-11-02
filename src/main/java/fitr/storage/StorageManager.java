package fitr.storage;

import fitr.exercise.Exercise;
import fitr.food.Food;
import fitr.goal.Goal;
import fitr.exception.InvalidFileFormatException;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.list.GoalList;
import fitr.ui.Ui;
import fitr.user.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class StorageManager {
    protected static final String COMMA_SEPARATOR = ",";
    private final ExerciseStorage exerciseStorage;
    private final FoodStorage foodStorage;
    private final GoalStorage goalStorage;
    private final TipStorage tipStorage;
    private final UserStorage userStorage;

    public StorageManager() throws IOException {
        exerciseStorage = new ExerciseStorage();
        foodStorage = new FoodStorage();
        goalStorage = new GoalStorage();
        tipStorage = new TipStorage();
        userStorage = new UserStorage();
    }

    public ArrayList<Exercise> loadExerciseList() throws FileNotFoundException {
        try {
            return exerciseStorage.loadExerciseList();
        } catch (InvalidFileFormatException e) {
            Ui.printCustomError("Error: Invalid exercise file - new exercise list created!");
            return new ArrayList<>();
        }
    }

    public void writeExerciseList(ExerciseList exerciseList) throws IOException {
        exerciseStorage.writeExerciseList(exerciseList);
    }

    public ArrayList<Food> loadFoodList() throws FileNotFoundException {
        try {
            return foodStorage.loadFoodList();
        } catch (InvalidFileFormatException e) {
            Ui.printCustomError("Error: Invalid food file - new food list created!");
            return new ArrayList<>();
        }
    }

    public void writeFoodList(FoodList foodList) throws IOException {
        foodStorage.writeFoodList(foodList);
    }

    public User loadUserProfile() throws FileNotFoundException {
        try {
            return userStorage.loadUserProfile();
        } catch (InvalidFileFormatException | NumberFormatException e) {
            Ui.printCustomError("Error: Invalid user file - creating a new user!");
            return new User();
        }
    }

    public void writeUserProfile(User user) throws IOException {
        userStorage.writeUserProfile(user);
    }

    public ArrayList<Goal> loadGoalList() throws FileNotFoundException {
        try {
            return goalStorage.loadGoalList();
        } catch (InvalidFileFormatException | ArrayIndexOutOfBoundsException e) {
            Ui.printCustomError("Error: Invalid goal file - new goal list created!");
            return new ArrayList<>();
        }
    }

    public void writeGoalList(GoalList goalList, FoodList foodList, ExerciseList exerciseList,
                              User user) throws IOException {
        goalStorage.writeGoalList(goalList, foodList, exerciseList, user);
    }

    public ArrayList<String> loadTipList() throws IOException {
        return tipStorage.loadTipList();
    }
}
