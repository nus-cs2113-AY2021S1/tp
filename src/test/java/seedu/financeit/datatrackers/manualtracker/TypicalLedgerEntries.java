package seedu.financeit.datatrackers.manualtracker;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.datatrackers.manualtracker.commands.CreateLedgerCommand;

import java.time.LocalDate;

import static seedu.financeit.datatrackers.manualtracker.TestCommands.generateCreateLedgerCorrectCommand;

public class TypicalLedgerEntries {
    static CommandPacket packet;
    static int seed;
    private static TypicalLedgerEntries typicalLedgerEntries = null;

    private TypicalLedgerEntries() {

    }

    public static TypicalLedgerEntries getInstance() {
        if (typicalLedgerEntries == null) {
            typicalLedgerEntries = new TypicalLedgerEntries();
        }
        return typicalLedgerEntries;
    }

    public Ledger generateTypicalLedgerFromSeed() {
        CreateLedgerCommand command = new CreateLedgerCommand();
        try {
            command.handlePacket(packet);
        } catch (InsufficientParamsException exception) {
            //Fall through
        }
        return command.getCurrLedger();
    }

    public Ledger generateTypicalLedger1() {
        Ledger ledger = new Ledger();
        LocalDate date = LocalDate.parse("2020-05-05");
        ledger.setDate(date);
        this.packet = packet;
        return ledger;
    }

    public Ledger generateTypicalLedger2() {
        Ledger ledger = new Ledger();
        LocalDate date = LocalDate.parse("2020-06-06");
        ledger.setDate(date);
        this.packet = packet;
        return ledger;
    }

    public void setSeed(int seed) {
        this.seed = seed;
        this.packet = generateCreateLedgerCorrectCommand(seed);
    }
}
