package seedu.financeit.manualtracker.ManualTrackerCommands;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.ParamHandler;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.manualtracker.LedgerList;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.ParamChecker;

import java.time.LocalDate;

public class RetrieveLedgerCommand extends ParamHandler {
    LedgerList ledgerList;

    public RetrieveLedgerCommand(String... params) {
        this.setRequiredParams(params);
    }

    public void handlePacket(CommandPacket packet, LedgerList ledgerList)
        throws InsufficientParamsException, ItemNotFoundException {
        this.ledgerList = ledgerList;
        this.handleParams(packet);
    }

    @Override
    public void handleSingleParam(CommandPacket packet, String paramType)
        throws ParseFailParamException, ItemNotFoundException {
        int index;
        switch (paramType) {
        case ParamChecker.PARAM_DATE:
            LocalDate date = ParamChecker.getInstance().checkAndReturnDate(paramType);
            index = this.ledgerList.getIndexFromDate(date);
            this.ledgerList.setIndexToModify(index);
            return;
        case ParamChecker.PARAM_INDEX:
            index = ParamChecker.getInstance().checkAndReturnIndex(paramType, this.ledgerList.getItems());
            this.ledgerList.setIndexToModify(index);
            return;
        default:
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                ParamChecker.getInstance().getUnrecognizedParamMessage(paramType));
            break;
        }
    }
}
