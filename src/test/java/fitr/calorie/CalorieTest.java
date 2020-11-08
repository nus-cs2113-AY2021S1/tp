package fitr;

import fitr.calorie.Calorie;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalorieTest {
    @Test
    public void getCaloriesAsInt_validInt_success() {
        Calorie newCalorie = new Calorie(500);
        assertEquals(500,newCalorie.get());
    }
}
