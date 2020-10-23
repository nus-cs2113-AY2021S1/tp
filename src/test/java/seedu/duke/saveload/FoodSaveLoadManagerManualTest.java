package seedu.duke.saveload;

import seedu.duke.food.Food;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class FoodSaveLoadManagerManualTest {
    static private FoodSaveLoadManager testManager;
    static private List<Food> inputFoodList;
    static private Food food1;
    static private Food food2;
    static private Food food3;
    static private Food food4;

    static private List<Food> testFoodList;

    public static void main(String[] args) throws FileNotFoundException, IllegalAccessException {
        testManager = new FoodSaveLoadManager();
        inputFoodList = new ArrayList<>();
        food1 = new Food("Apple", 20000,20,5,1);
        food2 = new Food("Peach", 3000,50,2,3);
        food3 = new Food("Bacon", 1000,20,10,99);
        food4 = new Food("Silicon", 500,100,50,10);

        inputFoodList.add(food1);
        inputFoodList.add(food2);
        inputFoodList.add(food3);
        inputFoodList.add(food4);

        testManager.save("Victor's Food List", inputFoodList);
        testManager.load("Victor's Food List");

        testFoodList = testManager.getFoodList();

        System.out.println(testFoodList.get(0).getName());
        System.out.println(testFoodList.get(0).getCalorie());
        System.out.println(testFoodList.get(3).getName());
        System.out.println(testFoodList.get(3).getCalorie());
    }
}
