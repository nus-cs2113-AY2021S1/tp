package logic.commands.diet.dietmanager;

import logic.commands.CommandResult;
import org.junit.jupiter.api.Test;
import storage.diet.DietStorage;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ui.diet.dietmanager.DietManagerUi.DIET_EDIT_WRONG_FORMAT;
import static ui.diet.dietmanager.DietManagerUi.DIET_FILE_ARRAY_OUT_OF_BOUND;
import static ui.diet.dietmanager.DietManagerUi.DIET_NEW_SUCCESS;

//@@author CFZeon
public class DietSessionEditTest {
    private static String EMPTY_STRING = "";

    @Test
    void testExecute_validInput_success() {
        DietStorage storage = new DietStorage();
        // to ensure there is something to edit
        System.setIn(new ByteArrayInputStream("end".getBytes()));
        new DietSessionCreate().execute(EMPTY_STRING, storage);
        System.setIn(new ByteArrayInputStream("end".getBytes()));
        String editInput = "1";
        CommandResult result = new DietSessionEdit().execute(editInput, storage);
        assertEquals(DIET_NEW_SUCCESS, result.getFeedbackMessage());
    }

    @Test
    void testExecute_invalidInput_resultArrayOutOfBound() {
        DietStorage storage = new DietStorage();
        // to ensure there is something to edit
        System.setIn(new ByteArrayInputStream("end".getBytes()));
        new DietSessionCreate().execute(EMPTY_STRING, storage);
        System.setIn(new ByteArrayInputStream("end".getBytes()));
        String editInput = "0";
        CommandResult result = new DietSessionEdit().execute(editInput, storage);
        assertEquals(DIET_FILE_ARRAY_OUT_OF_BOUND, result.getFeedbackMessage());
    }

    @Test
    void testExecute_emptyInput_resultEditWrongFormat() {
        DietStorage storage = new DietStorage();
        // to ensure there is something to edit
        System.setIn(new ByteArrayInputStream("end".getBytes()));
        new DietSessionCreate().execute(EMPTY_STRING, storage);
        System.setIn(new ByteArrayInputStream("end".getBytes()));
        CommandResult result = new DietSessionEdit().execute(EMPTY_STRING, storage);
        assertEquals(DIET_EDIT_WRONG_FORMAT, result.getFeedbackMessage());
    }
}
