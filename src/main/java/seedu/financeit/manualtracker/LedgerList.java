package seedu.financeit.manualtracker;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.Item;
import seedu.financeit.common.ItemList;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.ui.TablePrinter;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.ParamChecker;

import java.time.LocalDate;
import java.util.ArrayList;

public class LedgerList extends ItemList {
    public LedgerList() {
        super.requiredParams = new ArrayList<>() {
            {
                add("/date");
                add("/id");
            }
        };
    }

    public Ledger getItemFromDate(LocalDate date) throws ItemNotFoundException {
        Ledger output;
        for (Item i : this.items) {
            output = (Ledger)i;
            if ((output.getDate().equals(date))) {
                return output;
            }
        }
        throw new ItemNotFoundException();
    }

    @Override
    public void printList() {
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
    public void handleSingleParam(CommandPacket packet, String paramType)
        throws ParseFailParamException, ItemNotFoundException {
        switch (paramType) {
        case ParamChecker.PARAM_DATE:
            LocalDate date = paramChecker.checkAndReturnDate(paramType);
            this.setCurrItem(this.getItemFromDate(date));
            // Both params are added because the item specified has been identified,
            // therefore params processing was successful.
            this.parseSuccessParams.add("/date");
            this.parseSuccessParams.add("/id");
            break;
        case ParamChecker.PARAM_INDEX:
            int index = paramChecker.checkAndReturnIndex(paramType, this.items);
            this.setCurrItem(this.getItemFromIndex(index));
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
    }
}
