package seedu.financeit.datatrackers.entrytracker.entryhandlers;

import seedu.financeit.common.CategoryMap;
import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Common;
import seedu.financeit.common.ParamHandler;
import seedu.financeit.common.exceptions.IncompatibleParamsException;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.datatrackers.entrytracker.Entry;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.ParamChecker;

import java.time.LocalTime;
import java.util.Arrays;

import static seedu.financeit.utils.ParamChecker.PARAM_AMOUNT;
import static seedu.financeit.utils.ParamChecker.PARAM_CATEGORY;
import static seedu.financeit.utils.ParamChecker.PARAM_DESCRIPTION;
import static seedu.financeit.utils.ParamChecker.PARAM_EXP;
import static seedu.financeit.utils.ParamChecker.PARAM_INC;
import static seedu.financeit.utils.ParamChecker.PARAM_INDEX;
import static seedu.financeit.utils.ParamChecker.PARAM_TIME;

/**
 * CommandHandler class to edit an existing entry instance with specified parameter values.
 * Entry to be edited must be referenced via the constructor argument.
 */
public class EditEntryHandler extends ParamHandler {
    Entry entry;
    String prevEntry;
    private static EditEntryHandler handler = null;

    // Function of constructor is to set required params
    // No constructor needed as there are no required params for edit entry

    public void checkCatAndEntryType() throws IncompatibleParamsException {
        switch (this.entry.getEntryType()) {
        case EXP:
            if (!CategoryMap.expenseCategories.contains(this.entry.getCategory())) {
                UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    "Category is not compatible with entry type!",
                    "-e : {fd, tpt, tvl, shp, bll, oth}"
                );
                throw new IncompatibleParamsException(PARAM_EXP, PARAM_CATEGORY);
            }
            break;
        case INC:
            if (!CategoryMap.incomeCategories.contains(this.entry.getCategory())) {
                UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    "Category is not compatible with entry type!",
                    "-i : {slr, alw, oth}"
                );
                throw new IncompatibleParamsException(PARAM_INC, PARAM_CATEGORY);
            }
            break;

        default:
            // Fall through
            break;
        }
    }

    public static EditEntryHandler getInstance() {
        if (handler == null) {
            handler = new EditEntryHandler();
        }
        return handler;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    public void checkIfParamToEditExists(CommandPacket packet) throws InsufficientParamsException {
        if (packet.getParamTypes().size() <= 1) {
            assert packet.getParam("/id") != null;
            throw new InsufficientParamsException("At least 1 param required for edit!");
        }
    }

    public void checkIfParamToEditEffective() throws InsufficientParamsException {
        if (prevEntry.equals(entry.toString())) {
            throw new InsufficientParamsException("Specified params do not differ from original information. "
                + "Entry did not change.");
        }
    }

    public void handlePacket(CommandPacket packet)
        throws InsufficientParamsException, ItemNotFoundException, IncompatibleParamsException {
        prevEntry = entry.toString();
        this.handleParams(packet);
        checkCatAndEntryType();
        checkIfParamToEditExists(packet);
        checkIfParamToEditEffective();
    }

    @Override
    public void handleSingleParam(CommandPacket packet, String paramType) throws ParseFailParamException {
        switch (paramType) {
        case PARAM_TIME:
            LocalTime time = ParamChecker.getInstance().checkAndReturnTime(paramType);
            this.entry.setTime(time);
            break;
        case PARAM_AMOUNT:
            Double amount = ParamChecker.getInstance().checkAndReturnDoubleSigned(paramType);
            this.entry.setAmount(amount);
            break;
        case PARAM_INC:
            this.entry.setEntryType(Common.EntryType.INC);
            break;
        case PARAM_EXP:
            this.entry.setEntryType(Common.EntryType.EXP);
            break;
        case PARAM_DESCRIPTION:
            this.entry.setDescription(packet.getParam(paramType));
            break;
        case PARAM_CATEGORY:
            String category = ParamChecker.getInstance().checkAndReturnCategory(paramType);
            this.entry.setCategory(category);
            break;
        default:
            String[] ignoreParams = {
                PARAM_INDEX
            };
            if (!Arrays.asList(ignoreParams).contains(paramType)) {
                UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    ParamChecker.getInstance().getUnrecognizedParamMessage(paramType));
            }
            break;
        }
    }
}
