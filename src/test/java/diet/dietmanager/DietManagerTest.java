package diet.dietmanager;

import exceptions.InvalidCommandWordException;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author CFZeon
public class DietManagerTest {
    private static final String TEST_SAVES_FOLDER_DIET = "src/test/java/diet/dietmanager/saves/";
    private static final String TEST_DATE = "2020-04-04";
    private static final Double TEST_CALORIES = 444.0;

    @Test
    void getDateTotalCalories_validInput_returnDouble() {
        DietManager dietManager = new DietManager();
        LocalDate date = LocalDate.parse(TEST_DATE);
        Double expected = TEST_CALORIES;
        Double totalCalories = dietManager.getDateTotalCalories(TEST_SAVES_FOLDER_DIET, date);
        assertEquals(expected, totalCalories);
    }

    @Test
    void getDateTotalCalories_wrongFolderInput_throwsAssertionError() {
        DietManager dietManager = new DietManager();
        LocalDate date = LocalDate.parse(TEST_DATE);
        assertThrows(AssertionError.class, () ->
                dietManager.getDateTotalCalories(TEST_SAVES_FOLDER_DIET + "wrong", date));
    }

    @Test
    void getDateTotalCalories_wrongDateInput_returnsZero() {
        DietManager dietManager = new DietManager();
        Double expected = 0.0;
        Double totalCalories = dietManager.getDateTotalCalories(TEST_SAVES_FOLDER_DIET, java.time.LocalDate.now());
        assertEquals(expected, totalCalories);
    }

    @Test
    void processCommand_invalidInput_throwsInvalidCommandWordException() {
        DietManager dietManager = new DietManager();
        assertThrows(InvalidCommandWordException.class, () ->
                dietManager.processCommand("asdf"));
    }
}
