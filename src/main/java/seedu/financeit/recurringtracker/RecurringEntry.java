package seedu.financeit.recurringtracker;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.Item;
import seedu.financeit.common.exceptions.ConflictingItemReference;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.ui.UiManager;

import java.util.ArrayList;

import static seedu.financeit.utils.ParamChecker.PARAM_AMOUNT;
import static seedu.financeit.utils.ParamChecker.PARAM_AUTO;
import static seedu.financeit.utils.ParamChecker.PARAM_DAY;
import static seedu.financeit.utils.ParamChecker.PARAM_DESCRIPTION;
import static seedu.financeit.utils.ParamChecker.PARAM_EXP;
import static seedu.financeit.utils.ParamChecker.PARAM_INC;

public class RecurringEntry extends Item {
    private int day;
    private String description;
    private double amount;
    private Constants.EntryType entryType;
    private String notes;
    private boolean auto = false;

    public RecurringEntry() {
        requiredParams = new ArrayList<>() {
            {
                add("/day");
                add("/desc");
                add("/amt");
            }
        };
    }

    public RecurringEntry(CommandPacket packet) throws AssertionError, InsufficientParamsException {
        try {
            handleParams(packet);
        } catch (ItemNotFoundException | ConflictingItemReference exception) {
            // Fall-through
        }
    }

    @Override
    public void handleSingleParam(CommandPacket packet, String paramType) throws ParseFailParamException {
        boolean validParam = true;
        switch (paramType) {
        case PARAM_DAY:
            day = paramChecker.checkAndReturnInt(paramType);
            break;
        case PARAM_AMOUNT:
            amount = paramChecker.checkAndReturnInt(paramType);
            break;
        case PARAM_INC:
            entryType = Constants.EntryType.INC;
            break;
        case PARAM_EXP:
            entryType = Constants.EntryType.EXP;
            break;
        case PARAM_DESCRIPTION:
            description = packet.getParam(paramType);
            break;
        case PARAM_AUTO:
            auto = true;
            break;
        default:
            validParam = false;
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    paramChecker.getUnrecognizedParamMessage(paramType));
        }
        if (validParam) {
            parseSuccessParams.add(paramType);
        }
    }

    @Override
    public String getName() {
        return String.format("Entry: [ %s ] [ %s ]",
                day, description);
    }

    @Override
    public boolean isValidItem() {
        return (entryType != null)
                && (this.amount != -1);
    }
}
