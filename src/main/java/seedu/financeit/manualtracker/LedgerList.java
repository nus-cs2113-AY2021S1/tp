package seedu.financeit.manualtracker;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.ItemList;
import seedu.financeit.common.exceptions.ConflictingItemReference;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.ui.TablePrinter;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.ParamChecker;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class LedgerList extends ItemList {
    public LedgerList() {
        defaultDateTimeFormat = "date";
        super.requiredParams = new ArrayList<>() {
            {
                add("/date");
                add("/id");
            }
        };
    }

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
        return (this.itemQueue.size() == 1);
    }

    @Override
    public void handleSingleParam(CommandPacket packet, String paramType)
        throws ParseFailParamException, ItemNotFoundException, ConflictingItemReference {
        switch (paramType) {
        case ParamChecker.PARAM_DATE:
            LocalDateTime dateTime = paramChecker.checkAndReturnDateTime(paramType, this.defaultDateTimeFormat);
            this.itemQueue.add(this.getItemFromDateTime(dateTime));
            this.parseSuccessParams.add("/date");
            this.parseSuccessParams.add("/id");

            break;
        case ParamChecker.PARAM_INDEX:
            int index = paramChecker.checkAndReturnIndex(paramType, this.items);
            this.itemQueue.add(this.getItemFromIndex(index));
            this.parseSuccessParams.add("/date");
            this.parseSuccessParams.add("/id");
            break;
        default:
            if (!super.requiredParams.contains(paramType)) {
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    paramChecker.getUnrecognizedParamMessage(paramType));
            }
            break;
        }
        if (itemQueue.size() > 1) {
            throw new ConflictingItemReference(itemQueue);
        }
    }
}
