package seedu.financeit.financetools;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Common;
import seedu.financeit.common.ParamHandler;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.ParamChecker;

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
            totalInterestEarned = totalAmount - this.amount;
        } else {
            for (int i = 0; i < calculationPeriod; i++) {
                this.amount += yearlyDeposit;
                totalAmount = this.amount * Math.pow((1 + interestRate / compoundInterval), compoundInterval);
                interestEarned = totalAmount - this.amount;
                System.out.printf("Total Interest earned in Year " + "%d", i + 1);
                System.out.printf(": $%.2f\n", interestEarned);
                totalInterestEarned += interestEarned;
                this.amount += interestEarned;
            }
        }

        return Math.round(totalInterestEarned * 100.00) / 100.00;
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
        case "/p":
            this.calculationPeriod = ParamChecker.getInstance().checkAndReturnInt(paramType);
            break;
        case "/d":
            this.yearlyDeposit = ParamChecker.getInstance().checkAndReturnDouble(paramType);
            break;
        default:
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                ParamChecker.getInstance().getUnrecognizedParamMessage(paramType));
            break;
        }
    }
}