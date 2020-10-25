package seedu.financeit.manualtracker;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.testutil.TestUtil;
import seedu.financeit.utils.ParamChecker;

import static seedu.financeit.manualtracker.TestCommands.generateCreateLedgerCorrectCommand;

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
        Ledger ledger = new Ledger();
        try {
            ledger.handlePacket(this.packet);
        } catch (InsufficientParamsException exception) {
            //Fall through
        }
        return ledger;
    }

    public Ledger generateTypicalLedger1() {
        Ledger ledger = new Ledger();
        CommandPacket packet = TestUtil.createCommandPacket(
            "new",
            new String[][]{
                {
                    ParamChecker.PARAM_DATE,
                    "2020-05-05"
                }
            }
        );
        try {
            ledger.handlePacket(packet);
            this.packet = packet;
        } catch (InsufficientParamsException exception) {
            //Fall through
        }
        return ledger;
    }

    public Ledger generateTypicalLedger2() {
        Ledger ledger = new Ledger();
        CommandPacket packet = TestUtil.createCommandPacket(
            "new",
            new String[][]{
                {
                    ParamChecker.PARAM_DATE,
                    "2020-05-05"
                },
            }
        );
        try {
            ledger.handlePacket(packet);
            this.packet = packet;
        } catch (InsufficientParamsException exception) {
            //Fall through
        }
        return ledger;
    }

    public void setSeed(int seed) {
        this.seed = seed;
        this.packet = generateCreateLedgerCorrectCommand(seed);
    }
}
