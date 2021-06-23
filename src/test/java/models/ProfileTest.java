package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ui.CommonUi.EMPTY_STRING;
import static profile.Constants.EXAMPLE_BMI;
import static profile.Constants.EXAMPLE_CALORIES;
import static profile.Constants.EXAMPLE_EXPECTED_WEIGHT;
import static profile.Constants.EXAMPLE_HEIGHT;
import static profile.Constants.EXAMPLE_NAME;
import static profile.Constants.EXAMPLE_WEIGHT;
import static profile.Constants.PROFILE_STRING_REPRESENTATION;

//@@author tienkhoa16
class ProfileTest {
    private static final Profile SAMPLE_PROFILE = new Profile(EXAMPLE_NAME, EXAMPLE_HEIGHT, EXAMPLE_WEIGHT,
            EXAMPLE_EXPECTED_WEIGHT, EXAMPLE_CALORIES);
    private static final String EXAMPLE_PROFILE_STRING = String.format(PROFILE_STRING_REPRESENTATION, EXAMPLE_NAME,
            EXAMPLE_HEIGHT, EXAMPLE_WEIGHT, EXAMPLE_EXPECTED_WEIGHT, EXAMPLE_CALORIES, EXAMPLE_BMI);

    @Test
    void testGetBmiClassification_underweightInput_returnUnderweight() {
        Profile testProfile = new Profile(EXAMPLE_NAME, EXAMPLE_HEIGHT, 54, EXAMPLE_EXPECTED_WEIGHT, EXAMPLE_CALORIES);
        assertEquals("15.3 (Underweight)", testProfile.getBmiClassification());
    }

    @Test
    void testGetBmiClassification_normalWeightInput_returnNormalWeight() {
        Profile testProfile = new Profile(EXAMPLE_NAME, EXAMPLE_HEIGHT, 70, EXAMPLE_EXPECTED_WEIGHT, EXAMPLE_CALORIES);
        assertEquals("19.8 (Normal Weight)", testProfile.getBmiClassification());
    }

    @Test
    void testGetBmiClassification_overweightInput_returnOverweight() {
        Profile testProfile = new Profile(EXAMPLE_NAME, EXAMPLE_HEIGHT, 100, EXAMPLE_EXPECTED_WEIGHT, EXAMPLE_CALORIES);
        assertEquals("28.3 (Overweight)", testProfile.getBmiClassification());
    }

    @Test
    void testGetBmiClassification_obesityClassOneInput_returnObesityClassOne() {
        Profile testProfile = new Profile(EXAMPLE_NAME, EXAMPLE_HEIGHT, 113, EXAMPLE_EXPECTED_WEIGHT, EXAMPLE_CALORIES);
        assertEquals("32.0 (Obesity Class 1)", testProfile.getBmiClassification());
    }

    @Test
    void testGetBmiClassification_obesityClassTwoInput_returnObesityClassTwo() {
        Profile testProfile = new Profile(EXAMPLE_NAME, EXAMPLE_HEIGHT, 127, EXAMPLE_EXPECTED_WEIGHT, EXAMPLE_CALORIES);
        assertEquals("35.9 (Obesity Class 2)", testProfile.getBmiClassification());
    }

    @Test
    void testGetBmiClassification_extremeObesityClassThreeInput_returnExtremeObesityClassThree() {
        Profile testProfile = new Profile(EXAMPLE_NAME, EXAMPLE_HEIGHT, 148, EXAMPLE_EXPECTED_WEIGHT, EXAMPLE_CALORIES);
        assertEquals("41.9 (Extreme Obesity Class 3)", testProfile.getBmiClassification());
    }

    @Test
    void testEquals_inputTwoIdenticalProfiles_returnTrue() {
        Profile sampleProfile = new Profile(EXAMPLE_NAME, EXAMPLE_HEIGHT, EXAMPLE_WEIGHT, EXAMPLE_EXPECTED_WEIGHT,
                EXAMPLE_CALORIES);
        Profile profileToCompare = new Profile(EXAMPLE_NAME, EXAMPLE_HEIGHT, EXAMPLE_WEIGHT, EXAMPLE_EXPECTED_WEIGHT,
                EXAMPLE_CALORIES);
        assertEquals(sampleProfile, profileToCompare);
    }

    @Test
    void testEquals_inputDifferentNames_returnFalse() {
        Profile profileToCompare = new Profile("Duke", EXAMPLE_HEIGHT, EXAMPLE_WEIGHT,
                EXAMPLE_EXPECTED_WEIGHT, EXAMPLE_CALORIES);
        assertFalse(SAMPLE_PROFILE.equals(profileToCompare));
    }

    @Test
    void testEquals_inputDifferentHeights_returnFalse() {
        Profile profileToCompare = new Profile(EXAMPLE_NAME, EXAMPLE_HEIGHT + 1, EXAMPLE_WEIGHT,
                EXAMPLE_EXPECTED_WEIGHT, EXAMPLE_CALORIES);
        assertNotEquals(profileToCompare, SAMPLE_PROFILE);
    }

    @Test
    void testEquals_inputDifferentWeights_returnFalse() {
        Profile profileToCompare = new Profile(EXAMPLE_NAME, EXAMPLE_HEIGHT, EXAMPLE_WEIGHT + 1,
                EXAMPLE_EXPECTED_WEIGHT, EXAMPLE_CALORIES);
        assertNotEquals(profileToCompare, SAMPLE_PROFILE);
    }

    @Test
    void testEquals_inputDifferentExpectedWeights_returnFalse() {
        Profile profileToCompare = new Profile(EXAMPLE_NAME, EXAMPLE_HEIGHT, EXAMPLE_WEIGHT,
                EXAMPLE_EXPECTED_WEIGHT + 1, EXAMPLE_CALORIES);
        assertNotEquals(profileToCompare, SAMPLE_PROFILE);
    }

    @Test
    void testEquals_inputDifferentCalories_returnFalse() {
        Profile profileToCompare = new Profile(EXAMPLE_NAME, EXAMPLE_HEIGHT, EXAMPLE_WEIGHT,
                EXAMPLE_EXPECTED_WEIGHT, EXAMPLE_CALORIES + 1);
        assertNotEquals(profileToCompare, SAMPLE_PROFILE);
    }

    @Test
    void testEquals_inputSameProfileObject_returnTrue() {
        assertEquals(SAMPLE_PROFILE, SAMPLE_PROFILE);
    }

    @Test
    void testEquals_inputObjectOfDifferentClass_returnFalse() {
        assertNotEquals(SAMPLE_PROFILE, new StringBuilder());
    }

    @Test
    void testEquals_inputNull_returnFalse() {
        assertNotEquals(SAMPLE_PROFILE, null);
    }

    @Test
    void testToString_validProfile_returnValidProfileString() {
        assertEquals(EXAMPLE_PROFILE_STRING, SAMPLE_PROFILE.toString());
    }

    @Test
    void testToString_invalidProfile_throwsAssertionError() {
        Profile invalidProfile = new Profile(EMPTY_STRING, EXAMPLE_HEIGHT, EXAMPLE_WEIGHT,
                EXAMPLE_EXPECTED_WEIGHT, EXAMPLE_CALORIES);
        assertThrows(AssertionError.class, invalidProfile::toString);
    }
}
