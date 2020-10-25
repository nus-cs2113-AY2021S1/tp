package seedu.financeit.datatrackers.entrytracker.EntryCommands;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.ParamHandler;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.datatrackers.entrytracker.EntryList;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.ParamChecker;

import java.util.Arrays;

import static seedu.financeit.utils.ParamChecker.*;
import static seedu.financeit.utils.ParamChecker.PARAM_EXP;

/**
 * Command class to reference an existing entry instance with specified parameter values.
 * Ledger will then be referenced for the ItemList instance as currItem.
 */
public class RetrieveEntryCommand extends ParamHandler {
    EntryList entryList;

    public RetrieveEntryCommand(String... params) {
        this.setRequiredParams(
            PARAM_INDEX
        );
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
        case ParamChecker.PARAM_INDEX:
            int index = ParamChecker.getInstance().checkAndReturnIndex(paramType, this.entryList.getItems());
            this.entryList.setIndexToModify(index);
            return;
        default:
            String[] ignoreParams = {
                PARAM_TIME,
                PARAM_DESCRIPTION,
                PARAM_CATEGORY,
                PARAM_AMOUNT,
                PARAM_INC + " or " + PARAM_EXP
            };
            if (! Arrays.asList(ignoreParams).contains(paramType)) {
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    ParamChecker.getInstance().getUnrecognizedParamMessage(paramType));
            }
            break;
        }
    }
}
