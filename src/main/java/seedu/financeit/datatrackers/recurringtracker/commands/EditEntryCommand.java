package seedu.financeit.datatrackers.recurringtracker.commands;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Common;
import seedu.financeit.common.ParamHandler;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.datatrackers.recurringtracker.RecurringEntry;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.ParamChecker;

import java.util.Arrays;

//@@author Artemis-Hunt
/**
 * Command class to edit an existing entry instance with specified parameter values.
 * Entry to be edited must be referenced via the constructor argument.
 */
import static seedu.financeit.utils.ParamChecker.PARAM_INDEX;

public class EditEntryCommand extends ParamHandler {
    RecurringEntry recurringEntry;

    public EditEntryCommand(RecurringEntry recurringEntry) {
        this.recurringEntry = recurringEntry;
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
        case ParamChecker.PARAM_DAY:
            this.recurringEntry.setDay(ParamChecker.getInstance().checkAndReturnInt(paramType));
            break;
        case ParamChecker.PARAM_AMOUNT:
            this.recurringEntry.setAmount(ParamChecker.getInstance().checkAndReturnDouble(paramType));
            break;
        case ParamChecker.PARAM_INC:
            this.recurringEntry.setEntryType(Common.EntryType.INC);
            break;
        case ParamChecker.PARAM_EXP:
            this.recurringEntry.setEntryType(Common.EntryType.EXP);
            break;
        case ParamChecker.PARAM_DESCRIPTION:
            this.recurringEntry.setDescription(packet.getParam(paramType));
            break;
        case ParamChecker.PARAM_AUTO:
            this.recurringEntry.setAuto(true);
            break;
        case ParamChecker.PARAM_NOTES:
            this.recurringEntry.setNotes(packet.getParam(paramType));
            break;
        default:
            String[] ignoreParams = {
                PARAM_INDEX
            };
            if (!Arrays.asList(ignoreParams).contains(paramType)) {
                UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    ParamChecker.getInstance().getUnrecognizedParamMessage(paramType));
            }
        }
    }
}
