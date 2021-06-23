package logic.parser;

import exceptions.diet.NegativeCaloriesException;
import exceptions.diet.NoNameException;
import logger.SchwarzeneggerLogger;

import java.util.logging.Level;
import java.util.logging.Logger;

//@@author zsk612
/**
 * A class that is responsible for parsing user inputs in Diet Session.
 */
public class DietSessionParser extends CommonParser {
    private static Logger logger = SchwarzeneggerLogger.getInstanceLogger();

    /**
     * Processes the name of the food item.
     *
     * @param food string for food content
     * @return food name
     * @throws IndexOutOfBoundsException handles exception for not inputting food name or calories
     * @throws NoNameException handles empty food name
     */
    public String processFoodName(String food) throws IndexOutOfBoundsException, NoNameException {
        String[] temp = food.trim().split("/c", 2);
        if (temp[0].trim().isEmpty()) {
            throw new NoNameException();
        }
        return temp[0].trim();
    }

    /**
     * Processes the calories of the food item.
     *
     * @param food string for food content
     * @return food calories
     * @throws NumberFormatException handles exception for wrong calories input
     * @throws NegativeCaloriesException handles negative calories input
     */
    public double processFoodCalories(String food) throws NumberFormatException, NegativeCaloriesException {
        String[] temp = food.trim().split("/c", 2);
        if (Double.parseDouble(temp[1]) < 0) {
            throw new NegativeCaloriesException();
        }
        logger.log(Level.INFO, "Processed food calories successfully");
        return Double.parseDouble(temp[1]);
    }


}
