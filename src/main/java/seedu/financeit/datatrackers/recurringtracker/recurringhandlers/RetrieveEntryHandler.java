package seedu.financeit.datatrackers.recurringtracker.recurringhandlers;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Common;
import seedu.financeit.common.ParamHandler;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.datatrackers.recurringtracker.RecurringEntryList;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.ParamChecker;

import java.util.Arrays;

import static seedu.financeit.utils.ParamChecker.PARAM_AMOUNT;
import static seedu.financeit.utils.ParamChecker.PARAM_AUTO;
import static seedu.financeit.utils.ParamChecker.PARAM_DAY;
import static seedu.financeit.utils.ParamChecker.PARAM_DESCRIPTION;
import static seedu.financeit.utils.ParamChecker.PARAM_EXP;
import static seedu.financeit.utils.ParamChecker.PARAM_INC;
import static seedu.financeit.utils.ParamChecker.PARAM_INDEX;

//@@author Artemis-Hunt
/**
 * Command class to reference an existing entry instance with specified parameter values.
 * Entry will then be referenced for the ItemList instance as currItem.
 */
public class RetrieveEntryHandler extends ParamHandler {
    RecurringEntryList entryList;
    private static RetrieveEntryHandler handler = null;

    private RetrieveEntryHandler() {
        setRequiredParams(
            PARAM_INDEX
        );
    }

    public static RetrieveEntryHandler getInstance() {
        if (handler == null) {
            handler = new RetrieveEntryHandler();
        }
        return handler;
    }

    public void handlePacket(CommandPacket packet, RecurringEntryList entryList)
        throws InsufficientParamsException, ItemNotFoundException {
        this.entryList = entryList;
        handleParams(packet);
    }

    @Override
    public void handleSingleParam(CommandPacket packet, String paramType)
        throws ParseFailParamException {
        switch (paramType) {
        //RetrieveEntryHandler is only concerned with index of entry.
        case ParamChecker.PARAM_INDEX:
            int index = ParamChecker.getInstance().checkAndReturnIndex(paramType, entryList.getItems());
            entryList.setIndexToModify(index);
            return;
        default:
            //Ignore other params, as those will be handled by EditEntryHandler
            String[] ignoreParams = {
                PARAM_DAY,
                PARAM_AUTO,
                PARAM_AMOUNT,
                PARAM_DESCRIPTION,
                PARAM_EXP,
                PARAM_INC
            };
            if (!Arrays.asList(ignoreParams).contains(paramType)) {
                UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    ParamChecker.getInstance().getUnrecognizedParamMessage(paramType));
            }
            break;
        }
    }
}
