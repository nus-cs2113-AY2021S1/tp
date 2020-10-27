package seedu.financeit.datatrackers.recurringtracker;
//@@author Artemis-Hunt
import seedu.financeit.common.Constants;
import seedu.financeit.data.Item;

import java.time.Month;

public class RecurringEntry extends Item {
    int day;
    String description = "";
    Constants.EntryType entryType;
    double amount;
    Month start = Month.of(1);
    Month end  = Month.of(12);
    boolean isAuto = false;
    String notes = "";


    public RecurringEntry() {
        super();
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setEntryType(Constants.EntryType entryType) {
        this.entryType = entryType;
    }

    public void setAuto(boolean isAuto) {
        this.isAuto = isAuto;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getName() {
        return String.format("Entry: [ %s ] on day [ %s ] ",
                description, day);
    }

    public int getDay() {
        return day;
    }

    public boolean equals(RecurringEntry entry) {
        if (entry == this) {
            return true;
        }

        return (this.day == entry.day)
                && (this.description.equals(entry.description))
                && (this.entryType.equals(entry.entryType))
                && (this.amount == entry.amount)
                && (this.isAuto == entry.isAuto)
                && (this.notes.equals(entry.notes));
    }


    @Override
    public String toString() {

        //One string is filled and the other is left blank, based on whether the entry is income or expenditure
        String expenditureAmount = this.entryType == Constants.EntryType.EXP ? "-$" + this.amount : "";
        String incomeAmount = this.entryType == Constants.EntryType.INC ? "+$" + this.amount : "";
        String duration;
        if (this.start.getValue() == 1 && this.end.getValue() == 12) {
            duration = "Every month";
        } else {
            duration = start + " to " + end;
        }
        String payment = this.isAuto ? "Auto deduction" : "Manual payment";
        return String.format("%s;%s;%s;%s;%s;%s;%s", this.day, this.description, expenditureAmount, incomeAmount,
                duration, payment, this.notes);
    }

}
