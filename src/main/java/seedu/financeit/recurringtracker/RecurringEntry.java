package seedu.financeit.recurringtracker;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.Item;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.ParamChecker;

import java.time.Month;

public class RecurringEntry extends Item {
    int day;
    String description = "";
    Constants.EntryType entryType;
    double amount;
    Month start = Month.of(1);
    Month end  = Month.of(12);
    boolean auto = false;
    String notes = "";


    public RecurringEntry() {
        super();
    }

    public void handlePacket(CommandPacket packet) throws InsufficientParamsException {
        try {
            super.handleParams(packet);
        } catch (ItemNotFoundException exception) {
            // Fall-through
        }
    }

    @Override
    public void handleSingleParam(CommandPacket packet, String paramType) throws ParseFailParamException {
        switch (paramType) {
        case ParamChecker.PARAM_DAY:
            day = paramChecker.checkAndReturnInt(paramType);
            break;
        case ParamChecker.PARAM_AMOUNT:
            amount = paramChecker.checkAndReturnDouble(paramType);
            break;
        case ParamChecker.PARAM_INC:
            entryType = Constants.EntryType.INC;
            break;
        case ParamChecker.PARAM_EXP:
            entryType = Constants.EntryType.EXP;
            break;
        case ParamChecker.PARAM_DESCRIPTION:
            description = packet.getParam(paramType);
            break;
        case ParamChecker.PARAM_AUTO:
            auto = true;
            break;
        case ParamChecker.PARAM_NOTES:
            notes = packet.getParam(paramType);
            break;
        default:
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    paramChecker.getUnrecognizedParamMessage(paramType));
        }
    }

    @Override
    public String getName() {
        return String.format("Entry: [ %s ] on day [ %s ] ",
                description, day);
    }

    public boolean getAuto() {
        return auto;
    }

    public String getDescription() {
        return description;
    }

    public int getDay() {
        return day;
    }

    public Constants.EntryType getEntryType() {
        return entryType;
    }
    public boolean equals(RecurringEntry entry) {
        if (entry == this) {
            return true;
        }

        return (this.day == entry.day)
                && (this.description.equals(entry.description))
                && (this.entryType.equals(entry.entryType))
                && (this.amount == entry.amount)
                && (this.auto == entry.auto)
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
        String payment = this.auto ? "Auto deduction" : "Manual payment";
        return String.format("%s;%s;%s;%s;%s;%s;%s", this.day, this.description, expenditureAmount, incomeAmount,
                duration, payment, this.notes);
    }

}
