package profile;

import models.Profile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static profile.Constants.EXAMPLE_CALORIES;
import static profile.Constants.EXAMPLE_EXPECTED_WEIGHT;
import static profile.Constants.EXAMPLE_HEIGHT;
import static profile.Constants.EXAMPLE_NAME;
import static profile.Constants.EXAMPLE_WEIGHT;
import static profile.Constants.HEIGHT_LOWER_BOUND;
import static profile.Constants.HEIGHT_UPPER_BOUND;
import static profile.Constants.WEIGHT_LOWER_BOUND;
import static profile.Constants.WEIGHT_UPPER_BOUND;
import static profile.Utils.checkValidName;
import static profile.Utils.checkValidProfile;

//@@author tienkhoa16
class UtilsTest {

    @Test
    void testCheckValidProfile_validProfile_returnTrue() {
        Profile validProfile = new Profile(EXAMPLE_NAME, EXAMPLE_HEIGHT, EXAMPLE_WEIGHT,
                EXAMPLE_EXPECTED_WEIGHT, EXAMPLE_CALORIES);
        assertTrue(checkValidProfile(validProfile));
    }

    @Test
    void testCheckValidProfile_nullInput_returnFalse() {
        assertFalse(checkValidProfile(null));
    }

    @Test
    void testCheckValidProfile_heightLessThanLowerBound_returnFalse() {
        Profile inValidProfile = new Profile(EXAMPLE_NAME, HEIGHT_LOWER_BOUND - 1, EXAMPLE_WEIGHT,
                EXAMPLE_EXPECTED_WEIGHT, EXAMPLE_CALORIES);
        assertFalse(checkValidProfile(inValidProfile));
    }

    @Test
    void testCheckValidProfile_heightGreaterThanUpperBound_returnFalse() {
        Profile inValidProfile = new Profile(EXAMPLE_NAME, HEIGHT_UPPER_BOUND + 1, EXAMPLE_WEIGHT,
                EXAMPLE_EXPECTED_WEIGHT, EXAMPLE_CALORIES);
        assertFalse(checkValidProfile(inValidProfile));
    }

    @Test
    void testCheckValidProfile_weightLessThanLowerBound_returnFalse() {
        Profile inValidProfile = new Profile(EXAMPLE_NAME, EXAMPLE_HEIGHT, WEIGHT_LOWER_BOUND - 1,
                EXAMPLE_EXPECTED_WEIGHT, EXAMPLE_CALORIES);
        assertFalse(checkValidProfile(inValidProfile));
    }

    @Test
    void testCheckValidProfile_weightGreaterThanUpperBound_returnFalse() {
        Profile inValidProfile = new Profile(EXAMPLE_NAME, EXAMPLE_HEIGHT, WEIGHT_UPPER_BOUND + 1,
                EXAMPLE_EXPECTED_WEIGHT, EXAMPLE_CALORIES);
        assertFalse(checkValidProfile(inValidProfile));
    }

    @Test
    void testCheckValidProfile_negativeCalories_returnFalse() {
        Profile inValidProfile = new Profile(EXAMPLE_NAME, EXAMPLE_HEIGHT, EXAMPLE_WEIGHT,
                EXAMPLE_EXPECTED_WEIGHT, -1);
        assertFalse(checkValidProfile(inValidProfile));
    }

    @Test
    void testCheckValidName_validInput_returnTrue() {
        assertTrue(checkValidName("Schwarzenegger"));
    }

    @Test
    void testCheckValidName_emptyInput_returnFalse() {
        assertFalse(checkValidName(""));
    }

    @Test
    void testCheckValidName_nullInput_returnFalse() {
        assertFalse(checkValidName(null));
    }
}
