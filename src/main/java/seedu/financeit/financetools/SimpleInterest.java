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

public class SimpleInterest extends ParamHandler {

    private double amount = -1;
    private double interestRate = -1;

    public SimpleInterest() {
        super();
        super.requiredParams = new ArrayList<>() {
            {
                add("/amount");
                add("/ir");
            }
        };
    }

    public SimpleInterest(CommandPacket packet) throws InsufficientParamsException {
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

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public double calculateSimpleInterest() {
        return this.amount * (this.interestRate / 100);
    }

    @Override
    public boolean isValidItem() {
        return ((amount != -1) && (interestRate != -1));
    }

    @Override
    public void handleSingleParam(CommandPacket packet, String paramType) throws ParseFailParamException,
            ItemNotFoundException, ConflictingItemReference {
        switch (paramType) {
        case "/amount":
            this.amount = paramChecker.checkAndReturnDouble(paramType);
            break;
        case "/ir":
            this.interestRate = paramChecker.checkAndReturnDouble(paramType);
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