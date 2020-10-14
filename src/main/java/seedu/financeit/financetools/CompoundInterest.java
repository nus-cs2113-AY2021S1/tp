package seedu.financeit.financetools;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.ParamHandler;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.ui.UiManager;

import java.util.ArrayList;
import java.lang.Math;

public class CompoundInterest extends ParamHandler {

    private double amount = -1;
    private double interestRate = -1;
    private int calculationPeriod = -1;

    public CompoundInterest() {
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

    public double calculateCompoundInterest() {
        assert this.amount >= 0 : "Amount should not be a negative number";
        assert this.interestRate >= 0 : "Interest rate should not be a negative number";
        assert this.calculationPeriod >= 0 : "Calculation period should not be a negative number";

        double interestRate = this.interestRate / 100;
        int period = this.calculationPeriod;
        int compoundInterval = 1;
        double totalAmount = this.amount * Math.pow((1 + interestRate / compoundInterval), compoundInterval * period);
        double interestEarned = totalAmount - this.amount;
        return interestEarned;
    }

    @Override
    public void handleSingleParam(CommandPacket packet, String paramType) throws ParseFailParamException,
            ItemNotFoundException {
        switch (paramType) {
        case "/amount":
            this.amount = paramChecker.checkAndReturnDouble(paramType);
            break;
        case "/ir":
            this.interestRate = paramChecker.checkAndReturnDouble(paramType);
            break;
        case "/period":
            this.calculationPeriod = paramChecker.checkAndReturnInt(paramType);
            break;
        default:
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    paramChecker.getUnrecognizedParamMessage(paramType));
            break;
        }
    }
}