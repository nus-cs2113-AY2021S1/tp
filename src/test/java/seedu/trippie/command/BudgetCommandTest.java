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

class BudgetCommandTest {


    private final String[] badUserInputs = {"budgets 1000", "budget", "budget ", "budget abc"};
    private final String[] validUserInputs = {"budget 1000", "budget 2000", "budget 3000"};
    private final String[] budgetValues = {"1000", "2000", "3000"};

    @Test
    void budgetCommand_invalidUserInput_throwsTrippieInvalidArgumentException() {
        for (String badUserInput : badUserInputs) {
            assertThrows(TrippieInvalidArgumentException.class, () -> new BudgetCommand(badUserInput));
        }
    }

    @Test
    void budgetCommand_validUserInput_parsedCorrectly() throws TrippieException, ParseException {

        Ui ui = new Ui();
        Storage storage = new Storage();
        TrippieData trippieData = new TrippieData(storage);
        fileSetup(storage, trippieData);

        for (int i = 0; i < validUserInputs.length; i++) {
            BudgetCommand c = new BudgetCommand(validUserInputs[i]);
            c.execute(ui,trippieData);
            assertEquals(Float.parseFloat(budgetValues[i]),trippieData.getCurrentTrip()
                    .getExpenseListObject().getBudgetValue());
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
