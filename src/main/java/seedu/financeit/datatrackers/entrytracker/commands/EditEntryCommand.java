package seedu.financeit.datatrackers.entrytracker.commands;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Common;
import seedu.financeit.common.ParamHandler;
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
 * Command class to edit an existing entry instance with specified parameter values.
 * Entry to be edited must be referenced via the constructor argument.
 */
public class EditEntryCommand extends ParamHandler {
    Entry entry;

    public EditEntryCommand(Entry entry) {
        this.entry = entry;
    }

    public void handlePacket(CommandPacket packet) throws InsufficientParamsException {
        try {
            this.handleParams(packet);
        } catch (ItemNotFoundException exception) {
            // Fall-through
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
