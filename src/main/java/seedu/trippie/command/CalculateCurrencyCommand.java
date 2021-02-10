package seedu.trippie.command;

import seedu.trippie.Ui;
import seedu.trippie.data.Currency;
import seedu.trippie.data.CurrencyType;
import seedu.trippie.data.TrippieData;
import seedu.trippie.exception.TrippieInvalidArgumentException;


public class CalculateCurrencyCommand extends Command {

    private static final String FORMAT_ERROR_MESSAGE = "Incorrect format for [convert] command. "
            + "Please try the following:\n"
            + "Format: convert /toSGD AMOUNT_IN_FOREIGN_CURRENCY\n"
            + "Format: convert /toFOR AMOUNT_IN_SGD\n"
            + "Example: convert /toSGD 500\n"
            + "Example: convert /toFOR 500";
    private static final String NEGATIVE_VALUE_MESSAGE = "Trippie doesn't know how to deal with negative values";

    private final CurrencyType currencyChoice;
    private final Float currencyAmount;

    /**
     * Takes in the convert input command from the user.
     * Identifies and set the choice of conversion (local or foreign) and the amount to be converted into
     * currencyChoice and currencyAmount respectively.
     *
     * @param userInput Command input by the user.
     * @throws TrippieInvalidArgumentException if input has incorrect format.
     */
    public CalculateCurrencyCommand(String userInput) throws TrippieInvalidArgumentException {
        try {
            this.currencyChoice = extractCurrencyChoice(userInput);
            this.currencyAmount = extractCurrencyAmount(userInput);
        } catch (TrippieInvalidArgumentException e) {
            throw new TrippieInvalidArgumentException(FORMAT_ERROR_MESSAGE);
        }
    }

    /**
     * Extracts the choice of conversion from the command inputted by the user.
     *
     * @param userInput Command input by the user.
     * @return Input with only the choice of currency to be converted.
     * @throws TrippieInvalidArgumentException if choice inputted by user has incorrect format
     */
    public CurrencyType extractCurrencyChoice(String userInput) throws TrippieInvalidArgumentException {
        try {
            String withoutCommand = userInput.split(" /to")[1];
            String currencyChoice = withoutCommand.split(" ")[0];
            CurrencyType currencyType;
            if (currencyChoice.equals("SGD")) {
                currencyType = CurrencyType.LOCAL;
            } else if (currencyChoice.equals("FOR")) {
                currencyType = CurrencyType.FOREIGN;
            } else {
                throw new TrippieInvalidArgumentException(FORMAT_ERROR_MESSAGE);
            }
            return currencyType;
        } catch (IndexOutOfBoundsException e) {
            throw new TrippieInvalidArgumentException(FORMAT_ERROR_MESSAGE);
        }
    }

    /**
     * Extract the amount to be converted from the command inputted by the user.
     *
     * @param userInput Command input by the user.
     * @return Input without the currency amount
     * @throws TrippieInvalidArgumentException if amount inputted is negative or has incorrect format
     */
    public Float extractCurrencyAmount(String userInput) throws TrippieInvalidArgumentException {
        try {
            if (userInput.split(" ")[2] == null) { //account for missing input
                throw new ArrayIndexOutOfBoundsException();
            } else {
                String inputWithoutChoice = userInput.split(" ")[2];
                if (Float.parseFloat(inputWithoutChoice) < 0) {
                    throw new TrippieInvalidArgumentException(NEGATIVE_VALUE_MESSAGE);
                }
                return Float.parseFloat(inputWithoutChoice);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TrippieInvalidArgumentException(FORMAT_ERROR_MESSAGE);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Prints amount after conversion from either local to foreign or visa versa.
     *
     * @param ui User Interface of the program.
     * @param trippieData Current trip currency foreign exchange rate and foreign currency abbreviation.
     */
    @Override
    public void execute(Ui ui, TrippieData trippieData) {
        float exchangeRate = trippieData.getCurrentTrip().getExpenseListObject().getForExValue();
        String currencyAbbreviation = trippieData.getCurrentTrip().getExpenseListObject().getCurrencyAbbreviation();
        Currency currencyEntry = new Currency(currencyChoice, currencyAmount);
        float initialInputAmount = currencyEntry.getCurrencyAmount();
        float finalOutputAmount;
        String finalOutputAbbreviation;
        System.out.println("Processing... Please Wait.");
        if (currencyEntry.getCurrencyChoice().equals("local")) {
            finalOutputAmount = initialInputAmount / exchangeRate;
            finalOutputAbbreviation = "SGD";
        } else {
            finalOutputAmount = initialInputAmount * exchangeRate;
            finalOutputAbbreviation = currencyAbbreviation;
        }
        System.out.println("That amount in your " + currencyEntry.getCurrencyChoice()
                + " currency is " + String.format("%.2f ", finalOutputAmount)
                + finalOutputAbbreviation + ".");
    }
}
