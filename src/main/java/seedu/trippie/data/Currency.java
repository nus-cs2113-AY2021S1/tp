package seedu.trippie.data;

/**
 * Represents currency object to be converted into the requested currency.
 * Stores the choice of currency to be converted to and the amount.
 */
public class Currency {
    private final CurrencyType currencyChoice;
    private final Float currencyAmount;

    public Currency(CurrencyType currencyChoice, Float currencyAmount) {
        this.currencyChoice = currencyChoice;
        this.currencyAmount = currencyAmount;
    }

    public String getCurrencyChoice() {
        String currencyChosen;
        if (currencyChoice == CurrencyType.LOCAL) {
            currencyChosen = "local";
        } else {
            currencyChosen = "foreign";
        }
        return currencyChosen;
    }

    public Float getCurrencyAmount() {
        return currencyAmount;
    }
}
