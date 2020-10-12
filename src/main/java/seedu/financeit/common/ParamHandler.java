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
    protected ArrayList<String> parseSuccessParams = new ArrayList<>();
    protected ParamChecker paramChecker;
    protected String defaultDateTimeFormat = null;

    public void handleParams(CommandPacket packet)
        throws InsufficientParamsException, ItemNotFoundException, ConflictingItemReference {
        this.paramChecker = new ParamChecker(packet);
        for (String paramType : packet.getParamTypes()) {
            try {
                handleSingleParam(packet, paramType);
            } catch (ParseFailParamException exception) {
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    paramChecker.getParseFailParamMessage(paramType));
            }
        }

        this.parseFailParams = checkParseFailParams(this.requiredParams, this.parseSuccessParams);

        //Todo: implement check of whether requiredParams == parseSuccessParams
        if (!isValidItem()) {
            throw new InsufficientParamsException(this.parseFailParams);
        }
    }

    protected ArrayList<String> checkParseFailParams(ArrayList<String> requiredParams,
                                                     ArrayList<String> parseSuccessParams) {
        ArrayList<String> buffer = new ArrayList<>(requiredParams);
        for (String i : parseSuccessParams) {
            buffer.remove(i);
        }
        return buffer;
    }


    public abstract boolean isValidItem();

    public abstract void handleSingleParam(CommandPacket packet, String paramType)
        throws ParseFailParamException, ItemNotFoundException, ConflictingItemReference;
}
