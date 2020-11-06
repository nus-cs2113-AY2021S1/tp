package seedu.financeit.datatrackers.manualtracker.ledgerhandlers;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Common;
import seedu.financeit.common.ParamHandler;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.datatrackers.manualtracker.LedgerList;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.ParamChecker;

import java.time.LocalDate;

import static seedu.financeit.utils.ParamChecker.PARAM_DATE;
import static seedu.financeit.utils.ParamChecker.PARAM_INDEX;

/**
 * Command class to create an entry instance, and populates it with parameter values.
 * The populated entry will be stored within the class, and can be retrieved by calling a
 * corresponding getter method.
 */
public class RetrieveLedgerHandler extends ParamHandler {
    LedgerList ledgerList;
    private static RetrieveLedgerHandler handler = null;

    private RetrieveLedgerHandler() {
        this.setRequiredParams(
            PARAM_DATE + " or " + PARAM_INDEX
        );
    }

    public static RetrieveLedgerHandler getInstance() {
        if (handler == null) {
            handler = new RetrieveLedgerHandler();
        }
        return handler;
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
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                ParamChecker.getInstance().getUnrecognizedParamMessage(paramType));
            break;
        }
    }
}
