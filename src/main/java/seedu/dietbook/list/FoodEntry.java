package seedu.dietbook.list;

import seedu.dietbook.food.Food;


/**
 * Data class to store both serving sizes and a food object as a single object.
 */
public class FoodEntry {
    private final int portionSize;
    private final Food food;

    /**
     * Convenience constructor mainly for testing.
     * In the future, this is expected be the constructor for adding entries using food from the database.
     */
    public FoodEntry(int portionSize, Food food) {
        assert (portionSize > 0) : "Non-positive, invalid portion size not caught.";
        this.portionSize = portionSize;
        this.food = food;
    }

    /**
     * Default constructor. Creates new food object as part of entry.
     */
    public FoodEntry(int portionSize, String name, int calorie, 
            int carbohydrate, int protein, int fat) {
        assert (portionSize > 0) : "Non-positive, invalid portion size not caught.";
        this.portionSize = portionSize;
        this.food = new Food(name, calorie, carbohydrate, protein, fat);
    }

    /**
     * Getter method for the Food object.
     */
    public Food getFood() {
        return food;
    }

    /**
     * Getter method for the portionSize object.
     */
    public int getPortionSize() {
        return portionSize;
    }

    @Override
    public String toString() {
        return String.format("%s -- (%s)", food.toString(), portionSize);
    }

}
