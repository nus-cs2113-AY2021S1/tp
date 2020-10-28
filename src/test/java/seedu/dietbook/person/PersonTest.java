package seedu.dietbook.person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PersonTest {

    private Person person;

    @BeforeEach
    public void setUp() {
        person = new Person("Jack", Gender.MALE,21,165,75,65,
                60, ActivityLevel.LOW);
    }

    @Test
    void gender_setGenderToFemale_returnsCorrectGenderDescription() {
        person.setGender(Gender.FEMALE);
        assertEquals("female", person.getGender().getDescription());
    }

    @Test
    void gender_setGenderToMale_returnsCorrectGenderDescription() {
        person.setGender(Gender.MALE);
        assertEquals("male", person.getGender().getDescription());
    }

    @Test
    void gender_setGenderToOthers_returnsCorrectGenderDescription() {
        person.setGender(Gender.OTHERS);
        assertEquals("others", person.getGender().getDescription());
    }
    
    @Test
    void gender_setGenderToNull_expectAssertionError() {
        assertThrows(AssertionError.class, () -> person.setGender(null));
    }

    @Test
    void setActivityLevel_setNewActivityLevelToNone_returnsCorrectActivityLevelDescription() {
        person.setActivityLevel(ActivityLevel.NONE);
        assertEquals("You hardly engage in any exercise or have a job that requires little to no "
                + "physical activity.", person.getActivityLevel().getDescription());
    }

    @Test
    void setActivityLevel_setActivityLevelToLow_returnsCorrectActivityLevelDescription() {
        person.setActivityLevel(ActivityLevel.LOW);
        assertEquals("You engage in some form of light exercise or have a job that requires some "
                + "physical activity.", person.getActivityLevel().getDescription());
    }

    @Test
    void setActivityLevel_setActivityLevelToMedium_returnsCorrectActivityLevelDescription() {
        person.setActivityLevel(ActivityLevel.MEDIUM);
        assertEquals("You engage in moderate amount of exercise or have a job that requires moderate "
                + "physical activity.", person.getActivityLevel().getDescription());
    }

    @Test
    void setActivityLevel_setActivityLevelToHigh_returnsCorrectActivityLevelDescription() {
        person.setActivityLevel(ActivityLevel.HIGH);
        assertEquals("You engage in vigorous exercise or have a physically demanding job.",
                person.getActivityLevel().getDescription());
    }

    @Test
    void setActivityLevel_setActivityLevelToExtreme_returnsCorrectActivityLevelDescription() {
        person.setActivityLevel(ActivityLevel.EXTREME);
        assertEquals("You engage in extremely vigorous exercise or have an extremely physically "
                + "demanding job.", person.getActivityLevel().getDescription());
    }

    @Test
    void setActivityLevel_setActivityLevelToNull_expectsAssertionErrors() {
        assertThrows(AssertionError.class, () -> person.setActivityLevel(null));
    }
}
