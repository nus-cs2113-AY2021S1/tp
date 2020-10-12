package seedu.financeit.manualtracker.subroutine;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.DateTimeItem;
import seedu.financeit.common.exceptions.ConflictingItemReference;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.manualtracker.Ledger;
import seedu.financeit.ui.UiManager;

import java.time.LocalTime;
import java.util.ArrayList;

import static seedu.financeit.utils.ParamChecker.PARAM_AMOUNT;
import static seedu.financeit.utils.ParamChecker.PARAM_CATEGORY;
import static seedu.financeit.utils.ParamChecker.PARAM_DESCRIPTION;
import static seedu.financeit.utils.ParamChecker.PARAM_EXP;
import static seedu.financeit.utils.ParamChecker.PARAM_INC;
import static seedu.financeit.utils.ParamChecker.PARAM_TIME;

public class Entry extends DateTimeItem {
    private String description = " ";
    private String category = null;
    private Constants.EntryType entryType = null;
    private double amount = -1;
    private Ledger ledger = null;

    public Entry() {
        super();
        super.requiredParams = new ArrayList<>() {
            {
                add("/time");
                add("/cat");
                add("/amt");
                add("-i");
                add("-e");
                add("/id");
                add("/desc");
            }
        };
        super.setDefaultDateTimeFormat("time");
    }

    public Entry(CommandPacket packet) throws AssertionError, InsufficientParamsException {
        this();
        try {
            this.handleParams(packet);
        } catch (ItemNotFoundException | ConflictingItemReference exception) {
            // Fall-through
        }
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
        this.category = Constants.categoryMap.get(category);
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

    @Override
    public void handleSingleParam(CommandPacket packet, String paramType) throws ParseFailParamException {
        switch (paramType) {
        case PARAM_TIME:
            LocalTime time = paramChecker.checkAndReturnTime(paramType);
            super.setTime(time);
            this.parseSuccessParams.add(paramType);
            break;
        case PARAM_AMOUNT:
            Double amount = paramChecker.checkAndReturnDouble(paramType);
            this.setAmount(amount);
            this.parseSuccessParams.add(paramType);
            break;
        case PARAM_INC:
            this.setEntryType(Constants.EntryType.INC);
            this.parseSuccessParams.add(paramType);
            break;
        case PARAM_EXP:
            this.setEntryType(Constants.EntryType.EXP);
            this.parseSuccessParams.add(paramType);
            break;
        case PARAM_DESCRIPTION:
            this.setDescription(packet.getParam(paramType));
            this.parseSuccessParams.add(paramType);
            break;
        case PARAM_CATEGORY:
            String category = paramChecker.checkAndReturnCategory(paramType);
            this.setCategory(category);
            this.parseSuccessParams.add(paramType);
            break;
        default:
            if (!super.requiredParams.contains(paramType)) {
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    paramChecker.getUnrecognizedParamMessage(paramType));
            }
            break;
        }
    }

    @Override
    public String getName() {
        return String.format("Entry %d : [ %s ] [ %s ]", this.getIndex() + 1,
            this.dateTimeManager.getSingleTimeFormatted("time"), this.description);
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

    @Override
    public boolean isValidItem() {
        return (this.category != null)
            && (this.entryType != null)
            && (this.time != null)
            && (this.amount != -1);
    }
}
