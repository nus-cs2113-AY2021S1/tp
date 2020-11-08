package seedu.dietbook.saveload;

import seedu.dietbook.food.Food;
import seedu.dietbook.list.FoodList;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * This class takes care of saving and loading of food, portion and date.
 */
public class FoodPortionDateSaveLoadManager {
    private static final int DEFAULT_SAVER_WIDTH = 11;
    private static final int DEFAULT_SAVER_HEIGHT = 10;

    private static final int FOOD_NAME_INDEX = 1;
    private static final int FOOD_CALORIE_INDEX = 2;
    private static final int FOOD_CARBOHYDRATE_INDEX = 3;
    private static final int FOOD_PROTEIN_INDEX = 4;
    private static final int FOOD_FAT_INDEX = 5;
    private static final int PORTION_SIZE_INDEX = 6;
    private static final int DAY_INDEX = 7;
    private static final int MONTH_INDEX = 8;
    private static final int YEAR_INDEX = 9;
    private static final int HOUR_INDEX = 10;
    private static final int MINUTE_INDEX = 11;

    private static final String DEFAULT_FOOD_NAME = "No Food Name";
    private static final String DEFAULT_FOOD_CALORIE = "0";
    private static final String DEFAULT_FOOD_CARBOHYDRATE = "0";
    private static final String DEFAULT_FOOD_PROTEIN = "0";
    private static final String DEFAULT_FOOD_FAT = "0";
    private static final String DEFAULT_PORTION_SIZE = "1";
    private static final String DEFAULT_DAY = "1";
    private static final String DEFAULT_MONTH = "1";
    private static final String DEFAULT_YEAR = "2000";
    private static final String DEFAULT_MINUTE = "0";
    private static final String DEFAULT_HOUR = "0";

    private static final String FOOD_FOLDER_NAME = "Food##PORTION###dDATE##folder";

    private Saver saver;
    private Loader fileLoader;

    public FoodPortionDateSaveLoadManager() {
        this.saver = new Saver(DEFAULT_SAVER_WIDTH, DEFAULT_SAVER_HEIGHT);
        this.fileLoader = Loader.loadEmpty();
    }

    // ------ saving and loading functions ----------

    /**
     * Call this function to load a food file.
     * @param fileName name of file
     * @throws FileNotFoundException if there is no such save file
     */
    public void load(String fileName) throws FileNotFoundException {
        this.fileLoader = Loader.load(FOOD_FOLDER_NAME, fileName);
    }

    public void clearLoader() {
        this.fileLoader = Loader.loadEmpty();
    }

    public void save(String fileName) {
        this.saver.save(FOOD_FOLDER_NAME, fileName);
    }

    /**
     * Clears the saver and sets the number of entries it can take.
     * call this function at the start of a series of functions to store data.
     *
     * @param numEntry num of entries
     */
    public void readySaver(Integer numEntry) {
        saver.resetSize(DEFAULT_SAVER_WIDTH, numEntry);
    }

    // ------- top layer save loading -------

    /**
     * Constructs the food list from stored data.
     * Note : call load function before calling this function or else it will throw illegalAccessException.
     *
     * @return the completed food list
     */
    public FoodList getFoodList() throws IllegalAccessException {
        FoodList foodlist = new FoodList();
        for (int i = 1; i < fileLoader.getHeight() + 1; i++) {
            foodlist.addFoodAtDateTime(this.getPortionSize(i), this.getFood(i), this.getDateTime(i));
        }
        return foodlist;
    }

    /**
     * Takes in a food list object and saves all of it's contents to the specified file name.
     * The number of entries is equal to the number of items on the food list.
     *
     * @param foodlist food list
     */
    public void saveFoodList(FoodList foodlist, String fileName) {
        List<Food> foods = foodlist.getFoods();
        List<Integer> portions = foodlist.getPortionSizes();
        List<LocalDateTime> datetimes = foodlist.getDateTimes();
        int numEntry = foods.size();
        readySaver(numEntry);
        for (int i = 1; i < numEntry + 1; i++) {
            setFood(foods.get(i - 1), i);
            setDateTime(datetimes.get(i - 1), i);
            setPortionSize(portions.get(i - 1),i);
        }
        save(fileName);
    }

    // ------- setters and getters ----------

    public void setFood(Food food, int entryNumber) {
        setFoodName(food.getName(), entryNumber);
        setFoodCalorie(food.getCalorie(), entryNumber);
        setFoodCarbohydrate(food.getCarbohydrate(), entryNumber);
        setFoodProtein(food.getProtein(), entryNumber);
        setFoodFat(food.getFat(), entryNumber);
    }

    public Food getFood(int entryNumber) throws IllegalAccessException {
        String name = getFoodName(entryNumber);
        int calorie = getFoodCalorie(entryNumber);
        int carbohydrate = getFoodCarbohydrate(entryNumber);
        int protein = getFoodProtein(entryNumber);
        int fat = getFoodFat(entryNumber);
        return new Food(name, calorie, carbohydrate, protein, fat);
    }

    public void setDateTime(LocalDateTime dateTime, int entryNumber) {
        setDay(dateTime.getDayOfMonth(), entryNumber);
        setMonth(dateTime.getMonthValue(), entryNumber);
        setYear(dateTime.getYear(), entryNumber);
        setHour(dateTime.getHour(), entryNumber);
        setMinute(dateTime.getMinute(), entryNumber);
    }

