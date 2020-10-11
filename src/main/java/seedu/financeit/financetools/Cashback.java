package seedu.financeit.financetools;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.ParamHandler;
import seedu.financeit.common.exceptions.ConflictingItemReference;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.ui.UiManager;

import java.util.ArrayList;

public class Cashback extends ParamHandler {

    private double amount = -1;
    private double cashbackRate = -1;
    private double monthlyCap = -1;

    public Cashback() {
        super();
        super.requiredParams = new ArrayList<>() {
            {
                add("/amount");
                add("/cashback");
                add("/cap");
            }
        };
    }

    public Cashback(CommandPacket packet) throws InsufficientParamsException {
        this();
        try {
            handleParams(packet);
        } catch (ItemNotFoundException | ConflictingItemReference exception) {
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
    public boolean isValidItem() {
        return ((amount != -1) && (cashbackRate != -1) && (monthlyCap != - 1));
    }

    @Override
    public void handleSingleParam(CommandPacket packet, String paramType) throws ParseFailParamException,
            ItemNotFoundException, ConflictingItemReference {
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
