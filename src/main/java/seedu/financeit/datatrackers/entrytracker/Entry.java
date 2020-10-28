package seedu.financeit.datatrackers.entrytracker;

import seedu.financeit.common.CategoryMap;
import seedu.financeit.common.Constants;
import seedu.financeit.data.DateTimeItem;
import seedu.financeit.datatrackers.manualtracker.Ledger;

public class Entry extends DateTimeItem {
    private String description = " ";
    private String category = null;
    private Constants.EntryType entryType = null;
    private double amount = -1;
    // Allows the entry to be have access to the date of its conception from its "parent" ledger.
    private Ledger ledger = null;

    public Entry() {
        super();
        super.setDefaultDateTimeFormat("time");
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

    public void setEntryType(Constants.EntryType entryType) {
        this.entryType = entryType;
    }

    public Constants.EntryType getEntryType() {
        return this.entryType;
    }

    private String retrieveLastWord(String input) {
        input = input.replaceAll(">", " ");
        String[] tokens = input.split(" ");
        return tokens[tokens.length - 1];
    }

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
            this.dateTimeOutputManager.getSingleTimeFormatted("time"),
            this.getShortFormDesc(this.description));
    }

    @Override
    public String toString() {
        return String.format("%s;%s;%s;%s;%s", this.entryType, this.category, this.amount,
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
