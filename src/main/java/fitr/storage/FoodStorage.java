package fitr.storage;

import fitr.Calorie;
import fitr.Food;
import fitr.list.FoodList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import static fitr.storage.StorageManager.COMMA_SEPARATOR;

public class FoodStorage {
    private static final Logger LOGGER = Logger.getLogger(FoodStorage.class.getName());
    private static final String FOOD_LIST_PATH = "foodList.txt";

    public FoodStorage() throws IOException {
        File foodListFile = new File(FOOD_LIST_PATH);
        if (!foodListFile.exists()) {
            boolean isFileExists = foodListFile.createNewFile();
            LOGGER.fine("Food list file created: " + FOOD_LIST_PATH);
        }
    }

    /**
     * Loads the list of the user's consumed food from a file and returns an ArrayList of Food objects.
     *
     * @return an ArrayList of Food objects
     * @throws FileNotFoundException if the file is not found
     */
    public ArrayList<Food> loadFoodList() throws FileNotFoundException {
        LOGGER.fine("Attempting to read file: " + FOOD_LIST_PATH);
        ArrayList<Food> foodList = new ArrayList<>();
        String line;
        String[] arguments;
        File foodListFile = new File(FOOD_LIST_PATH);
        Scanner readFile = new Scanner(foodListFile);

        while (readFile.hasNext()) {
            line = readFile.nextLine();
            arguments = line.split(COMMA_SEPARATOR);
            foodList.add(new Food(arguments[0],
                    new Calorie(Integer.parseInt(arguments[1])), Integer.parseInt(arguments[2])));
        }

        LOGGER.fine("Food list file read successfully.");
        return foodList;
    }

    /**
     * Writes the food list data into a file.
     *
     * @param foodList the food list to write to the file
     * @throws IOException if an I/O error has occurred
     */
    public void writeFoodList(FoodList foodList) throws IOException {
        LOGGER.fine("Attempting to write to file: " + FOOD_LIST_PATH);
        FileWriter file = new FileWriter(FOOD_LIST_PATH);
        Food food;

        for (int i = 0; i < foodList.getSize(); i++) {
            food = foodList.getFood(i);
            file.write(food.getFoodName()
                    + COMMA_SEPARATOR + food.getCalories()
                    + COMMA_SEPARATOR + food.getAmountOfFood() + System.lineSeparator());
        }

        LOGGER.fine("Food list file written successfully.");
        file.close();
    }
}
