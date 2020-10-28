package seedu.financeit.common;

import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.ParamChecker;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class ParamHandler {
    // Compulsory params that has to be parsed - varies based on individual subclass
    protected HashSet<String> requiredParams = new HashSet<>();

    // Params that parsed successfully
    protected HashSet<String> paramsSuccessfullyParsed = new HashSet<>();
    // Params that are part of requiredParams yet are not present in paramsSuccessfullyParsed
    protected ArrayList<String> missingRequiredParams = new ArrayList<>();
    protected boolean hasParsedAllRequiredParams = false;

    /**
     * Iteratively handles params in the packet and
     * evaluates if the param processing was successful.
     *
     * @param packet input CommandPacket obtained from parsing user input.
     * @throws InsufficientParamsException If not all compulsory params are successfully parsed
     * @throws ItemNotFoundException       For item retrieving cases, if params supplied does not correspond with
     *                                     an existing item in the list.
     */
    public void handleParams(CommandPacket packet)
        throws InsufficientParamsException, ItemNotFoundException {
        // Reset Sequence
        this.resetAllParamCollections();
        this.hasParsedAllRequiredParams = false;
        ParamChecker.getInstance().setPacket(packet);

        // Handle each param using individual handleSingleParam of subclass
        for (String paramType : packet.getParamTypes()) {
            try {
                handleSingleParam(packet, paramType);
                // ParamTypes that are parsed correctly
                // (i.e. no exception thrown) will be recorded
                this.paramsSuccessfullyParsed.add(paramType);
            } catch (ParseFailParamException exception) {
                // Report paramTypes that failed to parse.
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    ParamChecker.getInstance().getParseFailParamMessage(paramType));
            }
        }
        this.hasParsedAllRequiredParams = checkParseFailedParams();
        this.requiredParams.clear();
        if (!this.hasParsedAllRequiredParams) {
            throw new InsufficientParamsException(this.missingRequiredParams);
        }
    }

    public boolean getHasParsedAllRequiredParams() {
        return this.hasParsedAllRequiredParams;
    }

    /**
     * Adds given params to the set of required params.
     *
     * @param paramTypes - all params that are required
     */
    public void setRequiredParams(String... paramTypes) {
        for (String paramType : paramTypes) {
            this.requiredParams.add(paramType);
        }
    }

    /**
     * Clears params assigned to required params.
     */
    public void resetAllParamCollections() {
        this.missingRequiredParams.clear();
        this.paramsSuccessfullyParsed.clear();
    }

    /**
     * Checks and records any required param that is missing from successfully parsed params.
     * Makes use of class level HashSets to check and record.
     *
     * @return Whether all required params have been parsed
     */
    protected boolean checkParseFailedParams() {
        for (String param: this.requiredParams) {
            boolean isRequiredParamParsed = checkParamRequirementSatisfied(param);
            if (!isRequiredParamParsed) {
                this.missingRequiredParams.add(param);
            }
        }
        return this.missingRequiredParams.isEmpty();
    }

    /**
     * Checks whether the param has been parsed.
     * Can be single param or a set of params where one param satisfies the entire set
     * E.g. "-i or -e". Parsing either "-i" or "-e" will satisfy this requirement
     *
     * @param param - Individual param string to check. Can be single param,
     *                or several params delimited by " or "
     * @return Whether the param requirement has been satisfied
     */
    protected boolean checkParamRequirementSatisfied(String param) {
        String[] paramArray = param.split(" or ");
        for (String paramInArray: paramArray) {
            if (this.paramsSuccessfullyParsed.contains(paramInArray)) {
                return true;
            }
        }
        return false;
    }

    /**
     * To be implemented by children classes.
     *
     * @param packet    input CommandPacket obtained from parsing user input.
     * @param paramType paramType of param that is currently being validated and processed.
     * @throws InsufficientParamsException If not all compulsory params are successfully parsed
     * @throws ItemNotFoundException       For item retrieving cases, if params supplied does not correspond with
     *                                     an existing item in the list.
     */
    public abstract void handleSingleParam(CommandPacket packet, String paramType)
        throws ParseFailParamException, ItemNotFoundException;

}
