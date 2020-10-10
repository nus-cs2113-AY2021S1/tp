package seedu.financeit.manualtracker;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.Item;
import seedu.financeit.common.exceptions.ConflictingItemReference;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.manualtracker.subroutine.EntryList;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.ParamChecker;

import java.util.ArrayList;

public class Ledger extends Item {
    public EntryList entryList = new EntryList();
    private ParamChecker paramChecker;

    public Ledger() {
        super();
        super.requiredParams = new ArrayList<>() {
            {
                add("/date");
                add("/id");
            }
        };
        super.requiredParams.add("/id");
        super.setDefaultDateTimeFormat("date");
    }

    public Ledger(CommandPacket packet) throws AssertionError, InsufficientParamsException {
        this();
        this.paramChecker = new ParamChecker(packet);
        try {
            this.handleParams(packet);
        } catch (ItemNotFoundException | ConflictingItemReference exception) {
            // Fall-through
        }
    }

    @Override
    public String getName() {
        return String.format("Ledger %d : [ %s ]", this.index + 1, this.dateTimeManager.getDateFormatted("date"));
    }

    @Override
    public boolean equals(Object object) {
        Ledger entry = (Ledger) object;
        return (super.dateTime.equals(entry.dateTime));
    }

    @Override
    public String toString() {
        return super.getDateTimeFormatted();
    }

    @Override
    public boolean isValidItem() {
        return (this.dateTime != null);
    }

    @Override
    public void handleParam(CommandPacket packet, String paramType) throws ParseFailParamException {
        switch (paramType) {
        case ParamChecker.PARAM_DATE:
            dateTime = paramChecker.checkAndReturnDateTime(paramType, defaultDateTimeFormat);
            this.setDateTime(dateTime);
            break;

        default:
            if (!super.requiredParams.contains(paramType)) {
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    paramChecker.getUnrecognizedParamMessage(paramType));
            }
            break;
        }
    }
}
