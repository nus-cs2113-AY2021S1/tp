package seedu.duke;

import seedu.duke.list.FoodList;
import seedu.duke.food.Food;
import org.junit.jupiter.api.Test;


class FoodListTest {
    public static void main(String[] args) {
        Food food = new Food("Kobe Beef", 480,50,40,30);
        FoodList foodList = new FoodList();
        
        System.out.println(foodList.addFood(3, food));
        System.out.println(foodList.addFood(2, "Sashimi", 100, 0, 30, 10));
        System.out.println(foodList);
        System.out.println(foodList.delete(1));
        System.out.println(foodList);
    }
}