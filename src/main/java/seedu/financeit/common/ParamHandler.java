package seedu.financeit.common;

import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.ParamChecker;

import java.util.ArrayList;

public abstract class ParamHandler {
    public abstract boolean isValidItem();
    protected ArrayList<String> requiredParams;
    protected ParamChecker paramChecker;
    protected String defaultDateTimeFormat = null;

    public void handleParams(CommandPacket packet)
        throws AssertionError, InsufficientParamsException, ItemNotFoundException {

        for (String paramType : packet.getParamTypes()) {
            try {
                handleParam(packet, paramType);
                requiredParams.remove(paramType);
            } catch (ParseFailParamException exception) {
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    paramChecker.getParseFailParamMessage(paramType));
            }
        }

        if (!isValidItem()) {
            throw new InsufficientParamsException(this.requiredParams);
        }
    }


    public abstract void handleParam(CommandPacket packet, String paramType) throws ParseFailParamException, ItemNotFoundException;
}
