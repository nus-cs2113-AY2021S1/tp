package seedu.dietbook.calculator;

import seedu.dietbook.food.Food;
import seedu.dietbook.list.FoodList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CalculatorData {
    FoodList list;
    List<Food> foods = null;

    public CalculatorData() {
        this.list = new FoodList();
    }

    public CalculatorData(FoodList foodList) {
        this.list = foodList;
    }

    public void inputData(FoodList foodList) {
        this.list = foodList;
    }

    public List<Integer> getTotalCalorie() {
        this.foods = list.getFoods();
        List<Integer> calories = new ArrayList<>();
        for (Food food : foods) {
            calories.add(food.getCalorie());
        }
        return calories;
    }

    public List<Integer> getTotalCalorie(LocalDateTime startTime) {
        this.foods = list.getFoodsAfterDateTime(startTime);
        List<Integer> calories = new ArrayList<>();
        for (Food food : foods) {
            calories.add(food.getCalorie());
        }
        return calories;
    }

    public List<Integer> getTotalCalorie(LocalDateTime startTime, LocalDateTime endTime) {
        this.foods = list.getFoodsInDateTimeRange(startTime, endTime);
        List<Integer> calories = new ArrayList<>();
        for (Food food : foods) {
            calories.add(food.getCalorie());
        }
        return calories;
    }

    public List<Integer> getTotalCarb() {
        this.foods = list.getFoods();
        List<Integer> carbs = new ArrayList<>();
        for (Food food : foods) {
            carbs.add(food.getCarbohydrate());
        }
        return carbs;
    }

    public List<Integer> getTotalCarb(LocalDateTime startTime) {
        this.foods = list.getFoodsAfterDateTime(startTime);
        List<Integer> carbs = new ArrayList<>();
        for (Food food : foods) {
            carbs.add(food.getCalorie());
        }
        return carbs;
    }

    public List<Integer> getTotalCarb(LocalDateTime startTime, LocalDateTime endTime) {
        this.foods = list.getFoodsInDateTimeRange(startTime, endTime);
        List<Integer> carbs = new ArrayList<>();
        for (Food food : foods) {
            carbs.add(food.getCarbohydrate());
        }
        return carbs;
    }

    public List<Integer> getTotalProtein() {
        this.foods = list.getFoods();
        List<Integer> proteins = new ArrayList<>();
        for (Food food : foods) {
            proteins.add(food.getProtein());
        }
        return proteins;
    }

    public List<Integer> getTotalProtein(LocalDateTime startTime) {
        this.foods = list.getFoodsAfterDateTime(startTime);
        List<Integer> proteins = new ArrayList<>();
        for (Food food : foods) {
            proteins.add(food.getProtein());
        }
        return proteins;
    }

    public List<Integer> getTotalProtein(LocalDateTime startTime, LocalDateTime endTime) {
        this.foods = list.getFoodsInDateTimeRange(startTime, endTime);
        List<Integer> proteins = new ArrayList<>();
        for (Food food : foods) {
            proteins.add(food.getProtein());
        }
        return proteins;
    }

    public List<Integer> getTotalFat() {
        this.foods = list.getFoods();
        List<Integer> fats = new ArrayList<>();
        for (Food food : foods) {
            fats.add(food.getFat());
        }
        return fats;
    }

    public List<Integer> getTotalFat(LocalDateTime startTime) {
        this.foods = list.getFoodsAfterDateTime(startTime);
        List<Integer> fats = new ArrayList<>();
        for (Food food : foods) {
            fats.add(food.getFat());
        }
        return fats;
    }

    public List<Integer> getTotalFat(LocalDateTime startTime, LocalDateTime endTime) {
        this.foods = list.getFoodsInDateTimeRange(startTime, endTime);
        List<Integer> fats = new ArrayList<>();
        for (Food food : foods) {
            fats.add(food.getFat());
        }
        return fats;
    }
}
