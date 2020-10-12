package seedu.financeit.common;

import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.ParamChecker;

import java.util.ArrayList;

public abstract class ParamHandler {
    // List of required params that instance needs to parse successfully for param processing to succeed.
    protected ArrayList<String> requiredParams = new ArrayList<>();
    // List of params that failed to parse.
    protected ArrayList<String> parseFailParams = new ArrayList<>();
    // List of params that are parsed successfully.
    protected ArrayList<String> parseSuccessParams = new ArrayList<>();
    // Class to check the correctness of the param input.
    protected ParamChecker paramChecker;
    protected boolean hasParsedAllRequiredParams;

    /**
     * Iteratively handles params in the packet and
     * evaluates if the param processing was successful.
     * @param packet input CommandPacket obtained from parsing user input.
     * @throws InsufficientParamsException
     * @throws ItemNotFoundException
     */
    public void handleParams(CommandPacket packet)
        throws InsufficientParamsException, ItemNotFoundException {
        // Reset flag to false so that it can be set to true when all the required params are parsed.
        this.hasParsedAllRequiredParams = false;
        this.paramChecker = new ParamChecker(packet);
        // Iterate over each paramType to handle each param in handleSingleParam.
        for (String paramType : packet.getParamTypes()) {
            try {
                // ParamTypes that are parsed correctly will be added to parseSuccessParams
                // within the children classes' implemented handleSingleParams.
                handleSingleParam(packet, paramType);
            } catch (ParseFailParamException exception) {
                // Report paramTypes that failed to parse.
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    paramChecker.getParseFailParamMessage(paramType));
            }
        }

        // Generate List of params that failed to parse.
        this.parseFailParams = checkParseFailParams(this.requiredParams, this.parseSuccessParams);

        if (this.hasParsedAllRequiredParams) {
            throw new InsufficientParamsException(this.parseFailParams);
        }
    }

    protected ArrayList<String> checkParseFailParams(ArrayList<String> requiredParams,
                                                     ArrayList<String> parseSuccessParams) {
        ArrayList<String> buffer = new ArrayList<>(requiredParams);
        // For each param that has parsed correctly,
        // "tick" off the required params list.
        for (String i : parseSuccessParams) {
            buffer.remove(i);
        }
        // If all params are ticked off,
        // all required params have parsed correctly.
        if (buffer.isEmpty()) {
            this.hasParsedAllRequiredParams = true;
        }
        return buffer;
    }

    /**
     * To be implemented by children classes.
     * @param packet input CommandPacket obtained from parsing user input.
     * @param paramType paramType of param that is currently being validated and processed.
     * @throws ParseFailParamException
     * @throws ItemNotFoundException
     */
    public abstract void handleSingleParam(CommandPacket packet, String paramType)
        throws ParseFailParamException, ItemNotFoundException;
}
