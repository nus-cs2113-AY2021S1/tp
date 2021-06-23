package logic.commands.diet.dietmanager;

import exceptions.InvalidDateFormatException;
import exceptions.diet.InvalidSearchDateException;
import org.junit.jupiter.api.Test;
import storage.diet.DietStorage;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author CFZeon
public class DietSessionSearchTest {

    @Test
    void testExecute_inputEndDateBeforeStartDate_ValidStorage_throwsInvalidSearchDateException() {
        String input = "/s 2020-05-04 /e 2020-05-03";
        assertThrows(InvalidSearchDateException.class, () -> {
            new DietSessionSearch().execute(input, (DietStorage) null);
        });
    }

    @Test
    void testExecute_inputInvalidDate_throwsInvalidDateFormatException() {
        String input = "/s 2020-";
        assertThrows(InvalidDateFormatException.class, () -> {
            new DietSessionSearch().execute(input, (DietStorage) null);
        });
    }
}
