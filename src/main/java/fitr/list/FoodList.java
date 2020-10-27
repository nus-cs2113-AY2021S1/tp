package fitr.list;

import fitr.Exercise;
import fitr.Food;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

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

    public int getSize() {
        return foodList.size();
    }

    public void clearList() {
        foodList.clear();
    }

    public ArrayList<Food> filterByDate(String date) {
        return (ArrayList<Food>) foodList.stream()
                .filter((s) -> s.getDate().contentEquals(date))
                .collect(toList());
    }
}
