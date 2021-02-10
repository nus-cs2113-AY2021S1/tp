package seedu.trippie.command;

import org.junit.jupiter.api.Test;
import seedu.trippie.Storage;
import seedu.trippie.Ui;
import seedu.trippie.data.Trip;
import seedu.trippie.data.TrippieData;
import seedu.trippie.exception.TrippieException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ListExpenseCommandTest {

    private static final String LIST_IS_EMPTY = "There is currently nothing in your Expense list.";
    private static final String REMAINING_BUDGET_LEFT = "You are still spending within your budget.";
    private static final String NO_BUDGET_LEFT = "You have spent finish your budget.";
    private static final String EXCEED_BUDGET = "WARNING! You have exceeded your initial budget.\n"
            + "Recommended action: Increase budget limit.";
    private final String[] validAddUserInputs = {"buy /n ice-cream /d 2 /c 3.00", "buy /n chicken rice /d 1 /c 5.00",
        "buy /n pants /d 3 /c $30.00"};
    private final String[] validAddEnoughUserInputs = {"buy /n right price product /d 1 /c 100000"};
    private final String[] invalidAddUserInputs = {"buy /n priceless product /d 2 /c 100001"};

    @Test
    void listExpenseCommand_emptyList_printListIsEmpty() throws TrippieException, ParseException {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TrippieData trippieData = new TrippieData(storage);
        fileSetup(storage, trippieData);

        ListExpenseCommand c = new ListExpenseCommand();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outputStream);
        System.setOut(ps);
        PrintStream old = System.out;
        c.execute(ui, trippieData);
        System.out.flush();
        System.setOut(old);
        assertTrue(outputStream.toString().contains(LIST_IS_EMPTY));
    }

    @Test
    void listExpenseCommand_validUserInput_printRemainBudget() throws TrippieException, ParseException {

        Ui ui = new Ui();
        Storage storage = new Storage();
        TrippieData trippieData = new TrippieData(storage);
        fileSetup(storage, trippieData);

        for (String validAddUserInput : validAddUserInputs) {
            AddExpenseCommand c = new AddExpenseCommand(validAddUserInput);
            c.execute(ui,trippieData);
        }

        ListExpenseCommand c = new ListExpenseCommand();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outputStream);
        System.setOut(ps);
        PrintStream old = System.out;
        c.execute(ui, trippieData);
        System.out.flush();
        System.setOut(old);
        assertTrue(outputStream.toString().contains(REMAINING_BUDGET_LEFT));

    }

    @Test
    void listExpenseCommand_validUserInput_printEqualBudget() throws TrippieException, ParseException {

        Ui ui = new Ui();
        Storage storage = new Storage();
        TrippieData trippieData = new TrippieData(storage);
        fileSetup(storage, trippieData);

        for (String validAddEnoughUserInput : validAddEnoughUserInputs) {
            AddExpenseCommand c = new AddExpenseCommand(validAddEnoughUserInput);
            c.execute(ui,trippieData);
        }

        ListExpenseCommand c = new ListExpenseCommand();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outputStream);
        System.setOut(ps);
        PrintStream old = System.out;
        c.execute(ui, trippieData);
        System.out.flush();
        System.setOut(old);
        assertTrue(outputStream.toString().contains(NO_BUDGET_LEFT));
    }

    @Test
    void listExpenseCommand_invalidUserInput_exceedBudget() throws TrippieException, ParseException {

        Ui ui = new Ui();
        Storage storage = new Storage();
        TrippieData trippieData = new TrippieData(storage);
        fileSetup(storage, trippieData);

        for (String invalidAddUserInput : invalidAddUserInputs) {
            AddExpenseCommand c = new AddExpenseCommand(invalidAddUserInput);
            c.execute(ui,trippieData);
        }

        ListExpenseCommand c = new ListExpenseCommand();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outputStream);
        System.setOut(ps);
        PrintStream old = System.out;
        c.execute(ui, trippieData);
        System.out.flush();
        System.setOut(old);
        assertTrue(outputStream.toString().contains(EXCEED_BUDGET));
    }

    private void fileSetup(Storage storage, TrippieData trippieData) throws TrippieException, ParseException {
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
