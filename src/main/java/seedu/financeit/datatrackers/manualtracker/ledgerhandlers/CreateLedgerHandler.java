package seedu.financeit.datatrackers.manualtracker.ledgerhandlers;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Common;
import seedu.financeit.common.ParamHandler;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.datatrackers.manualtracker.Ledger;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.ParamChecker;

import java.time.LocalDate;

import static seedu.financeit.utils.ParamChecker.PARAM_DATE;

/**
 * CommandHandler class to create a ledger instance, and populates it with parameter values.
 * The populated ledger will be stored within the class, and can be retrieved by calling a
 * corresponding getter method.
 */
public class CreateLedgerHandler extends ParamHandler {
    Ledger ledger;
    private static CreateLedgerHandler handler = null;

    private CreateLedgerHandler() {
        this.setRequiredParams(
            PARAM_DATE
        );
    }

    public static CreateLedgerHandler getInstance() {
        if (handler == null) {
            handler = new CreateLedgerHandler();
        }
        return handler;
    }

    public void handlePacket(CommandPacket packet)
        throws InsufficientParamsException, ItemNotFoundException {
        this.ledger = new Ledger();
        this.handleParams(packet);
    }

    @Override
    public void handleSingleParam(CommandPacket packet, String paramType) throws ParseFailParamException {
        switch (paramType) {
        case PARAM_DATE:
            LocalDate date = ParamChecker.getInstance().checkAndReturnDate(paramType);
            ledger.setDate(date);
            break;
        default:
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                ParamChecker.getInstance().getUnrecognizedParamMessage(paramType));
            break;
        }
    }

    public Ledger getCurrLedger() {
        return this.ledger;
    }
}
