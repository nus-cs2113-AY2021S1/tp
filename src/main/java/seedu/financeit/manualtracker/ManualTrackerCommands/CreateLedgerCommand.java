package seedu.financeit.manualtracker.ManualTrackerCommands;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.ParamHandler;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.manualtracker.Ledger;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.ParamChecker;

import java.time.LocalDate;

public class CreateLedgerCommand extends ParamHandler {
    Ledger ledger;

    public CreateLedgerCommand(String... paramTypes) {
        this.setRequiredParams(paramTypes);
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
        case ParamChecker.PARAM_DATE:
            LocalDate date = ParamChecker.getInstance().checkAndReturnDate(paramType);
            ledger.setDate(date);
            break;
        default:
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                ParamChecker.getInstance().getUnrecognizedParamMessage(paramType));
            break;
        }
    }

    public Ledger getCurrLedger() {
        return this.ledger;
    }
}
