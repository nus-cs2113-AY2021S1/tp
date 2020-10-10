package seedu.financeit.manualtracker;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.ItemList;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.ui.TablePrinter;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.ParamChecker;

import java.time.LocalDateTime;

public class LedgerList extends ItemList {

    @Override
    public void printList(String... ledgerName) {
        TablePrinter.setTitle("List of Ledgers");
        TablePrinter.addRow("Ledger Number;Ledger Date");
        if (super.getItemsSize() == 0) {
            TablePrinter.addRow("No ledgers created;               ");
        } else {
            for (int i = 0; i < super.getItemsSize(); i++) {
                TablePrinter.addRow(String.format("%s;%s", i + 1, this.items.get(i)));
            }
        }
        TablePrinter.printList();
    }


    @Override
    public boolean isValidItem() {
        return true;
    }

    @Override
    public void handleParam(CommandPacket packet, String paramType)
        throws ParseFailParamException, ItemNotFoundException {
        switch (paramType) {
        case ParamChecker.PARAM_DATE:
            LocalDateTime dateTime = paramChecker.checkAndReturnDateTime(paramType, defaultDateTimeFormat);
            this.currItem = this.getItemFromDateTime(dateTime);
            break;
        case ParamChecker.PARAM_INDEX:
            int index = paramChecker.checkAndReturnIndex(paramType, this.items);
            this.currItem = this.getItemFromIndex(index);
        default:
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                paramChecker.getUnrecognizedParamMessage(paramType));
            break;
        }
    }
}
