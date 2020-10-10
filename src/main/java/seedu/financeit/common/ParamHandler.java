package seedu.financeit.common;

import seedu.financeit.common.exceptions.ConflictingItemReference;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.ParamChecker;

import java.util.ArrayList;

public abstract class ParamHandler {
    protected ArrayList<String> requiredParams = new ArrayList<>();
    protected ArrayList<String> parseFailParams = new ArrayList<>();
    protected ParamChecker paramChecker;
    protected String defaultDateTimeFormat = null;

    public void handleParams(CommandPacket packet)
        throws AssertionError, InsufficientParamsException, ItemNotFoundException, ConflictingItemReference {
        this.paramChecker = new ParamChecker(packet);
        for (String paramType : requiredParams) {
            try {
                handleParam(packet, paramType);
            } catch (ParseFailParamException exception) {
                parseFailParams.add(paramType);
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    paramChecker.getParseFailParamMessage(paramType));
            }
        }

        if (!isValidItem()) {
            throw new InsufficientParamsException(this.parseFailParams);
        }
    }

    public abstract boolean isValidItem();

    public abstract void handleParam(CommandPacket packet, String paramType)
        throws ParseFailParamException, ItemNotFoundException, ConflictingItemReference;
}
