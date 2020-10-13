package seedu.financeit.financetools;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.ParamHandler;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.ParamChecker;

public class Cashback extends ParamHandler {

    private double amount = -1;
    private double cashbackRate = -1;
    private double monthlyCap = -1;

    public Cashback() {
        super();
    }

    public void handlePacket(CommandPacket packet) throws InsufficientParamsException {
        this.paramChecker = new ParamChecker(packet);
        try {
            this.handleParams(packet);
        } catch (ItemNotFoundException exception) {
            // Fall-through
        }
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setCashbackRate(Double cashbackRate) {
        this.cashbackRate = cashbackRate;
    }

    public double calculateCashback() {
        Double cashbackEarned = this.amount * (this.cashbackRate / 100);
        if (cashbackEarned > this.monthlyCap) {
            cashbackEarned = this.monthlyCap;
        }
        return cashbackEarned;
    }

    @Override
    public void handleSingleParam(CommandPacket packet, String paramType) throws ParseFailParamException {
        switch (paramType) {
        case "/amount":
            this.amount = paramChecker.checkAndReturnDouble(paramType);
            break;
        case "/cashback":
            this.cashbackRate = paramChecker.checkAndReturnDouble(paramType);
            break;
        case "/cap":
            this.monthlyCap = paramChecker.checkAndReturnDouble(paramType);
            break;
        default:
            if (!super.requiredParams.contains(paramType)) {
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                        paramChecker.getUnrecognizedParamMessage(paramType));
            }
            break;
        }
    }
}
