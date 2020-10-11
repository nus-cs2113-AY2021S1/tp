package seedu.duke.list;
import seedu.duke.food.Food;


/**
 * Data class to story both serving sizes and a food object as a single object
 */
public class FoodEntry {
    private int portionSize;
    private Food food;

    public FoodEntry(int portionSize, Food food) {
        this.portionSize = portionSize;
        this.food = food;
    }

    public FoodEntry(int portionSize, String name, int calorie, 
            int carbohydrate, int protein, int fat) {
        this.portionSize = portionSize;
        this.food = new Food (name, calorie, carbohydrate, protein, fat);
    }

    public Food getFood() {
        return food;
    }

    public int portionSize() {
        return portionSize;
    }

    @Override
    public String toString() {
        return String.format("%s -- (%s)", food.toString(), portionSize);
    }

}