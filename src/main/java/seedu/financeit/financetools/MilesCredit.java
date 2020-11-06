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
 * Represents all operations for Miles Credit Calculator feature.
 */
public class MilesCredit extends ParamHandler {

    private double amount = -1;
    private double milesRate = -1;

    /**
     * Constructor for Miles Credit object.
     */
    public MilesCredit() {
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
            handleParams(packet);
        } catch (ItemNotFoundException exception) {
            // Fall-through
        }
    }

    /**
     * Calculates miles credit earned.
     *
     * @return miles credit earned.
     */
    public double calculateMiles() {
        assert this.amount >= 0 : "Amount should not be a negative number";
        assert this.milesRate >= 0 : "Miles rate should not be a negative number";

        return Math.round((this.amount * this.milesRate) * 100.00) / 100.00;
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
            this.milesRate = ParamChecker.getInstance().checkAndReturnDoubleSigned(paramType);
            break;
        default:
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    ParamChecker.getInstance().getUnrecognizedParamMessage(paramType));
            break;
        }
    }
}