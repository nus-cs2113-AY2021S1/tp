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
 * Represents all operations for Simple Interest Calculator feature.
 */
public class SimpleInterest extends ParamHandler {

    private double amount = -1;
    private double interestRate = -1;

    /**
     * Constructor for Simple Interest object.
     */
    public SimpleInterest() {
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
     * Calculates simple interest earned.
     *
     * @return simple interest earned.
     */
    public double calculateSimpleInterest() {
        assert this.amount >= 0 : "Amount should not be a negative number";
        assert this.interestRate >= 0 : "Interest rate should not be a negative number";

        return Math.round((this.amount * (this.interestRate / 100)) * 100.00) / 100.00;
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
            this.amount = ParamChecker.getInstance().checkAndReturnDouble(paramType);
            break;
        case "/r":
            this.interestRate = ParamChecker.getInstance().checkAndReturnDouble(paramType);
            break;
        default:
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                ParamChecker.getInstance().getUnrecognizedParamMessage(paramType));
            break;
        }
    }
}