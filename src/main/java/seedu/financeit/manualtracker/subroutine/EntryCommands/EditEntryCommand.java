package seedu.financeit.manualtracker.subroutine.EntryCommands;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.ParamHandler;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.manualtracker.subroutine.Entry;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.ParamChecker;

import java.time.LocalTime;
import java.util.Arrays;

public class EditEntryCommand extends ParamHandler {
    Entry entry;

    public EditEntryCommand(String... paramTypes) {
        this.setRequiredParams(paramTypes);
    }

    public void handlePacket(CommandPacket packet) throws InsufficientParamsException {
        try {
            this.entry = new Entry();
            this.handleParams(packet);
        } catch (ItemNotFoundException exception) {
            // Fall-through
        }
    }

    @Override
    public void handleSingleParam(CommandPacket packet, String paramType) throws ParseFailParamException {
        switch (paramType) {
        case ParamChecker.PARAM_TIME:
            LocalTime time = ParamChecker.getInstance().checkAndReturnTime(paramType);
            this.entry.setTime(time);
            break;
        case ParamChecker.PARAM_AMOUNT:
            Double amount = ParamChecker.getInstance().checkAndReturnDoubleSigned(paramType);
            this.entry.setAmount(amount);
            break;
        case ParamChecker.PARAM_INC:
            this.entry.setEntryType(Constants.EntryType.INC);
            break;
        case ParamChecker.PARAM_EXP:
            this.entry.setEntryType(Constants.EntryType.EXP);
            break;
        case ParamChecker.PARAM_DESCRIPTION:
            this.entry.setDescription(packet.getParam(paramType));
            break;
        case ParamChecker.PARAM_CATEGORY:
            String category = ParamChecker.getInstance().checkAndReturnCategory(paramType);
            this.entry.setCategory(category);
            break;
        default:
            String[] ignoreParams = {
                "/id"
            };
            if (! Arrays.asList(ignoreParams).contains(paramType)) {
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    ParamChecker.getInstance().getUnrecognizedParamMessage(paramType));
            }
            break;
        }
    }
}
