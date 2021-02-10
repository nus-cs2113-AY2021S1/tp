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

class DeleteExpenseCommandTest {

    private final String[] validAddUserInputs = {"buy /n ice-cream /d 2 /c 3.00", "buy /n chicken rice /d 1 /c 5.00",
        "buy /n pants /d 3 /c $30.00"};
    private final String[] validDeleteUserInputs = {"delete /e 4", "delete /e 1", "delete /e 1", "delete /e 1"};
    private final String[] badUserInputs = {"delete /e", "delete /e three", "delete ", "delete"};
    private final int[] expectedSize = {3,2,1,0};

    @Test
    void deleteExpenseCommand_invalidUserInput_throwsTrippieInvalidArgumentException() {
        for (String badUserInput : badUserInputs) {
            assertThrows(TrippieInvalidArgumentException.class, () -> new DeleteExpenseCommand(badUserInput));
        }
    }

    @Test
    void deleteExpenseCommand_validUserInput_parsedCorrectly() throws TrippieException, ParseException {

        Ui ui = new Ui();
        Storage storage = new Storage();
        TrippieData trippieData = new TrippieData(storage);
        fileSetup(storage, trippieData);

        for (String validAddUserInput : validAddUserInputs) {
            AddExpenseCommand c = new AddExpenseCommand(validAddUserInput);
            c.execute(ui,trippieData);
        }

        for (int i = 0; i < validDeleteUserInputs.length; i++) {
            DeleteExpenseCommand c = new DeleteExpenseCommand(validDeleteUserInputs[i]);
            c.execute(ui,trippieData);
            List<Expense> expenses = trippieData.getCurrentTrip().getExpenseListObject().getExpenseList();
            assertEquals(expectedSize[i],expenses.size());
        }

    }

    private void fileSetup(Storage storage, TrippieData trippieData) throws ParseException, TrippieException {
        storage.setupMasterFile(trippieData);
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Trip newTrip = new Trip(trippieData.getTripList().size(), "Singapore", df.parse("11-11-2020"));
        newTrip.getExpenseListObject().setForExValue(Float.parseFloat("100"));
        newTrip.getExpenseListObject().setCurrencyAbbreviation("SGD");
        newTrip.getExpenseListObject().setBudgetValue(Float.parseFloat("1000"));
        trippieData.getTripList().add(newTrip);

    }
}
