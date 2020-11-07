//@@author bqxy

package seedu.financeit.financetools;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Common;
import seedu.financeit.common.ParamHandler;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.ParamChecker;

/**
 * Represents all operations for Cashback Calculator feature.
 */
public class Cashback extends ParamHandler {

    private double amount = -1;
    private double cashbackRate = -1;
    private double monthlyCap = -1;

    /**
     * Constructor for Cashback object.
     */
    public Cashback() {
        super();
    }

    /**
     * Handles parameters inputted by user.
     *
     * @param packet each packet contains different inputs from user.
     * @throws InsufficientParamsException if there are missing params.
     */
    public void handlePacket(CommandPacket packet) throws InsufficientParamsException {
        try {
            this.handleParams(packet);
        } catch (ItemNotFoundException exception) {
            // Fall-through
        }
    }

    /**
     * Calculates cashback interest earned.
     *
     * @return cashback interest earned.
     */
    public double calculateCashback() {
        assert this.amount >= 0 : "Amount should not be a negative number";
        assert this.cashbackRate >= 0 : "Cashback rate should not be a negative number";
        assert this.monthlyCap >= 0 : "Monthly cap should not be a negative number";

        Double cashbackEarned = Math.round((this.amount * (this.cashbackRate / 100)) * 100.00) / 100.00;
        if (cashbackEarned > this.monthlyCap) {
            cashbackEarned = this.monthlyCap;
        }
        return cashbackEarned;
    }

    /**
     * Handles user inputted param.
     *
     * @param packet    input CommandPacket obtained from parsing user input.
     * @param paramType paramType of param that is currently being validated and processed.
     * @throws ParseFailParamException if param does not fulfil conditions for a proper input param.
     */
    @Override
    public void handleSingleParam(CommandPacket packet, String paramType) throws ParseFailParamException {
        switch (paramType) {
        case "/a":
            this.amount = ParamChecker.getInstance().checkAndReturnDoubleSigned(paramType);
            break;
        case "/r":
            this.cashbackRate = ParamChecker.getInstance().checkAndReturnDoubleSigned(paramType);
            break;
        case "/c":
            this.monthlyCap = ParamChecker.getInstance().checkAndReturnDoubleSigned(paramType);
            break;
        default:
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    ParamChecker.getInstance().getUnrecognizedParamMessage(paramType));
            break;
        }
    }
}
