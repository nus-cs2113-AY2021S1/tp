package seedu.financeit.datatrackers.entrytracker.entryhandlers;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Common;
import seedu.financeit.common.ParamHandler;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.datatrackers.entrytracker.EntryList;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.ParamChecker;

import java.util.Arrays;

import static seedu.financeit.utils.ParamChecker.PARAM_AMOUNT;
import static seedu.financeit.utils.ParamChecker.PARAM_CATEGORY;
import static seedu.financeit.utils.ParamChecker.PARAM_DESCRIPTION;
import static seedu.financeit.utils.ParamChecker.PARAM_EXP;
import static seedu.financeit.utils.ParamChecker.PARAM_INC;
import static seedu.financeit.utils.ParamChecker.PARAM_TIME;
import static seedu.financeit.utils.ParamChecker.PARAM_INDEX;

/**
 * Command class to reference an existing entry instance with specified parameter values.
 * Ledger will then be referenced for the ItemList instance as currItem.
 */
public class RetrieveEntryHandler extends ParamHandler {
    EntryList entryList;
    private static RetrieveEntryHandler handler = null;

    private RetrieveEntryHandler() {
        this.setRequiredParams(
            PARAM_INDEX
        );
    }

    public static RetrieveEntryHandler getInstance() {
        if (handler == null) {
            handler = new RetrieveEntryHandler();
        }
        return handler;
    }

    public void handlePacket(CommandPacket packet, EntryList entryList)
        throws InsufficientParamsException, ItemNotFoundException {
        this.entryList = entryList;
        this.handleParams(packet);
    }

    @Override
    public void handleSingleParam(CommandPacket packet, String paramType)
        throws ParseFailParamException, ItemNotFoundException {
        switch (paramType) {
        //RetrieveEntryHandler is only concerned with index of entry.
        case ParamChecker.PARAM_INDEX:
            int index = ParamChecker.getInstance().checkAndReturnIndex(paramType, this.entryList.getItems());
            this.entryList.setIndexToModify(index);
            return;
        default:
            //Ignore other params, as those will be handled by EditEntryHandler
            String[] ignoreParams = {
                PARAM_AMOUNT,
                PARAM_CATEGORY,
                PARAM_DESCRIPTION,
                PARAM_EXP,
                PARAM_INC,
                PARAM_TIME,
            };

            if (!Arrays.asList(ignoreParams).contains(paramType)) {
                UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    ParamChecker.getInstance().getUnrecognizedParamMessage(paramType));
            }
            break;
        }
    }
}
