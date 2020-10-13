package seedu.financeit.financetools;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.ParamHandler;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.ui.UiManager;

import java.util.ArrayList;

public class MilesCredit extends ParamHandler {

    private double amount = -1;
    private double milesRate = -1;

    public MilesCredit() {
        super();
        super.requiredParams = new ArrayList<>() {
            {
                add("/amount");
                add("/rate");
            }
        };
    }

    public MilesCredit(CommandPacket packet) throws InsufficientParamsException {
        this();
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
            if (!super.requiredParams.contains(paramType)) {
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                        paramChecker.getUnrecognizedParamMessage(paramType));
            }
            break;
        }
    }
}