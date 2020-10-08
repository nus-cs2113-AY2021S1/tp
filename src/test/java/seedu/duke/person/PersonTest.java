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
        assertEquals(person.getAge(),21);
    }

    @Test
    void setAge_personWithNewAge_returnsNewAge() {
        person.setAge(24);
        assertEquals(person.getAge(),24);
    }

    @Test
    void getHeight_person_returnsHeight() {
        assertEquals(person.getHeight(),165);
    }

    @Test
    void setHeight_personWithNewHeight_returnsNewHeight() {
        person.setHeight(175);
        assertEquals(person.getHeight(),175);
    }

    @Test
    void getOriginalWeight_person_returnsOriginalWeight() {
        assertEquals(person.getOriginalWeight(),65);
    }

    @Test
    void setOriginalWeight_personWithNewOriginalWeight_returnsNewOriginalWeight() {
        person.setOriginalWeight(70);
        assertEquals(person.getOriginalWeight(),70);
    }

    @Test
    void getTargetWeight_person_returnsTargetWeight() {
        assertEquals(person.getTargetWeight(),55);
    }

    @Test
    void setTargetWeight_personWithNewTargetWeight_returnsNewTargetWeight() {
        person.setTargetWeight(50);
        assertEquals(person.getTargetWeight(),50);
    }

    @Test
    void getActivityLevel_person_returnsActivityLevel() {
        assertEquals(person.getActivityLevel(),ActivityLevel.LOW);
    }

    @Test
    void setActivityLevel_personWithNewActivityLevel_returnsNewActivityLevel() {
        person.setActivityLevel(ActivityLevel.HIGH);
        assertEquals(person.getActivityLevel(),ActivityLevel.HIGH);
    }
}
