package profile;

import models.Profile;

import static profile.Constants.CALORIES_LOWER_BOUND;
import static profile.Constants.HEIGHT_LOWER_BOUND;
import static profile.Constants.HEIGHT_UPPER_BOUND;
import static profile.Constants.WEIGHT_LOWER_BOUND;
import static profile.Constants.WEIGHT_UPPER_BOUND;

//@@author tienkhoa16

/**
 * A class containing utility methods used in Profile Session.
 */
public class Utils {
    /**
     * Verifies if user's input when creating profile is valid.
     *
     * @param profile User Profile object.
     * @return Whether input profile is valid.
     */
    public static boolean checkValidProfile(Profile profile) {
        return (profile != null
                && checkValidName(profile.getName())
                && checkValidCalories(profile.getCalories())
                && checkValidHeight(profile.getHeight())
                && checkValidWeight(profile.getWeight())
                && checkValidWeight(profile.getExpectedWeight()));
    }

    /**
     * Verifies if user's input name is not empty string.
     *
     * @param name User's input name.
     * @return Whether input name is valid.
     */
    public static boolean checkValidName(String name) {
        return name != null && !name.isEmpty();
    }

    /**
     * Verifies if user's input calories is not negative.
     *
     * @param calories User's expected calories intake daily.
     * @return Whether calories amount is not negative.
     */
    public static boolean checkValidCalories(double calories) {
        return (calories >= CALORIES_LOWER_BOUND);
    }

    /**
     * Verifies if user's input height is in the valid range
     * (between {@link HEIGHT_LOWER_BOUND} and {@link HEIGHT_UPPER_BOUND} inclusive).
     *
     * @param height User's input height.
     * @return Whether input height is valid.
     */
    public static boolean checkValidHeight(int height) {
        return (height >= HEIGHT_LOWER_BOUND && height <= HEIGHT_UPPER_BOUND);
    }

    /**
     * Verifies if user's input weight is in the valid range
     * (between {@link WEIGHT_LOWER_BOUND} and {@link WEIGHT_UPPER_BOUND} inclusive).
     *
     * @param weight User's input weight.
     * @return Whether input weight is valid.
     */
    public static boolean checkValidWeight(double weight) {
        return (weight >= WEIGHT_LOWER_BOUND && weight <= WEIGHT_UPPER_BOUND);
    }
}