    public LocalDateTime getDateTime(int entryNumber) throws IllegalAccessException {
        int day = Integer.parseInt(fileLoader.get(DAY_INDEX, entryNumber).orElse(DEFAULT_DAY));
        int month = Integer.parseInt(fileLoader.get(MONTH_INDEX, entryNumber).orElse(DEFAULT_MONTH));
        int year = Integer.parseInt(fileLoader.get(YEAR_INDEX, entryNumber).orElse(DEFAULT_YEAR));
        int hour = Integer.parseInt(fileLoader.get(HOUR_INDEX, entryNumber).orElse(DEFAULT_HOUR));
        int minute = Integer.parseInt(fileLoader.get(MINUTE_INDEX, entryNumber).orElse(DEFAULT_MINUTE));
        return LocalDateTime.of(year, month, day, hour, minute);
    }

    // ------ Basic setters and getters -------

    private void setFoodCarbohydrate(int carbohydrate, int entryNumber) {
        saver.add(Integer.toString(carbohydrate),  FOOD_CARBOHYDRATE_INDEX, entryNumber);
    }

    private void setFoodName(String name, int entryNumber) {
        saver.add(name, FOOD_NAME_INDEX, entryNumber);
    }

    private void setFoodCalorie(int calorie, int entryNumber) {
        saver.add(Integer.toString(calorie), FOOD_CALORIE_INDEX, entryNumber);
    }

    private void setFoodProtein(int protein, int entryNumber) {
        saver.add(Integer.toString(protein), FOOD_PROTEIN_INDEX, entryNumber);
    }

    private void setFoodFat(int fat, int entryNumber) {
        saver.add(Integer.toString(fat), FOOD_FAT_INDEX, entryNumber);
    }

    public void setPortionSize(int portionSize, Integer entryNumber) {
        saver.add(Integer.toString(portionSize), PORTION_SIZE_INDEX, entryNumber);
    }

    public void setDay(int day, int entryNumber) {
        saver.add(Integer.toString(day), DAY_INDEX, entryNumber);
    }

    public void setMonth(int month, int entryNumber) {
        saver.add(Integer.toString(month), MONTH_INDEX, entryNumber);
    }

    public void setYear(int year, int entryNumber) {
        saver.add(Integer.toString(year), YEAR_INDEX, entryNumber);
    }

    public void setHour(int hour, int entryNumber) {
        saver.add(Integer.toString(hour), HOUR_INDEX, entryNumber);
    }

    public void setMinute(int minute, int entryNumber) {
        saver.add(Integer.toString(minute), MINUTE_INDEX, entryNumber);
    }

    private String getFoodName(Integer entryNumber) throws IllegalAccessException {
        return fileLoader.get(FOOD_NAME_INDEX, entryNumber).orElse(DEFAULT_FOOD_NAME);
    }

    private int getFoodFat(Integer entryNumber) throws IllegalAccessException {
        return Integer.parseInt(fileLoader.get(FOOD_FAT_INDEX, entryNumber).orElse(DEFAULT_FOOD_FAT));
    }

    private int getFoodProtein(Integer entryNumber) throws IllegalAccessException {
        return Integer.parseInt(fileLoader.get(FOOD_PROTEIN_INDEX, entryNumber).orElse(DEFAULT_FOOD_PROTEIN));
    }

    private int getFoodCarbohydrate(Integer entryNumber) throws IllegalAccessException {
        return Integer.parseInt(fileLoader.get(FOOD_CARBOHYDRATE_INDEX, entryNumber).orElse(DEFAULT_FOOD_CARBOHYDRATE));
    }

    private int getFoodCalorie(Integer entryNumber) throws IllegalAccessException {
        return Integer.parseInt(fileLoader.get(FOOD_CALORIE_INDEX, entryNumber).orElse(DEFAULT_FOOD_CALORIE));
    }

    public int getPortionSize(int entryNumber) throws IllegalAccessException {
        return Integer.parseInt(fileLoader.get(PORTION_SIZE_INDEX, entryNumber).orElse(DEFAULT_PORTION_SIZE));
    }

    public int getDay(int entryNumber) throws IllegalAccessException {
        return Integer.parseInt(fileLoader.get(DAY_INDEX, entryNumber).orElse(DEFAULT_DAY));
    }

    public int getMonth(int entryNumber) throws IllegalAccessException {
        return Integer.parseInt(fileLoader.get(MONTH_INDEX, entryNumber).orElse(DEFAULT_MONTH));
    }

    public int getYear(int entryNumber) throws IllegalAccessException {
        return Integer.parseInt(fileLoader.get(YEAR_INDEX, entryNumber).orElse(DEFAULT_YEAR));
    }

    public int getHour(int entryNumber) throws IllegalAccessException {
        return Integer.parseInt(fileLoader.get(HOUR_INDEX, entryNumber).orElse(DEFAULT_HOUR));
    }

    public int getMinute(int entryNumber) throws IllegalAccessException {
        return Integer.parseInt(fileLoader.get(MINUTE_INDEX, entryNumber).orElse(DEFAULT_MINUTE));
    }
}
