package exceptions.diet;


import exceptions.SchwarzeneggerException;

import static ui.diet.dietsession.DietSessionUi.MESSAGE_NEGATIVE_CALORIES;

/**
 * Represents exception when calories is negative.
 */
public class NegativeCaloriesException extends SchwarzeneggerException {

    /**
     * Constructs NegativeCaloriesException object inheriting abstract class SchwarzeneggerException.
     */
    public NegativeCaloriesException() {
        super(MESSAGE_NEGATIVE_CALORIES);
    }
}
