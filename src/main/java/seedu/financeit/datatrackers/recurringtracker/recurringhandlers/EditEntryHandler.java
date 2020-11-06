package seedu.financeit.datatrackers.recurringtracker.recurringhandlers;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Common;
import seedu.financeit.common.ParamHandler;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.datatrackers.recurringtracker.RecurringEntry;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.ParamChecker;

import java.util.Arrays;

//@@author Artemis-Hunt
/**
 * Command class to edit an existing entry instance with specified parameter values.
 * Entry to be edited must be referenced via the constructor argument.
 */
import static seedu.financeit.utils.ParamChecker.PARAM_INDEX;

public class EditEntryHandler extends ParamHandler {
    RecurringEntry recurringEntry;
    private static EditEntryHandler handler = null;

    // Function of constructor is to set required params
    // No constructor needed as there are no required params for edit entry

    public static EditEntryHandler getInstance() {
        if (handler == null) {
            handler = new EditEntryHandler();
        }
        return handler;
    }

    public void setEntry(RecurringEntry recurringEntry) {
        this.recurringEntry = recurringEntry;
    }

    public void handlePacket(CommandPacket packet)
            throws InsufficientParamsException, ItemNotFoundException {
        handleParams(packet);

        //If only param provided is /id
        if (packet.getParamTypes().size() == 1) {
            assert packet.getParam("/id") != null;
            throw new InsufficientParamsException("At least 1 param required for edit!");
        }
    }

    @Override
    public void handleSingleParam(CommandPacket packet, String paramType) throws ParseFailParamException {
        switch (paramType) {
        case ParamChecker.PARAM_DAY:
            int day = ParamChecker.getInstance().checkAndReturnDayOfMonth(paramType);
            recurringEntry.setDay(day);
            break;
        case ParamChecker.PARAM_AMOUNT:
            double amount = ParamChecker.getInstance().checkAndReturnDouble(paramType);
            recurringEntry.setAmount(amount);
            break;
        case ParamChecker.PARAM_INC:
            recurringEntry.setEntryType(Common.EntryType.INC);
            //Need to update expenditureAmount and incomeAmount, since type has changed.
            recurringEntry.convertAttributesToString();
            break;
        case ParamChecker.PARAM_EXP:
            recurringEntry.setEntryType(Common.EntryType.EXP);
            //Need to update expenditureAmount and incomeAmount, since type has changed.
            recurringEntry.convertAttributesToString();
            break;
        case ParamChecker.PARAM_DESCRIPTION:
            String description = ParamChecker.getInstance().checkAndReturnDescription(paramType);
            recurringEntry.setDescription(description);
            break;
        case ParamChecker.PARAM_AUTO:
            recurringEntry.setAuto(true);
            break;
        case ParamChecker.PARAM_NOTES:
            String notes = ParamChecker.getInstance().checkAndReturnDescription(paramType);
            recurringEntry.setNotes(notes);
            break;
        default:
            String[] ignoreParams = {
                PARAM_INDEX
            };
            if (!Arrays.asList(ignoreParams).contains(paramType)) {
                UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    ParamChecker.getInstance().getUnrecognizedParamMessage(paramType));
            }
        }
    }
}
