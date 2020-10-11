package seedu.duke.list;

import java.util.ArrayList;
import seedu.duke.food.Food;


/**
 * Wrapper class for the implementation of foodlist as an arraylist of foodEntry objects.
 * Foodlist does not return its ArrayList nor foodEntry objects.
 * This is a stateful object.
 */
public class FoodList {
    private ArrayList<FoodEntry> foodEntries;

    public FoodList() {
        this.foodEntries = new ArrayList<>();
    }

    protected FoodList(ArrayList<FoodEntry> entries) {
        this.foodEntries = entries;
    }

    /**
     * Adds food of portion size directly into the foodlist as an entry.
     * When date functionality is added, this method will need to be overhauled.
     * The adding feature will be largely pushed to FoodListManager (to figure out dates)
     * @param portionSize integer to designate number of servings
     * @param food food object to be added
     * @return string representation of the food object added
     */
    public String addFood(int portionSize, Food food) {
        FoodEntry toAdd = new FoodEntry(portionSize, food);
        foodEntries.add(toAdd);
        return toAdd.toString();
    }

    public String addFood(int portionSize, String name, int calorie, 
            int carbohydrate, int protein, int fat) {
        FoodEntry toAdd = new FoodEntry(portionSize, name, calorie, carbohydrate, protein, fat);
        foodEntries.add(toAdd);
        return toAdd.toString();
    }

    /**
     * Food database search functionality support.
     * Currently just throws a not found exception when called in this manner.
     * @param portionSize integer to designate number of servings
     * @param name food object to be added
     * @return string representation of the food object added
     * @throws FoodNotFoundException custom exception to indicate search for food in database failed.
     */
    public String addFood(int portionSize, String name) throws FoodNotFoundException {
        throw new FoodNotFoundException();
    }


    public String delete(int index) throws IndexOutOfBoundsException {
        try{
            return FoodListManager.deleteEntry(foodEntries, index).toString();
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
    }

    public boolean clear() {
        this.foodEntries = new ArrayList<>();
        return true;
    }

    public ArrayList<Food> getFoods() {
        return FoodListManager.listToFoods(foodEntries);
    }

    @Override
    public String toString() {
        return FoodListManager.listToString(foodEntries);
    }

}
