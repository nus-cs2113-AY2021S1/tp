package seedu.duke;

import java.util.ArrayList;

public class FoodList {
    private final ArrayList<Food> foodList;

    public FoodList() {
        this(new ArrayList<>());
    }

    public FoodList(ArrayList<Food> foodList) {
        this.foodList = foodList;
    }

    public void addFood(Food food) {
        foodList.add(food);
    }

    public Food getFood(int index) {
        return foodList.get(index);
    }

    public Food deleteFood(int index) {
        return foodList.remove(index);
    }
}
