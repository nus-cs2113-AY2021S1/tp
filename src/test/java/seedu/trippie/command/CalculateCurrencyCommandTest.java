package seedu.trippie.command;

import org.junit.jupiter.api.Test;
import seedu.trippie.Storage;
import seedu.trippie.Ui;
import seedu.trippie.data.CurrencyType;
import seedu.trippie.data.Trip;
import seedu.trippie.data.TrippieData;
import seedu.trippie.exception.TrippieException;
import seedu.trippie.exception.TrippieInvalidArgumentException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculateCurrencyCommandTest {

    private static final String CURRENCY_CHOICE_TO_SGD = "convert /toSGD 5";
    private static final String CURRENCY_CHOICE_TO_FOR = "convert /toFOR 5";
    private static final String CURRENCY_AMOUNT = "convert /toSGD 500";
    private static final String MISSING_CURRENCY_AMOUNT = "convert /toFOR";
    private final String[] validUserInputs = {"convert /toSGD 50", "convert /toFOR 60"};
    private final String[] badUserInputs = {"convert /toSDG 5", "convert /to", "convert /to 5", "convert /toSGD"};

    @Test
    void calculateCurrencyCommand_invalidUserInput_throwsTrippieInvalidArgumentException() {
        for (String badUserInput : badUserInputs) {
            assertThrows(TrippieInvalidArgumentException.class, () -> new CalculateCurrencyCommand(badUserInput));
        }
    }

    @Test
    void calculateCurrencyCommand_validUserInput_setCurrencyParameter() throws TrippieInvalidArgumentException {
        CurrencyType[] currencyChoice = {CurrencyType.LOCAL, CurrencyType.FOREIGN};
        Float[] currencyAmount = {50f, 60f};
        for (int i = 0; i < validUserInputs.length; i++) {
            CalculateCurrencyCommand c = new CalculateCurrencyCommand(validUserInputs[i]);
            assertEquals(currencyChoice[i], c.extractCurrencyChoice(validUserInputs[i]));
            assertEquals(currencyAmount[i], c.extractCurrencyAmount(validUserInputs[i]));
        }
    }

    @Test
    void extractCurrencyChoice_currencyChoiceIsLocalEnum_Success() throws TrippieInvalidArgumentException {
        CalculateCurrencyCommand c = new CalculateCurrencyCommand(CURRENCY_CHOICE_TO_SGD);
        assertEquals(c.extractCurrencyChoice(CURRENCY_CHOICE_TO_SGD), CurrencyType.LOCAL);
    }

    @Test
    void extractCurrencyChoice_currencyChoiceIsForeignEnum_Success() throws TrippieInvalidArgumentException {
        CalculateCurrencyCommand c = new CalculateCurrencyCommand(CURRENCY_CHOICE_TO_FOR);
        assertEquals(c.extractCurrencyChoice(CURRENCY_CHOICE_TO_FOR), CurrencyType.FOREIGN);
    }

    @Test
    void extractCurrencyAmount_currencyChoiceIsExtracted_Success() throws TrippieInvalidArgumentException {
        CalculateCurrencyCommand c = new CalculateCurrencyCommand(CURRENCY_AMOUNT);
        assertEquals(c.extractCurrencyAmount(CURRENCY_AMOUNT), 500);
    }

    @Test
    void extractCurrencyAmount_missingAmountInput_throwArrayIndexOutOfBoundsException() {
        assertThrows(TrippieInvalidArgumentException.class, () ->
                new CalculateCurrencyCommand(MISSING_CURRENCY_AMOUNT));
    }

    @Test
    void calculateCurrencyCommand_validUserInput_parsedCorrectly()
            throws TrippieException, ParseException {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TrippieData trippieData = new TrippieData(storage);
        fileSetup(storage, trippieData);

        for (int i = 0; i < validUserInputs.length; i++) {
            CalculateCurrencyCommand c = new CalculateCurrencyCommand(validUserInputs[i]);
            c.execute(ui, trippieData);
            assertEquals(10f, trippieData.getCurrentTrip()
                    .getExpenseListObject().getForExValue());
        }
    }

    private void fileSetup(Storage storage, TrippieData trippieData) throws ParseException, TrippieException {

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Trip newTrip = new Trip(trippieData.getTripList().size(), "Singapore", df.parse("11-11-2020"));
        newTrip.getExpenseListObject().setForExValue(Float.parseFloat("10"));
        newTrip.getExpenseListObject().setCurrencyAbbreviation("SGD");
        newTrip.getExpenseListObject().setBudgetValue(Float.parseFloat("1000"));
        int index = trippieData.getTripList().size();
        trippieData.getTripList().add(newTrip);
        trippieData.setCurrentTripFromIndex(index);
    }
}
