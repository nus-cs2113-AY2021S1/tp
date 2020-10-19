package fitr.storage;

import fitr.Calorie;
import fitr.Exercise;
import fitr.Food;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {
    @Test
    public void load_invalidExerciseData_exceptionThrown() throws Exception {
        Storage storage = getInvalidStorage();
        assertThrows(ArrayIndexOutOfBoundsException.class, storage::loadExerciseList);
    }

    @Test
    public void load_invalidFoodData_exceptionThrown() throws Exception {
        Storage storage = getInvalidStorage();
        assertThrows(ArrayIndexOutOfBoundsException.class, storage::loadFoodList);
    }

    @Test
    public void load_validExerciseData_success() throws Exception {
        Storage storage = getValidStorage();
        ArrayList<Exercise> actualExerciseList = storage.loadExerciseList();
        ArrayList<Exercise> expectedExerciseList = getValidExerciseList();

        assertEquals(expectedExerciseList.get(0).getNameOfExercise(), actualExerciseList.get(0).getNameOfExercise());
        assertEquals(expectedExerciseList.get(0).getCalories(), actualExerciseList.get(0).getCalories());
        assertEquals(expectedExerciseList.get(0).getDuration(), actualExerciseList.get(0).getDuration());

        assertEquals(expectedExerciseList.get(1).getNameOfExercise(), actualExerciseList.get(1).getNameOfExercise());
        assertEquals(expectedExerciseList.get(1).getCalories(), actualExerciseList.get(1).getCalories());
        assertEquals(expectedExerciseList.get(1).getDuration(), actualExerciseList.get(1).getDuration());
    }

    @Test
    public void load_validFoodData_success() throws Exception {
        Storage storage = getValidStorage();
        ArrayList<Food> actualFoodList = storage.loadFoodList();
        ArrayList<Food> expectedFoodList = getValidFoodList();

        assertEquals(expectedFoodList.get(0).getFoodName(), actualFoodList.get(0).getFoodName());
        assertEquals(expectedFoodList.get(0).getCalories(), actualFoodList.get(0).getCalories());
        assertEquals(expectedFoodList.get(0).getAmountOfFood(), actualFoodList.get(0).getAmountOfFood());

        assertEquals(expectedFoodList.get(1).getFoodName(), actualFoodList.get(1).getFoodName());
        assertEquals(expectedFoodList.get(1).getCalories(), actualFoodList.get(1).getCalories());
        assertEquals(expectedFoodList.get(1).getAmountOfFood(), actualFoodList.get(1).getAmountOfFood());
    }

    private ArrayList<Exercise> getValidExerciseList() {
        ArrayList<Exercise> exerciseList = new ArrayList<>();
        exerciseList.add(new Exercise("Test Exercise 1", new Calorie(500), 10));
        exerciseList.add(new Exercise("Test Exercise 2", new Calorie(100), 5));
        return exerciseList;
    }

    private ArrayList<Food> getValidFoodList() {
        ArrayList<Food> foodList = new ArrayList<>();
        foodList.add(new Food("Test Food 1", new Calorie(100), 1));
        foodList.add(new Food("Test Food 2", new Calorie(50), 5));
        return foodList;
    }

    private Storage getValidStorage() throws Exception {
        return new Storage("src/test/data/StorageTest/ValidUserData.txt",
                "src/test/data/StorageTest/ValidFoodData.txt",
                "src/test/data/StorageTest/ValidExerciseData.txt");
    }

    private Storage getInvalidStorage() throws Exception {
        return new Storage("src/test/data/StorageTest/InvalidUserData.txt",
                "src/test/data/StorageTest/InvalidFoodData.txt",
                "src/test/data/StorageTest/InvalidExerciseData.txt");
    }
}
