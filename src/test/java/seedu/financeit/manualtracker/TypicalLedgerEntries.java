package seedu.financeit.manualtracker;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.exceptions.InsufficientParamsException;

import java.util.ArrayList;

public class TypicalLedgerEntries {
    public static ArrayList<String> commandInputs = new ArrayList<>();
    Ledger ledger = null;

    public Ledger generateTypicalLedgerOne() throws InsufficientParamsException {
        Ledger ledger = new Ledger();
        CommandPacket packet = TestCommands.generateCreateLedgerCorrectCommand(3);
        ledger.handlePacket(packet);
        return ledger;
    }

}
