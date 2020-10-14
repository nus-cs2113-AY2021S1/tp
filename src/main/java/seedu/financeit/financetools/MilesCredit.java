package seedu.financeit.financetools;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.ParamHandler;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.ui.UiManager;

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

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setMilesRate(Double milesRate) {
        this.milesRate = milesRate;
    }

    public double calculateMiles() {
        assert this.amount >= 0 : "Amount should not be a negative number";
        assert this.milesRate >= 0 : "Miles rate should not be a negative number";

        return this.amount * this.milesRate;
    }

    @Override
    public void handleSingleParam(CommandPacket packet, String paramType) throws ParseFailParamException,
            ItemNotFoundException {
        switch (paramType) {
        case "/amount":
            this.amount = paramChecker.checkAndReturnDouble(paramType);
            break;
        case "/miles":
            this.milesRate = paramChecker.checkAndReturnDouble(paramType);
            break;
        default:
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    paramChecker.getUnrecognizedParamMessage(paramType));
            break;
        }
    }
}