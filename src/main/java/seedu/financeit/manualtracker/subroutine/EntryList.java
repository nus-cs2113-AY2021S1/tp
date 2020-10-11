package seedu.financeit.manualtracker.subroutine;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.ItemList;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.ui.TablePrinter;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.ParamChecker;

import java.time.LocalTime;
import java.util.ArrayList;

public class EntryList extends ItemList {
    public EntryList() {
        defaultDateTimeFormat = "time";
        super.requiredParams = new ArrayList<>() {
            {
                add("/time");
                add("/cat");
                add("/amt");
                add("-i");
                add("-e");
                add("/id");
                add("/desc");
            }
        };
    }

    @Override
    public void printList(String... ledgerDate) {
        TablePrinter.setTitle(String.format("List of Entries for Ledger [%s]", ledgerDate[0]));
        TablePrinter.addRow("Entry Number;Entry Type;Category;Amount;Time;Description                    ");
        if (super.getItemsSize() == 0) {
            TablePrinter.addRow("No entries created               ");
        } else {
            for (int i = 0; i < super.getItemsSize(); i++) {
                TablePrinter.addRow(String.format("%s;%s", i + 1, super.items.get(i)));
            }
        }
        TablePrinter.printList();
    }

    @Override
    public boolean isValidItem() {
        return (this.itemQueue.size() == 1);
    }

    public Entry getItemFromTime(LocalTime time) throws ItemNotFoundException {
        Entry output;
        for (Object i : this.items) {
            output = (Entry)i;
            if ((output.getTime().equals(time))) {
                return output;
            }
        }
        throw new ItemNotFoundException();
    }

    @Override
    public void handleSingleParam(CommandPacket packet, String paramType)
        throws ParseFailParamException {
        switch (paramType) {
        case ParamChecker.PARAM_INDEX:
            int index = paramChecker.checkAndReturnIndex(paramType, this.items);
            this.itemQueue.add(super.getItemFromIndex(index));
            this.parseSuccessParams.add(paramType);
            break;
        default:
            if (!super.requiredParams.contains(paramType)) {
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    paramChecker.getUnrecognizedParamMessage(paramType));
            }
            break;
        }
    }
}

