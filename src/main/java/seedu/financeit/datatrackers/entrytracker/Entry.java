package seedu.financeit.datatrackers.entrytracker;

import seedu.financeit.common.CategoryMap;
import seedu.financeit.common.Common;
import seedu.financeit.data.DateTimeItem;
import seedu.financeit.datatrackers.manualtracker.Ledger;

/**
 * Item class which represents transactions of the users.
 */
public class Entry extends DateTimeItem {
    private String description = " ";
    private String category = null;
    private Common.EntryType entryType = null;
    private double amount = -1;
    // Allows the entry to be have access to the date of its conception from its "parent" ledger.
    private Ledger ledger = null;

    public Entry() {
        super();
        super.setDefaultDateTimeFormat("");
    }

    public Entry(Entry entry) {
        this();
        this.category = entry.category;
        this.description = entry.description;
        this.amount = entry.amount;
        this.entryType = entry.entryType;
        this.ledger = entry.ledger;
        this.setTime(entry.time);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setLedger(Ledger ledger) {
        this.ledger = ledger;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setCategory(String category) {
        this.category = CategoryMap.getCategoryFromInput(category);
    }

    public String getCategory() {
        return this.category;
    }

    public void setEntryType(Common.EntryType entryType) {

        this.entryType = entryType;
    }

    public Common.EntryType getEntryType() {
        return this.entryType;
    }

    private String retrieveLastWord(String input) {
        input = input.replaceAll(">", " ");
        String[] tokens = input.split(" ");
        return tokens[tokens.length - 1];
    }

    /**
     * In certain system messages, referencing of entry type by full description can be
     * aesthetically unpleasing.
     * This method shortens the description printed.
     * @param description Full description of entry.
     * @return Shortened description of entry.
     */
    private String getShortFormDesc(String description) {
        int maxDescLength = 20;
        maxDescLength = Math.min(maxDescLength, description.length());
        String shortFormDescription = description.substring(0, maxDescLength);
        if (description.length() > maxDescLength) {
            shortFormDescription += "... ";
            shortFormDescription += retrieveLastWord(description);
        }
        return shortFormDescription;
    }

    @Override
    public String getName() {
        return String.format("Entry %d : [ %s ] [ %s ]", this.getIndex() + 1,
            this.dateTimeOutputManager.getSingleTimeFormatted(""),
            this.getShortFormDesc(this.description));
    }

    @Override
    public String toString() {
        return String.format("%s;%s;$%.2f;%s;%s", this.entryType, this.category, this.amount,
            this.getTimeFormatted(), this.description);
    }

    @Override
    public boolean equals(Object object) {
        Entry entry = (Entry) object;
        return (this.description.equals(entry.description))
            && (this.category.equals(entry.category))
            && (this.entryType.equals(entry.entryType))
            && (this.time.equals(entry.time))
            && (this.amount == entry.amount);
    }
}
