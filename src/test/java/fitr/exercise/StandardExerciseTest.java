package fitr.exercise;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StandardExerciseTest {
    ArrayList<Double> duration = new ArrayList<>();
    ArrayList<Integer> sets = new ArrayList<>();
    @Test
    public void getName_validName_success() {
        StandardExercise standardExercise = new StandardExercise("Thigh stretch", 2.4, duration, sets);
        assertEquals("Thigh stretch", standardExercise.getName());
    }

    @Test
    public void getMet_validMet_success() {
        StandardExercise standardExercise = new StandardExercise("Thigh stretch", 2.4, duration, sets);
        assertEquals(2.4, standardExercise.getMet());
    }

    @Test
    public void getDuration_validDuration_success() {
        duration.add(0.5);
        duration.add(0.6);
        StandardExercise standardExercise = new StandardExercise("Thigh stretch", 2.4, duration, sets);
        assertEquals(0.5, standardExercise.getDuration().get(0));
    }

    @Test
    public void getSets_validSets_success() {
        sets.add(4);
        sets.add(5);
        StandardExercise standardExercise = new StandardExercise("Thigh stretch", 2.4, duration, sets);
        assertEquals(5, standardExercise.getSets().get(1));
    }
}
