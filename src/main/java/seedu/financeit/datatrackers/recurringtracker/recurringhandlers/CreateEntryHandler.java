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

import static seedu.financeit.utils.ParamChecker.PARAM_AMOUNT;
import static seedu.financeit.utils.ParamChecker.PARAM_DAY;
import static seedu.financeit.utils.ParamChecker.PARAM_DESCRIPTION;
import static seedu.financeit.utils.ParamChecker.PARAM_EXP;
import static seedu.financeit.utils.ParamChecker.PARAM_INC;

//@@author Artemis-Hunt
/**
 * Command class to create an entry instance, and populates it with parameter values.
 * The populated entry will be stored within the class, and can be retrieved by calling a
 * corresponding getter method.
 */
public class CreateEntryHandler extends ParamHandler {
    RecurringEntry recurringEntry;
    private static CreateEntryHandler handler = null;

    private CreateEntryHandler() {
        setRequiredParams(
            PARAM_DAY,
            PARAM_DESCRIPTION,
            PARAM_AMOUNT,
            PARAM_INC + " or " + PARAM_EXP
        );
    }

    public static CreateEntryHandler getInstance() {
        if (handler == null) {
            handler = new CreateEntryHandler();
        }
        return handler;
    }

    public void handlePacket(CommandPacket packet)
            throws InsufficientParamsException, ItemNotFoundException {
        recurringEntry = new RecurringEntry();
        handleParams(packet);
    }

    @Override
    public void handleSingleParam(CommandPacket packet, String paramType) throws ParseFailParamException {
        switch (paramType) {
        case ParamChecker.PARAM_DAY:
            int day = ParamChecker.getInstance().checkAndReturnDayOfMonth(paramType);
            recurringEntry.setDay(day);
            break;
        case ParamChecker.PARAM_AMOUNT:
            double amount = ParamChecker.getInstance().checkAndReturnDoubleSigned(paramType);
            recurringEntry.setAmount(amount);
            break;
        case ParamChecker.PARAM_INC:
            recurringEntry.setEntryType(Common.EntryType.INC);
            break;
        case ParamChecker.PARAM_EXP:
            recurringEntry.setEntryType(Common.EntryType.EXP);
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
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                ParamChecker.getInstance().getUnrecognizedParamMessage(paramType));
        }
    }

    public RecurringEntry getCurrEntry() {
        return recurringEntry;
    }
}
