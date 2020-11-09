package seedu.financeit.datatrackers.recurringtracker;

import seedu.financeit.common.Common;
import seedu.financeit.data.Item;
import seedu.financeit.utils.DateTimeHelper;
import seedu.financeit.utils.ParamChecker;

import java.time.Month;
import java.util.Arrays;
import java.util.HashMap;

//@@author Artemis-Hunt
public class RecurringEntry extends Item {
    int day;
    String description = "";
    Common.EntryType entryType;
    double amount;
    Month start = Month.of(1);
    Month end = Month.of(12);
    boolean isAuto = false;
    String notes = "";

    //Attributes in String form, for table printing
    String expenditureAmount = null;
    String incomeAmount;
    String duration;
    String payment;


    public RecurringEntry() {
        super();
    }

    public void setAmount(double amount) {
        assert amount > 0;
        this.amount = amount;
    }

    public void setEntryType(Common.EntryType entryType) {
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

    public void setDay(int day) {
        this.day = day;
    }

    public boolean equals(Object object) {
        RecurringEntry entry = (RecurringEntry) object;
        if (entry == this) {
            return true;
        }

        return (this.day == entry.day)
                && (this.description.equals(entry.description))
                && (this.entryType.equals(entry.entryType))
                && (this.amount == entry.amount)
                && (this.isAuto == entry.isAuto);
    }

    /**
     * Get entry details necessary for printing a reminder.
     * Remember to do appropriate downcasting when accessing attributes.
     *
     * @return HashMap of details, with key being the attribute name and value
     *         being the attribute itself, upcasted to Object.
     */
    public HashMap<String, Object> getDetailsForReminder() {
        HashMap<String, Object> details = new HashMap<>();
        details.put("day", day);
        details.put("description", description);
        details.put("entryType", entryType);
        details.put("isAuto", isAuto);

        return details;
    }


    public void convertAttributesToString() {
        //One string is filled and the other is left blank, based on whether the entry is income or expenditure
        expenditureAmount = entryType == Common.EntryType.EXP ? "-$"
                + String.format("%.2f", amount) : "";
        incomeAmount = entryType == Common.EntryType.INC ? "+$"
                + String.format("%.2f", amount) : "";
        String[] monthsWithoutDay = DateTimeHelper.monthsWithoutDayOfMonth(day);
        duration = "Every month";
        if (monthsWithoutDay.length >= 1) {
            duration += " except " + String.join(",", monthsWithoutDay);
        }
        payment = isAuto ? "Auto deduction" : "Manual payment";
    }

    @Override
    public String toString() {
        if (expenditureAmount == null) {
            convertAttributesToString();
        }

        return String.format("%s;%s;%s;%s;%s;%s;%s", day, description, expenditureAmount,
                incomeAmount, duration, payment, notes);
    }

    public String toSave() {
        if (expenditureAmount == null) {
            convertAttributesToString();
        }

        return String.format("%s>&@#<%s>&@#<%s>&@#<%s>&@#<%s>&@#<%s>&@#<%s", day, description,
                expenditureAmount, incomeAmount, duration, payment, notes);
    }

}
