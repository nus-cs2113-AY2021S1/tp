package seedu.financeit.common;

import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.ParamChecker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public abstract class ParamHandler {
    // List of required params that instance needs to parse successfully for param processing to succeed.
    protected ArrayList<String> requiredParams = new ArrayList<>();
    // List of params that failed to parse.
    protected ArrayList<String> parseFailParams = new ArrayList<>();
    // List of params that are parsed successfully.
    protected ArrayList<String> parseSuccessParams = new ArrayList<>();
    // Class to check the correctness of the param input.
    protected ParamChecker paramChecker;
    protected boolean hasParsedAllRequiredParams = false;

    /**
     * Iteratively handles params in the packet and
     * evaluates if the param processing was successful.
     *
     * @param packet input CommandPacket obtained from parsing user input.
     * @throws InsufficientParamsException If params supplied by the required paramTypes are not enough to warrant
     *                                     a successful parse.
     * @throws ItemNotFoundException       for item retrieving cases, if params supplied does not correspond with
     *                                     an existing item in the list.
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
                this.parseSuccessParams.add(paramType);
            } catch (ParseFailParamException exception) {
                // Report paramTypes that failed to parse.
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    paramChecker.getParseFailParamMessage(paramType));
            }
        }

        // Generate List of params that failed to parse.
        this.parseFailParams = checkParseFailParams(this.requiredParams, this.parseSuccessParams);

        if (!this.hasParsedAllRequiredParams) {
            throw new InsufficientParamsException(this.parseFailParams);
        }
    }

    public boolean getHasParsedAllRequiredParams() {
        return this.hasParsedAllRequiredParams;
    }

    // Sets the required param depending on the function required.
    public void setRequiredParams(String... paramTypes) {
        this.requiredParams.clear();
        for (String paramType : paramTypes) {
            this.requiredParams.add(paramType);
        }
    }

    protected ArrayList<String> checkParseFailParams(ArrayList<String> requiredParams,
                                                     ArrayList<String> parseSuccessParams) {
        ArrayList<String> buffer = new ArrayList<>(requiredParams);
        ArrayList<String> output = assessParamType(buffer);
        // If all params are ticked off,
        // all required params have parsed correctly.
        if (output.isEmpty()) {
            this.hasParsedAllRequiredParams = true;
        }
        this.parseSuccessParams.clear();
        return buffer;
    }

    /**
     * For each param that has parsed correctly,
     * "tick" off the required params list.
     * @param buffer list of params to be "ticked" off against.
     * @return processed buffer.
     */
    protected ArrayList<String> assessParamType(ArrayList<String> buffer) {
        for (String i : this.parseSuccessParams) {
            removeParamTypeIfParsed(i, buffer);
        }
        return buffer;
    }

    protected void removeParamTypeIfParsed(String checkingParam, ArrayList<String> paramList) {
        for (String paramPair : this.requiredParams) {
            // This enables checking for a set params whereby only one is required.
            // One example would be "-i or -e".
            String[] params = paramPair.split(" or ");
            HashSet<String> paramSet = new HashSet<>(Arrays.asList(params));
            if (paramSet.contains(checkingParam)) {
                paramList.remove(paramPair);
            }
        }
    }

    /**
     * To be implemented by children classes.
     *
     * @param packet    input CommandPacket obtained from parsing user input.
     * @param paramType paramType of param that is currently being validated and processed.
     * @throws InsufficientParamsException If params supplied by the required paramTypes are not enough to warrant
     *                                     a successful parse.
     * @throws ItemNotFoundException       for item retrieving cases, if params supplied does not correspond with
     *                                     an existing item in the list.
     */
    public abstract void handleSingleParam(CommandPacket packet, String paramType)
        throws ParseFailParamException, ItemNotFoundException;
}
