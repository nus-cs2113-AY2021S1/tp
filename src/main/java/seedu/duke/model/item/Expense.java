package seedu.duke.model.item;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

// @@author GuoAi

/**
 * Represents an expense item.
 */
public class Expense extends Item {

    private final double value;
    private String currency;
    private LocalDate date;
    private LocalDate now = LocalDate.now();

    /**
     * Constructor used when adding an expense item.
     * Byt default, the expense item has currency "SGD and date of today.
     *
     * @param description the description of the expense item
     * @param value the value of the expense item
     */
    public Expense(String description, double value) {
        super(description);
        this.value = value;
        this.currency = "SGD";
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
}
