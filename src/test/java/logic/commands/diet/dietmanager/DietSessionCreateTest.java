package logic.commands.diet.dietmanager;

import logic.commands.CommandResult;
import org.junit.jupiter.api.Test;
import storage.diet.DietStorage;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ui.diet.dietmanager.DietManagerUi.DIET_CREATE_WRONG_FORMAT;
import static ui.diet.dietmanager.DietManagerUi.DIET_DATE_WRONG_FORMAT;
import static ui.diet.dietmanager.DietManagerUi.DIET_NEW_SUCCESS;

//@@author CFZeon
public class DietSessionCreateTest {
    private static final String EMPTY_STRING = "";

    @Test
    void testExecute_inputEmptyArguments_success() {
        DietStorage storage = new DietStorage();
        System.setIn(new ByteArrayInputStream("end".getBytes()));
        CommandResult result = new DietSessionCreate().execute(EMPTY_STRING, storage);
        assertEquals(DIET_NEW_SUCCESS, result.getFeedbackMessage());
    }

    @Test
    void testExecute_inputOnlyDate_success() {
        String dateInput = "/d 2020-05-04";
        DietStorage storage = new DietStorage();
        System.setIn(new ByteArrayInputStream("end".getBytes()));
        CommandResult result = new DietSessionCreate().execute(dateInput, storage);
        assertEquals(DIET_NEW_SUCCESS, result.getFeedbackMessage());
    }

    @Test
    void testExecute_inputTag_success() {
        String tagInput = "/t breakfast";
        DietStorage storage = new DietStorage();
        System.setIn(new ByteArrayInputStream("end".getBytes()));
        CommandResult result = new DietSessionCreate().execute(tagInput, storage);
        assertEquals(DIET_NEW_SUCCESS, result.getFeedbackMessage());
    }

    @Test
    void testExecute_validInput_success() {
        String tagInput = "/d 2020-05-04 /t breakfast";
        DietStorage storage = new DietStorage();
        System.setIn(new ByteArrayInputStream("end".getBytes()));
        CommandResult result = new DietSessionCreate().execute(tagInput, storage);
        assertEquals(DIET_NEW_SUCCESS, result.getFeedbackMessage());
    }

    @Test
    void testExecute_invalidDate_returnsResultDateWrongFormat() {
        DietStorage storage = new DietStorage();
        String input = "/d 2020-1234";
        System.setIn(new ByteArrayInputStream("end".getBytes()));
        CommandResult result = new DietSessionCreate().execute(input, storage);
        assertEquals(DIET_DATE_WRONG_FORMAT, result.getFeedbackMessage());
    }

    @Test
    void testExecute_invalidInput_placeholder() {
        DietStorage storage = new DietStorage();
        String input = "//d";
        System.setIn(new ByteArrayInputStream("end".getBytes()));
        CommandResult result = new DietSessionCreate().execute(input, storage);
        assertEquals(DIET_CREATE_WRONG_FORMAT, result.getFeedbackMessage());
    }
}
