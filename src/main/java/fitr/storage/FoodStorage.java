package fitr.storage;

import fitr.calorie.Calorie;
import fitr.food.Food;
import fitr.exception.InvalidFileFormatException;
import fitr.list.FoodList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import static fitr.storage.StorageManager.COMMA_SEPARATOR;

/**
 * Handles the read and write operations of the user's food.
 */
public class FoodStorage {
    private static final Logger LOGGER = Logger.getLogger(FoodStorage.class.getName());
    private static final String DEFAULT_FOOD_LIST_PATH = "foodList.txt";
    private final String foodListPath;

    public FoodStorage() throws IOException {
        this(DEFAULT_FOOD_LIST_PATH);
    }

    public FoodStorage(String foodListPath) throws IOException {
        this.foodListPath = foodListPath;
        File foodListFile = new File(foodListPath);
        if (!foodListFile.exists()) {
            boolean isFileExists = foodListFile.createNewFile();
            LOGGER.fine("Food list file created: " + foodListPath);
        }
    }


    /**
     * Loads the list of the user's consumed food from a file and returns an ArrayList of Food objects.
     *
     * @return an ArrayList of Food objects
     * @throws FileNotFoundException FileNotFoundException if the file is not found
     * @throws InvalidFileFormatException if the file format is invalid
     */
    public ArrayList<Food> loadFoodList() throws FileNotFoundException, InvalidFileFormatException {
        LOGGER.fine("Attempting to read file: " + foodListPath);
        ArrayList<Food> foodList = new ArrayList<>();
        String line;
        String[] arguments;
        File foodListFile = new File(foodListPath);
        Scanner readFile = new Scanner(foodListFile);

        while (readFile.hasNext()) {
            line = readFile.nextLine();
            arguments = line.split(COMMA_SEPARATOR);

            if (arguments.length != 4) {
                throw new InvalidFileFormatException();
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            foodList.add(new Food(arguments[0], new Calorie(Integer.parseInt(arguments[1])),
                    Integer.parseInt(arguments[2]), LocalDate.parse(arguments[3], formatter)));
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
        LOGGER.fine("Attempting to write to file: " + foodListPath);
        FileWriter file = new FileWriter(foodListPath);
        Food food;

        for (int i = 0; i < foodList.getSize(); i++) {
            food = foodList.getFood(i);
            file.write(food.getFoodName()
                    + COMMA_SEPARATOR + food.getCalories()
                    + COMMA_SEPARATOR + food.getAmountOfFood()
                    + COMMA_SEPARATOR + food.getDate() + System.lineSeparator());
        }

        LOGGER.fine("Food list file written successfully.");
        file.close();
    }
}
