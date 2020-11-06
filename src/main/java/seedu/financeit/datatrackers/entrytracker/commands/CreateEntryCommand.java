package seedu.financeit.datatrackers.entrytracker.commands;

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
import static seedu.financeit.utils.ParamChecker.PARAM_TIME;

/**
 * Command class to create an entry instance, and populates it with parameter values.
 * The populated entry will be stored within the class, and can be retrieved by calling a
 * corresponding getter method.
 */
public class CreateEntryCommand extends ParamHandler {
    Entry entry;

    public CreateEntryCommand() {
        this.setRequiredParams(
            PARAM_TIME,
            PARAM_DESCRIPTION,
            PARAM_CATEGORY,
            PARAM_AMOUNT,
            PARAM_INC + " or " + PARAM_EXP
        );
    }

    public void handlePacket(CommandPacket packet)
        throws InsufficientParamsException, IncompatibleParamsException {
        try {
            this.entry = new Entry();
            this.handleParams(packet);
            checkCatAndEntryType();
        } catch (ItemNotFoundException exception) {
            // Fall-through
        }
    }

    public void checkCatAndEntryType() throws InsufficientParamsException, IncompatibleParamsException {
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
            String description = ParamChecker.getInstance().checkAndReturnDescription(paramType);
            this.entry.setDescription(description);
            break;
        case PARAM_CATEGORY:
            String category = ParamChecker.getInstance().checkAndReturnCategory(paramType);
            this.entry.setCategory(category);
            break;
        default:
            String[] ignoreParams = {
                "/id"
            };
            if (! Arrays.asList(ignoreParams).contains(paramType)) {
                UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    ParamChecker.getInstance().getUnrecognizedParamMessage(paramType));
            }
            break;
        }
    }

    /**
     * Gets the entry that is generated from the command.
     * @return Entry instance
     */
    public Entry getCurrEntry() {
        return this.entry;
    }
}
