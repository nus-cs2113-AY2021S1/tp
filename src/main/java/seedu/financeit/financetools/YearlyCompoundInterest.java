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

public class YearlyCompoundInterest extends ParamHandler {

    private double amount = 0;
    private double interestRate = 0;
    private int calculationPeriod = 0;
    private double yearlyDeposit = 0;

    public YearlyCompoundInterest() {
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
        assert this.calculationPeriod >= 0 : "Calculation period (in years) should not be a negative number";
        assert this.yearlyDeposit >= 0 : "Yearly deposit should not be a negative number";

        double interestRate = this.interestRate / 100;
        int period = this.calculationPeriod;
        int compoundInterval = 1;
        double totalAmount;
        double interestEarned;
        double totalInterestEarned = 0;

        System.out.println("Compound Interval: Yearly\n");
        if (yearlyDeposit == 0) {
            totalAmount = this.amount * Math.pow((1 + interestRate / compoundInterval), compoundInterval * period);
            totalInterestEarned = Math.round((totalAmount - this.amount) * 100.00) / 100.00;
        } else {
            for (int i = 0; i < calculationPeriod; i++) {
                this.amount += yearlyDeposit;
                totalAmount = this.amount * Math.pow((1 + interestRate / compoundInterval), compoundInterval);
                interestEarned = Math.round((totalAmount - this.amount) * 100.00) / 100.00;
                System.out.printf("Total Interest earned in Year " + "%d", i + 1);
                System.out.printf(": $%.2f\n", interestEarned);
                totalInterestEarned += interestEarned;
                this.amount += interestEarned;
            }
        }

        return totalInterestEarned;
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
        case "/deposit":
            this.yearlyDeposit = paramChecker.checkAndReturnDouble(paramType);
            break;
        default:
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    paramChecker.getUnrecognizedParamMessage(paramType));
            break;
        }
    }
}