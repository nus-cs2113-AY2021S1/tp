package seedu.duke.person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonTest {

    private Person person;

    @BeforeEach
    public void setUp() {
        person = new Person(21,165,65,55,ActivityLevel.LOW);
    }

    @Test
    void getAge_person_returnsAge() {
        assertEquals(21, person.getAge());
    }

    @Test
    void setAge_personWithNewAge_returnsNewAge() {
        person.setAge(24);
        assertEquals(24, person.getAge());
    }

    @Test
    void getHeight_person_returnsHeight() {
        assertEquals(165, person.getHeight());
    }

    @Test
    void setHeight_personWithNewHeight_returnsNewHeight() {
        person.setHeight(175);
        assertEquals(175, person.getHeight());
    }

    @Test
    void getOriginalWeight_person_returnsOriginalWeight() {
        assertEquals(65, person.getOriginalWeight());
    }

    @Test
    void setOriginalWeight_personWithNewOriginalWeight_returnsNewOriginalWeight() {
        person.setOriginalWeight(70);
        assertEquals(70, person.getOriginalWeight());
    }

    @Test
    void getTargetWeight_person_returnsTargetWeight() {
        assertEquals(55, person.getTargetWeight());
    }

    @Test
    void setTargetWeight_personWithNewTargetWeight_returnsNewTargetWeight() {
        person.setTargetWeight(50);
        assertEquals(50, person.getTargetWeight());
    }

    @Test
    void getActivityLevel_person_returnsActivityLevel() {
        assertEquals(ActivityLevel.LOW, person.getActivityLevel());
    }

    @Test
    void setActivityLevel_personWithNewActivityLevel_returnsNewActivityLevel() {
        person.setActivityLevel(ActivityLevel.HIGH);
        assertEquals(ActivityLevel.HIGH, person.getActivityLevel());
    }

    @Test
    void setActivityLevel_personWithNullActivityLevel_returnsNullActivityLevel() {
        person.setActivityLevel(null);
        assertEquals(null, person.getActivityLevel());
    }
}
