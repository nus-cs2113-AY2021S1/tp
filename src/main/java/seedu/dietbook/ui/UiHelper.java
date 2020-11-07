package seedu.dietbook.ui;

import seedu.dietbook.logger.MainLogger;

import java.time.LocalDateTime;
import java.util.logging.Level;

/**
 * Represents a helper that provide methods utilised in the other Ui related classes.
 * A <code>UiHelper</code> object contains all the helper methods needed for the other Ui related classes.
 */
public class UiHelper {

    static final String LINE_SEPARATOR = System.lineSeparator();

    private final MainLogger mainLogger;

    UiHelper() {
        mainLogger = new MainLogger(UiHelper.class.getName());
    }

    /**
     * Returns true if the string length is zero after it has been trimmed for leading and trailing
     * spaces, false otherwise.
     *
     * @param string The string to be trimmed and determined if length is zero.
     * @return True if the string length is zero after it has been trimmed for leading and trailing
     *         spaces, false otherwise.
     */
    boolean isEmptyString(String string) {
        mainLogger.log(Level.FINE, "String to check if empty: " + string);
        performAssertionsForNullStringInputs(string, "String to be determined if empty");

        return trimString(string).length() == 0;
    }

    /**
     * Returns a string that has been trimmed for leading and trailing spaces.
     *
     * @param string The string to be trimmed for leading and trailing spaces.
     * @return A string that has been trimmed for leading and trailing spaces.
     */
    String trimString(String string) {
        mainLogger.log(Level.FINE, "String to trim: " + string);
        performAssertionsForNullStringInputs(string, "String to trim");

        return string.trim();
    }

    /**
     * Performs assertions for the string inputs.
     *
     * @param string The string input.
     * @param stringDescription A description of what the string input represents.
     */
    void performAssertionsForStringInputs(String string, String stringDescription) {
        performAssertionsForNullStringInputs(string, stringDescription);
        assert !isEmptyString(string) : stringDescription + " should not be an empty string";
    }

    /**
     * Performs assertions for time inputs.
     *
     * @param start Starting date time of the time period given.
     * @param end Ending date time of the time period given.
     */
    void performAssertionsForTimePeriod(LocalDateTime start, LocalDateTime end) {
        assert start != null : "Starting date time of the time period given should not be null";
        assert end != null : "Ending date time of the time period given should not be null";
        assert !start.isAfter(end) : "Starting date time should not be later than ending date time "
                + "of the time period";
        LocalDateTime now = LocalDateTime.now();
        assert !start.isAfter(now) : "Starting date time of the time period given should "
                + "not be in the future" + start + LocalDateTime.now();
        assert !end.isAfter(now) : "Ending date time of the time period given should not be"
                + " in the future" + end + LocalDateTime.now();
    }

    /**
     * Performs assertions for null string inputs.
     *
     * @param string The string input.
     * @param stringDescription A description of what the string input represents.
     */
    void performAssertionsForNullStringInputs(String string, String stringDescription) {
        assert string != null : stringDescription + " should not be null";
    }

    /**
     * Performs assertions for nutritional intake inputs.
     *
     * @param nutrientIntake The nutritional intake value.
     * @param nutrientType The nutrient type.
     */
    void performAssertionsForNutritionalIntake(int nutrientIntake, String nutrientType) {
        assert nutrientIntake >= 0 : "Total " + nutrientType + " intake should be equals to or greater than 0";
    }

    /**
     * Performs assertions for calorie recommendation input.
     *
     * @param calorieRecommendation The recommended daily calorie intake for the user.
     */
    void performAssertionsForCalorieRecommendation(int calorieRecommendation) {
        // A minimum daily intake of 1200 calorie is required.
        int minCalorie = 1000;
        assert calorieRecommendation >= minCalorie : "Daily calorie recommendation should be equals to or "
                + "greater than " + minCalorie;
        // Highest calorie intake recommendation allowed.
        int maxCalorie = 20000;
        assert calorieRecommendation <= maxCalorie : "Daily calorie recommendation should be equals to or "
                + "less than " + maxCalorie;
    }
}