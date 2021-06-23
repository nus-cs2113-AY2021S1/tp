package logic.parser;

import logic.parser.DietSessionParser;
import exceptions.diet.NegativeCaloriesException;
import exceptions.diet.NoNameException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DietSessionParserTest {

    @Test
    void processFoodName_ValidInput_Success() throws NoNameException {
        String input = "Food /c 123";
        DietSessionParser parser = new DietSessionParser();
        String expected = "Food";
        String result = parser.processFoodName(input);
        assertEquals(expected, result);
    }


    @Test
    void processFoodCalories_ValidInput_Success() throws NegativeCaloriesException {
        String input = "Food /c 123";
        DietSessionParser parser = new DietSessionParser();
        Double expected = 123.0;
        double result = parser.processFoodCalories(input);
        assertEquals(expected, result);
    }
}
