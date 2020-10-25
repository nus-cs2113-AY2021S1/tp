package seedu.financeit.financetools;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.ParamHandler;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.ParamChecker;

public class SimpleInterest extends ParamHandler {

    private double amount = -1;
    private double interestRate = -1;

    public SimpleInterest() {
        super();
    }

    public void handlePacket(CommandPacket packet) throws InsufficientParamsException {
        try {
            this.handleParams(packet);
        } catch (ItemNotFoundException exception) {
            // Fall-through
        }
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public double calculateSimpleInterest() {
        assert this.amount >= 0 : "Amount should not be a negative number";
        assert this.interestRate >= 0 : "Interest rate should not be a negative number";

        return Math.round((this.amount * (this.interestRate / 100)) * 100.00) / 100.00;
    }

    @Override
    public void handleSingleParam(CommandPacket packet, String paramType) throws ParseFailParamException,
            ItemNotFoundException {
        switch (paramType) {
        case "/a":
            this.amount = ParamChecker.getInstance().checkAndReturnDouble(paramType);
            break;
        case "/r":
            this.interestRate = ParamChecker.getInstance().checkAndReturnDouble(paramType);
            break;
        default:
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                ParamChecker.getInstance().getUnrecognizedParamMessage(paramType));
            break;
        }
    }
}