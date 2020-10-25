package seedu.financeit.manualtracker.subroutine.EntryCommands;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.ParamHandler;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.manualtracker.subroutine.EntryList;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.ParamChecker;

import java.util.Arrays;

public class RetrieveEntryCommand extends ParamHandler {
    EntryList entryList;

    public RetrieveEntryCommand(String... params) {
        this.setRequiredParams(params);
    }

    public void handlePacket(CommandPacket packet, EntryList entryList)
        throws InsufficientParamsException, ItemNotFoundException {
        this.entryList = entryList;
        this.handleParams(packet);
    }

    @Override
    public void handleSingleParam(CommandPacket packet, String paramType)
        throws ParseFailParamException, ItemNotFoundException {
        switch (paramType) {
        case ParamChecker.PARAM_INDEX:
            int index = ParamChecker.getInstance().checkAndReturnIndex(paramType, this.entryList.getItems());
            this.entryList.setIndexToModify(index);
            return;
        default:
            String[] ignoreParams = {
                "/time",
                "/desc",
                "/cat",
                "-i",
                "-e"
            };
            if (! Arrays.asList(ignoreParams).contains(paramType)) {
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    ParamChecker.getInstance().getUnrecognizedParamMessage(paramType));
            }
            break;
        }
    }
}
