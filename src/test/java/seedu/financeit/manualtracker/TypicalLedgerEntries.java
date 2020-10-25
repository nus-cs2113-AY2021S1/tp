package seedu.financeit.manualtracker;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.exceptions.InsufficientParamsException;

import java.util.ArrayList;

public class TypicalLedgerEntries {
    public static ArrayList<String> commandInputs = new ArrayList<>();
    Ledger ledger = null;

    public static Ledger generateTypicalLedgerOne() {
        Ledger ledger = new Ledger();
        CommandPacket packet = TestCommands.generateCreateLedgerCorrectCommand(3);
        try {
            ledger.handlePacket(packet);
        } catch (InsufficientParamsException exception) {
            //Fall through
        }
        return ledger;
    }
}
