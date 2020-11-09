package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.DukeException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author GuoAi

public class SpendCommandTest extends CommandTest {

    private Double testValue = 0.0;
    private String testValueString = "0.0";
    private String defaultCurrency = "SGD";
    private String testCurrency = "USD";
    private LocalDate defaultDate = LocalDate.now();
    private LocalDate testDate = LocalDate.parse("2020-10-24");

    @Test
    void execute_validCommand_spends() throws DukeException {
        resetFields();

        argumentsMap.put("v", testValueString);

        new SpendCommand(TEST_DESCRIPTION, argumentsMap).execute(model);

        assertEquals(1, expenses.size());
        assertEquals(TEST_DESCRIPTION, expenses.get(0).getDescription());
        assertEquals(0, expenses.get(0).getValue());
        assertEquals(defaultCurrency, expenses.get(0).getCurrency());
        assertEquals(defaultDate.toString(), expenses.get(0).getDate().toString());
    }

    @Test
    void execute_validCommandWithCurrency_spendsWithCurrency() throws DukeException {
        resetFields();

        argumentsMap.put("v", testValueString);
        argumentsMap.put("currency", testCurrency);

        new SpendCommand(TEST_DESCRIPTION, argumentsMap).execute(model);

        assertEquals(1, expenses.size());
        assertEquals(TEST_DESCRIPTION, expenses.get(0).getDescription());
        assertEquals(0, expenses.get(0).getValue());
        assertEquals(testCurrency, expenses.get(0).getCurrency());
        assertEquals(defaultDate.toString(), expenses.get(0).getDate().toString());
    }

    @Test
    void execute_validCommandWithDate_spendsWithDate() throws DukeException {
        resetFields();

        argumentsMap.put("v", testValueString);
        argumentsMap.put("date", testDate.toString());

        new SpendCommand(TEST_DESCRIPTION, argumentsMap).execute(model);

        assertEquals(1, expenses.size());
        assertEquals(TEST_DESCRIPTION, expenses.get(0).getDescription());
        assertEquals(0, expenses.get(0).getValue());
        assertEquals(defaultCurrency, expenses.get(0).getCurrency());
        assertEquals(testDate.toString(), expenses.get(0).getDate().toString());
    }

    @Test
    void execute_commandWithInvalidDescription_throwsException() {
        resetFields();

        String inputDescription = "";
        argumentsMap.put("v", testValueString);

        assertThrows(DukeException.class, () ->
                new SpendCommand(inputDescription, argumentsMap).execute(model));
    }

    @Test
    void execute_commandWithInvalidValue_throwsException() {
        resetFields();

        String inputValueString = "a";
        argumentsMap.put("v", inputValueString);

        assertThrows(DukeException.class, () ->
                new SpendCommand(TEST_DESCRIPTION, argumentsMap).execute(model));
    }

    @Test
    void execute_commandWithInvalidCurrency_throwsException() {
        resetFields();

        String inputCurrency = "";
        argumentsMap.put("v", testValueString);
        argumentsMap.put("currency", inputCurrency);

        assertThrows(DukeException.class, () ->
                new SpendCommand(TEST_DESCRIPTION, argumentsMap).execute(model));
    }

    @Test
    void execute_commandWithInvalidDate_throwsException() {
        resetFields();

        String inputDate = "";
        argumentsMap.put("v", testValueString);
        argumentsMap.put("date", inputDate);
        assertThrows(DukeException.class, () ->
                new SpendCommand(TEST_DESCRIPTION, argumentsMap).execute(model));

        inputDate = "09-11-2020";
        argumentsMap.put("date", inputDate);
        assertThrows(DukeException.class, () ->
                new SpendCommand(TEST_DESCRIPTION, argumentsMap).execute(model));

        inputDate = "blah";
        argumentsMap.put("date", inputDate);
        assertThrows(DukeException.class, () ->
                new SpendCommand(TEST_DESCRIPTION, argumentsMap).execute(model));
    }
}
