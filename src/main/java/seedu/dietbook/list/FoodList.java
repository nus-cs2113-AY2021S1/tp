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
     * Mainly for adding food directly from the data base of foods.
     * @param portionSize integer to designate number of servings
     * @param food food object to be added
     * @return string representation of the entry added
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
     * Add add method for backlogged entries.
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
     * Truncated add method for the purpose of save-loading (allows adding food object directly).
     * Can also be used to add backlogged entry via database.
     * @param portionSize integer to designate number of servings
     * @param food Food object to be added (from the save-load/database)
     * @param dateTime Save-loaded date-time or user specified time for backlogged entry.
     * @return string representation of entry added.
     */
    public String addFoodAtDateTime(int portionSize, Food food, LocalDateTime dateTime) {
        FoodEntry entry = new DatedFoodEntry(portionSize, food, dateTime);
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
        return FoodListManager.convertListToFoods(foodEntries);
    }

    /**
     * Obtain list of food objects in FoodList, scaled to portion size.
     */
    public List<Food> getPortionedFoods() {
        return FoodListManager.convertListToPortionedFoods(foodEntries);
    }

    /**
     * Obtain list of foods consumed after specified timing.
     */
    public List<Food> getFoodsAfterDateTime(LocalDateTime dateTime) {
        List<FoodEntry> entriesAfterDateTime = FoodListManager.filterListByDate(foodEntries, dateTime);
        return FoodListManager.convertListToFoods(entriesAfterDateTime);
    }

    /**
     * Obtain list of foods consumed after specified timing, scaled to portion size.
     */
    public List<Food> getPortionedFoodsAfterDateTime(LocalDateTime dateTime) {
        List<FoodEntry> entriesAfterDateTime = FoodListManager.filterListByDate(foodEntries, dateTime);
        return FoodListManager.convertListToFoods(entriesAfterDateTime);
    }

    /**
     * Obtain list of foods consumed within the range of a specified timing.
     */
    public List<Food> getFoodsInDateTimeRange(LocalDateTime start, LocalDateTime end) {
        List<FoodEntry> entriesInRange = FoodListManager.filterListByDate(foodEntries, start, end);
        return FoodListManager.convertListToFoods(entriesInRange);
    }

    /**
     * Obtain list of foods consumed within the range of a specified timing, scaled to portion size.
     */
    public List<Food> getPortionedFoodsInDateTimeRange(LocalDateTime start, LocalDateTime end) {
        List<FoodEntry> entriesInRange = FoodListManager.filterListByDate(foodEntries, start, end);
        return FoodListManager.convertListToPortionedFoods(entriesInRange);
    }

    /**
     * Obtain list of portion sizes.
     * (For storage purposes)
     */
    public List<Integer> getPortionSizes() {
        return FoodListManager.convertListToPortionSizes(foodEntries);
    }


    /**
     * Obtain list of LocalDateTimes for when the entries were made.
     * (For storage purposes)
     */
    public List<LocalDateTime> getDateTimes() {
        return FoodListManager.convertListToLocalDateTimes(foodEntries);
    }

    @Override
    public String toString() {
        return FoodListManager.convertListToString(foodEntries);
    }

    /**
     * Returns toString representation of the segmented list based on DateTime.
     * @param dateTime Start DateTime.
     * @return string representation of FoodList
     */
    public String getAfterDateTimeToString(LocalDateTime dateTime) {
        List<FoodEntry> entriesAfterDateTime = FoodListManager.filterListByDate(foodEntries, dateTime);
        return FoodListManager.convertListToString(entriesAfterDateTime);
    }

    /**
     * Returns toString representation of the segmented list based on DateTime (within a range of 2 datetimes).
     * @param start lower bound of datetime (inclusive)
     * @param end   upper bound of datetime (inclusive)
     * @return  string representation of FoodList
     */
    public String getInDateTimeRangeToString(LocalDateTime start, LocalDateTime end) {
        List<FoodEntry> entriesInRange = FoodListManager.filterListByDate(foodEntries, start, end);
        return FoodListManager.convertListToString(entriesInRange);
    }

    /**
     * Alternative toString method that also displays all the associated dates with each food entry.
     */
    public String toDatedString() {
        return FoodListManager.convertListToDatedString(foodEntries);
    }

    /**
     * Alternative toString method that provides associated dates with each food entry.
     * Only returns entries within the bounds of a start and end date.
     * @param start lower bound of datetime (inclusive)
     * @param end upper bound of datetime (inclusive)
     * @return string representation of FoodList with datetimes.
     */
    public String toDatedString(LocalDateTime start, LocalDateTime end) {
        List<FoodEntry> entriesInRange = FoodListManager.filterListByDate(foodEntries, start, end);
        return FoodListManager.convertListToDatedString(entriesInRange);
    }

    /**
     * Alternative toString method that provides associated dates with each food entry.
     * Only returns entries within the bounds of the start datetime and MAX.
     * @param start lower bound of datetime (inclusive).
     * @return string representation of FoodList with datetimes.
     */
    public String toDatedString(LocalDateTime start) {
        List<FoodEntry> entriesInRange = FoodListManager.filterListByDate(foodEntries, start);
        return FoodListManager.convertListToDatedString(entriesInRange);
    }

}
