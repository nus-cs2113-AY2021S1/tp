package seedu.dietbook.list;

import seedu.dietbook.food.Food;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Wrapper class for the implementation of foodlist as an arraylist of foodEntry objects.
 * Foodlist does not return its ArrayList nor foodEntry objects.
 * This is a stateful object.
 */
public class FoodList {
    private ArrayList<FoodEntry> foodEntries;

    /**
     * Default constructor that instantiates FoodList with an empty foodentry arraylist.
     */
    public FoodList() {
        this.foodEntries = new ArrayList<>();
    }

    /**
     * Convenience constructor for testing purposes.
     */
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
        FoodEntry entry = new DatedFoodEntry(portionSize, food);
        foodEntries.add(entry);
        return entry.toString();
    }

    /**
     * Default add method that adds a food entry using the food details and portion size.
     */
    public String addFood(int portionSize, String name, int calorie, 
            int carbohydrate, int protein, int fat) {
        FoodEntry entry = new DatedFoodEntry(portionSize, name, calorie, carbohydrate, protein, fat);
        foodEntries.add(entry);
        return entry.toString();
    }


    /**
     * Food database search functionality support.
     * Not expected to function. Added for completeness.
     * Currently just throws a not found exception when called in this manner.
     * @param portionSize integer to designate number of servings
     * @param name food object to be added
     * @return string representation of the food object added
     * @throws FoodNotFoundException custom exception to indicate search for food in database failed.
     */
    public String addFood(int portionSize, String name) throws FoodNotFoundException {
        throw new FoodNotFoundException();
    }


    /**
     * Add add method for baglogged entries.
     * Allows specificiation of time via LocalDateTime param.
     * @param dateTime User specified time for backlogged entry.
     */
    public String addFoodAtDateTime(int portionSize, String name, int calorie, 
            int carbohydrate, int protein, int fat, LocalDateTime dateTime) {
        
        FoodEntry entry = new DatedFoodEntry(portionSize, name, calorie, carbohydrate, protein, fat, dateTime);
        foodEntries.add(entry);
        return entry.toString();
    }

    /**
     * Deletes the the entry of the list at the provided index.
     * index starts from 1 (not 0). i.e. is User's understanding of index.
     */
    public String delete(int index) throws IndexOutOfBoundsException {
        try {
            return FoodListManager.deleteEntry(foodEntries, index).toString();
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
    }
    
    /**
     * Discards previous foodEntry list and creates a new one.
     */
    public boolean clear() {
        this.foodEntries = new ArrayList<>();
        return true;
    }


    /**
     * Obtain the food objects in Foodlist as an ArrayList.
     * For other classes that wish to operate on the Food items directly.
     * @return Arraylist of ordered Food objects in Foodlist's foodEntries.
     */
    public List<Food> getFoods() {
        return FoodListManager.listToFoods(foodEntries);
    }

    /**
     * Obtain list of food objects in FoodList, scaled to portion size.
     */
    public List<Food> getPortionedFoods() {
        return FoodListManager.listToPortionedFoods(foodEntries);
    }

    /**
     * Obtain list of foods consumed after specified timing.
     */
    public List<Food> getFoodsAfterDateTime(LocalDateTime dateTime) {
        List<FoodEntry> entriesAfterDateTime = FoodListManager.filterListByDate(foodEntries, dateTime);
        return FoodListManager.listToFoods(entriesAfterDateTime);
    }

    /**
     * Obtain list of foods consumed after specified timing, scaled to portion size.
     */
    public List<Food> getPortionedFoodsAfterDateTime(LocalDateTime dateTime) {
        List<FoodEntry> entriesAfterDateTime = FoodListManager.filterListByDate(foodEntries, dateTime);
        return FoodListManager.listToFoods(entriesAfterDateTime);
    }

    /**
     * Obtain list of foods consumed within the range of a specified timing.
     */
    public List<Food> getFoodsInDateTimeRange(LocalDateTime start, LocalDateTime end) {
        List<FoodEntry> entriesInRange = FoodListManager.filterListByDate(foodEntries, start, end);
        return FoodListManager.listToFoods(entriesInRange);
    }

    /**
     * Obtain list of foods consumed within the range of a specified timing, scaled to portion size.
     */
    public List<Food> getPortionedFoodsInDateTimeRange(LocalDateTime start, LocalDateTime end) {
        List<FoodEntry> entriesInRange = FoodListManager.filterListByDate(foodEntries, start, end);
        return FoodListManager.listToPortionedFoods(entriesInRange);
    }

    /**
     * Obtain list of portion sizes.
     * (For storage purposes)
     */
    public List<Integer> getPortionSizes() {
        return FoodListManager.listToPortionSizes(foodEntries);
    }

    /**
     * Obtain list of LocalDateTimes for when the entries were made.
     * (For storage purposes)
     */
    public List<LocalDateTime> getDateTimes() {
        return FoodListManager.listToLocalDateTimes(foodEntries);
    }

    @Override
    public String toString() {
        return FoodListManager.listToString(foodEntries);
    }

}
