package seedu.financeit.datatrackers.manualtracker.commands;

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
 * Command class to create a ledger instance, and populates it with parameter values.
 * The populated ledger will be stored within the class, and can be retrieved by calling a
 * corresponding getter method.
 */
public class CreateLedgerCommand extends ParamHandler {
    Ledger ledger;

    public CreateLedgerCommand(String... paramTypes) {
        this.setRequiredParams(
            PARAM_DATE
        );
    }

    public void handlePacket(CommandPacket packet) throws InsufficientParamsException {
        try {
            this.ledger = new Ledger();
            this.handleParams(packet);
        } catch (ItemNotFoundException exception) {
            // Fall-through
        }
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
