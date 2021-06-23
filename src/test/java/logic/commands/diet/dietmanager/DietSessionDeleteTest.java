package logic.commands.diet.dietmanager;

import logic.commands.CommandResult;
import org.junit.jupiter.api.Test;
import storage.diet.DietStorage;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ui.diet.dietmanager.DietManagerUi.DIET_DELETE_SUCCESS;
import static ui.diet.dietmanager.DietManagerUi.DIET_DELETE_WRONG_FORMAT;
import static ui.diet.dietmanager.DietManagerUi.DIET_FILE_ARRAY_OUT_OF_BOUND;

//@@author CFZeon
public class DietSessionDeleteTest {
    private static String EMPTY_STRING = "";

    @Test
    void testExecute_validInput_success() {
        DietStorage storage = new DietStorage();
        // to ensure there is something to delete
        System.setIn(new ByteArrayInputStream("end".getBytes()));
        new DietSessionCreate().execute(EMPTY_STRING, storage);
        System.setIn(new ByteArrayInputStream("end".getBytes()));
        String deleteInput = "1";
        CommandResult result = new DietSessionDelete().execute(deleteInput, storage);
        assertEquals(DIET_DELETE_SUCCESS, result.getFeedbackMessage());
    }

    @Test
    void testExecute_outOfBoundsInput_resultOutOfBounds() {
        DietStorage storage = new DietStorage();
        // to ensure there is something to delete
        System.setIn(new ByteArrayInputStream("end".getBytes()));
        new DietSessionCreate().execute(EMPTY_STRING, storage);
        System.setIn(new ByteArrayInputStream("end".getBytes()));
        String deleteInput = "0";
        CommandResult result = new DietSessionDelete().execute(deleteInput, storage);
        assertEquals(DIET_FILE_ARRAY_OUT_OF_BOUND, result.getFeedbackMessage());
    }

    @Test
    void testExecute_wrongInput_resultDeleteWrongFormat() {
        DietStorage storage = new DietStorage();
        // to ensure there is something to delete
        System.setIn(new ByteArrayInputStream("end".getBytes()));
        new DietSessionCreate().execute(EMPTY_STRING, storage);
        System.setIn(new ByteArrayInputStream("end".getBytes()));
        String deleteInput = "a";
        CommandResult result = new DietSessionDelete().execute(deleteInput, storage);
        assertEquals(DIET_DELETE_WRONG_FORMAT, result.getFeedbackMessage());
    }
}
