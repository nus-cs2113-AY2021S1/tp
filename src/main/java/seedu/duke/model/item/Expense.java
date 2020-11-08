// @@author GuoAi

package seedu.duke.model.item;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

// @@author GuoAi

/**
 * Represents an expense item.
 */
public class Expense extends Item implements Comparable<Expense> {

    private final double value;
    private String currency;
    private LocalDate date;
    private LocalDate now;

    /**
     * Constructor used when adding an expense item.
     * By default, the expense item has currency "SGD and date of today.
     *
     * @param description the description of the expense item
     * @param value the value of the expense item
     */
    public Expense(String description, double value) {
        super(description);
        this.value = value;
        this.currency = "SGD";
        now = LocalDate.now();
        this.date = now;
    }

    @Override
    public String toString() {
        return String.format("%s (%.2f %s) (date: %s)", getDescription(), getValue(), getCurrency(),
                getDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
    }

    public String getDescription() {
        return description;
    }

    public double getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Converts the attributes of the expense item into a formatted string to be saved into the storage file.
     *
     * @return the formatted string to be saved into the storage file
     */
    @Override
    public String toFile() {
        return getDescription() + " | " + getValue() + " | " + getCurrency() + " | " + getDate().toString();
    }

    /**
     * Defines how expense items are sorted. Firstly sort expense items based on date in descending order (i.e. the
     * latest expense items are shown first). If two expense items have the same date, sort them based on currency
     * lexicographically.
     *
     * @param otherExpense The other expense item to compare to
     * @return negative integer if this expense item precedes the argument expense item, positive integer if this
     *     expense item follows the argument expense item, 0 otherwise.
     */
    @Override
    public int compareTo(Expense otherExpense) {
        if (this.date.compareTo(otherExpense.getDate()) != 0) {
            return -this.date.compareTo(otherExpense.getDate());
        } else {
            return this.currency.compareTo(otherExpense.getCurrency());
        }
    }
}
