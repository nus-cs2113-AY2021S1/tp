package seedu.financeit.datatrackers.manualtracker;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.datatrackers.manualtracker.ledgerhandlers.CreateLedgerHandler;

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
        CreateLedgerHandler createLedgerHandler = CreateLedgerHandler.getInstance();
        try {
            createLedgerHandler.handlePacket(packet);
        } catch (Exception exception) {
            //Fall through
        }
        return createLedgerHandler.getCurrLedger();
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
