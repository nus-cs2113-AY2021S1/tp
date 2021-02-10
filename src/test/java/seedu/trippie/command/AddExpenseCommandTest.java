package seedu.trippie.command;

import org.junit.jupiter.api.Test;
import seedu.trippie.Storage;
import seedu.trippie.Ui;
import seedu.trippie.data.Expense;
import seedu.trippie.data.Trip;
import seedu.trippie.data.TrippieData;
import seedu.trippie.exception.TrippieException;
import seedu.trippie.exception.TrippieInvalidArgumentException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddExpenseCommandTest {

    private final String[] validUserInputs = {"buy /n ice-cream /d 2 /c 3.00", "buy /n chicken rice /d 1 /c 5.00",
        "buy /n pants /d 3 /c $30.00"};

    private final String[] badUserInputs = {"buy /n /d /c", "buy /n ice-cream /d 2 /c three dollars",
        "buy /n ice-cream /d two /c 3.00", "buy /n ice-cream /d /c", "buy /n /d 2 /c",
        "buys /n ice-cream /d 2 /c 3.00"};

    @Test
    void addExpenseCommand_invalidUserInput_throwsTrippieInvalidArgumentException() {
        for (String badUserInput : badUserInputs) {
            assertThrows(TrippieInvalidArgumentException.class, () -> new AddExpenseCommand(badUserInput));
        }
    }

    @Test
    void addExpenseCommand_validUserInput_setsExpenseParameters() throws TrippieInvalidArgumentException {
        String[] expenseNames = {"ice-cream", "chicken rice", "pants"};
        Float[] expenseCosts  = {Float.parseFloat("3.00"), Float.parseFloat("5.00"),
                Float.parseFloat("30.00"),};
        int[] expenseDays = {2,1,3};
        for (int i = 0; i < validUserInputs.length; i++) {
            AddExpenseCommand c = new AddExpenseCommand(validUserInputs[i]);
            assertEquals(expenseNames[i], c.extractExpenseName(validUserInputs[i]));
            assertEquals(expenseCosts[i], c.extractExpenseCost(validUserInputs[i]));
            assertEquals(expenseDays[i], c.extractDayBought(validUserInputs[i]));
        }
    }

    @Test
    void addExpenseCommand_validUserInput_parsedCorrectly() throws ParseException, TrippieException {

        Ui ui = new Ui();
        Storage storage = new Storage();
        TrippieData trippieData = new TrippieData(storage);
        fileSetup(storage, trippieData);

        for (String validUserInput : validUserInputs) {
            AddExpenseCommand c = new AddExpenseCommand(validUserInput);
            c.execute(ui,trippieData);
        }

        List<Expense> expenses = trippieData.getCurrentTrip().getExpenseListObject().getExpenseList();
        assertEquals(3,expenses.size());

    }

    private void fileSetup(Storage storage, TrippieData trippieData) throws ParseException, TrippieException {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Trip newTrip = new Trip(trippieData.getTripList().size(), "Singapore", df.parse("11-11-2020"));
        newTrip.getExpenseListObject().setForExValue(Float.parseFloat("100"));
        newTrip.getExpenseListObject().setCurrencyAbbreviation("SGD");
        newTrip.getExpenseListObject().setBudgetValue(Float.parseFloat("1000"));
        int index = trippieData.getTripList().size();
        trippieData.getTripList().add(newTrip);
        trippieData.setCurrentTripFromIndex(index);
    }

}
