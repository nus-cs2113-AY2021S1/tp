package fitr.command;

import fitr.Calorie;
import fitr.Exercise;
import fitr.Food;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.storage.Storage;
import fitr.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClearCommandTest {
    @Test
    public void clear_clearBothLists_success() throws Exception {
        ClearCommand clearCommand = new ClearCommand("clear");
        FoodList foodList = getFoodList();
        ExerciseList exerciseList = getExerciseList();
        User user = new User();
        clearCommand.execute(foodList, exerciseList, getStorage(), user);
        assertEquals(0, foodList.getSize());
        assertEquals(0, exerciseList.getSize());
    }

    @Test
    public void clear_clearFoodList_success() throws Exception {
        ClearCommand clearCommand = new ClearCommand("clear food");
        FoodList foodList = getFoodList();
        ExerciseList exerciseList = getExerciseList();
        User user = new User();
        clearCommand.execute(foodList, exerciseList, getStorage(), user);
        assertEquals(0, foodList.getSize());
    }

    @Test
    public void clear_clearExerciseList_success() throws Exception {
        ClearCommand clearCommand = new ClearCommand("clear exercise");
        FoodList foodList = getFoodList();
        ExerciseList exerciseList = getExerciseList();
        User user = new User();
        clearCommand.execute(foodList, exerciseList, getStorage(), user);
        assertEquals(0, exerciseList.getSize());
    }

    private FoodList getFoodList() {
        FoodList foodList = new FoodList();
        foodList.addFood(new Food("Rice", new Calorie(100), 1));
        foodList.addFood(new Food("Noodles", new Calorie(200), 1));
        return foodList;
    }

    private ExerciseList getExerciseList() {
        ExerciseList exerciseList = new ExerciseList();
        exerciseList.addExercise(new Exercise("Push ups", new Calorie(100), 5));
        exerciseList.addExercise(new Exercise("Run", new Calorie(300), 10));
        return exerciseList;
    }

    private Storage getStorage() throws Exception {
        return new Storage("src/test/data/StorageTest/ValidUserData.txt",
                "src/test/data/StorageTest/ValidFoodData.txt",
                "src/test/data/StorageTest/ValidExerciseData.txt");
    }
}
