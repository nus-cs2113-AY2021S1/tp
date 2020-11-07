package seedu.dietbook.saveload;

import seedu.dietbook.food.Food;

import java.io.FileNotFoundException;

/***
 * this class takes care of saving and loading of food, portion and date
 */
public class FoodPortionDateSaveLoadManager {
    private static final int DEFAULT_SAVER_WIDTH = 7;
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
    private static final int TIME_INDEX = 10;

    private static final String DEFAULT_FOOD_NAME = "No Food Name";
    private static final String DEFAULT_FOOD_CALORIE = "0";
    private static final String DEFAULT_FOOD_CARBOHYDRATE= "0";
    private static final String DEFAULT_FOOD_PROTEIN= "0";
    private static final String DEFAULT_FOOD_FAT = "0";
    private static final String DEFAULT_PORTION_SIZE = "1";
    private static final String DEFAULT_DAY = "1";
    private static final String DEFAULT_MONTH= "1";
    private static final String DEFAULT_YEAR = "2000";
    private static final String DEFAULT_TIME = "0";


    private static final String FOOD_FOLDER_NAME = "Food##PORTION###dDATE##folder";

    private static final String DEFAULT_NAME = "MISSING NAME";
    private static final String DEFAULT_NUTRITION_VALUE = "0";

    private Saver saver;
    private Loader fileLoader;

    public FoodPortionDateSaveLoadManager() {
        this.saver = new Saver(DEFAULT_SAVER_WIDTH, DEFAULT_SAVER_HEIGHT);
        this.fileLoader = Loader.loadEmpty();
    }

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

    /**
     * Clears the saver and sets the number of entries it can take
     * call this function at the start of a series of functions to store data
     *
     * @param numEntry num of entries
     */
    public void readySaver(Integer numEntry){
        saver.resetSize(DEFAULT_SAVER_WIDTH, numEntry);
    }

    public void addFood(Food food, int entryNumber){
        setFoodName(food.getName(), entryNumber);
        addFoodCalorie(food.getCalorie(), entryNumber);
        setFoodCarbohydrate(food.getCarbohydrate(), entryNumber);
        addFoodProtein(food.getProtein(), entryNumber);
        addFoodFat(food.getFat(), entryNumber);
    }

    public Food getFood(Integer entryNumber) throws IllegalAccessException {
        String name = getFoodName(entryNumber);
        int calorie = getFoodCalorie(entryNumber);
        int carbohydrate = getFoodCarbohydrate(entryNumber);
        int protein = getFoodProtein(entryNumber);
        int fat = getFoodFat(entryNumber);
        return new Food(name, calorie, carbohydrate, protein, fat);
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

    private void setFoodCarbohydrate( int carbohydrate, int entryNumber) {
        saver.add(Integer.toString(carbohydrate),  FOOD_CARBOHYDRATE_INDEX, entryNumber);
    }

    private void setFoodName(String name, int entryNumber) {
        saver.add(name, FOOD_NAME_INDEX, entryNumber);
    }

    private void addFoodCalorie(int calorie, int entryNumber) {
        saver.add(Integer.toString(calorie), FOOD_CALORIE_INDEX, entryNumber);
    }

    private void addFoodProtein(int protein, int entryNumber){
        saver.add(Integer.toString(protein), FOOD_PROTEIN_INDEX, entryNumber);
    }

    private void addFoodFat(int fat, int entryNumber){
        saver.add(Integer.toString(fat), FOOD_FAT_INDEX, entryNumber);
    }

    public void setPortionSize(int portionSize, Integer entryNumber){
        saver.add(Integer.toString(portionSize), PORTION_SIZE_INDEX, entryNumber);
    }

    public void setDay(int day, int entryNumber){
        saver.add(Integer.toString(day), DAY_INDEX, entryNumber);
    }

    public void setMonth(int month, int entryNumber){
        saver.add(Integer.toString(month), MONTH_INDEX, entryNumber);
    }

    public void setYear(int year, int entryNumber){
        saver.add(Integer.toString(year), YEAR_INDEX, entryNumber);
    }

    public void setTime(int time, int entryNumber){
        saver.add(Integer.toString(time), TIME_INDEX, entryNumber);
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

    public int getTime(int entryNumber) throws IllegalAccessException {
        return Integer.parseInt(fileLoader.get(TIME_INDEX, entryNumber).orElse(DEFAULT_TIME));
    }
}
