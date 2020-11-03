package seedu.financeit.financetools;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Common;
import seedu.financeit.common.ParamHandler;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.ParamChecker;

public class MilesCredit extends ParamHandler {

    private double amount = -1;
    private double milesRate = -1;

    public MilesCredit() {
        super();
    }

    public void handlePacket(CommandPacket packet) throws InsufficientParamsException {
        try {
            handleParams(packet);
        } catch (ItemNotFoundException exception) {
            // Fall-through
        }
    }

    public double calculateMiles() {
        assert this.amount >= 0 : "Amount should not be a negative number";
        assert this.milesRate >= 0 : "Miles rate should not be a negative number";

        return Math.round((this.amount * this.milesRate) * 100.00) / 100.00;
    }

    @Override
    public void handleSingleParam(CommandPacket packet, String paramType) throws ParseFailParamException {
        switch (paramType) {
        case "/a":
            this.amount = ParamChecker.getInstance().checkAndReturnDouble(paramType);
            break;
        case "/r":
            this.milesRate = ParamChecker.getInstance().checkAndReturnDouble(paramType);
            break;
        default:
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    ParamChecker.getInstance().getUnrecognizedParamMessage(paramType));
            break;
        }
    }
}