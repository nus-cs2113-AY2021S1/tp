package seedu.financeit.datatrackers.entrytracker;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.exceptions.IncompatibleParamsException;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.datatrackers.entrytracker.entryhandlers.CreateEntryHandler;
import seedu.financeit.testutil.TestUtil;
import seedu.financeit.utils.ParamChecker;

import static seedu.financeit.datatrackers.entrytracker.TestCommands.generateCreateEntryCorrectCommand;

/**
 * Generates instances of the Entry class for testing purposes.
 */
public class TypicalEntryEntries {
    static CommandPacket packet;
    // Seed used to generate an entry with a particular set of parameters. Entries generated with the same seed
    // are identical.
    static int seed;
    private static TypicalEntryEntries typicalEntryEntries = null;

    private TypicalEntryEntries() {

    }

    public void setSeed(int seed) {
        this.seed = seed;
        this.packet = generateCreateEntryCorrectCommand(seed);
    }

    public static TypicalEntryEntries getInstance() {
        if (typicalEntryEntries == null) {
            typicalEntryEntries = new TypicalEntryEntries();
        }
        return typicalEntryEntries;
    }

    /**
     * Generates an entry populated with values generated from an integer seed.
     * @return populated Entry.
     */
    public Entry generateTypicalEntryFromSeed() {
        CreateEntryHandler createEntryHandler = CreateEntryHandler.getInstance();
        try {
            createEntryHandler.handlePacket(this.packet);
        } catch (InsufficientParamsException | IncompatibleParamsException exception) {
            //Fall through
        }
        return createEntryHandler.getCurrEntry();
    }

    /**
     * Generates an entry with manually-specified parameters.
     * @return Populated entry.
     */
    public Entry generateTypicalEntry1() {
        CreateEntryHandler createEntryHandler = CreateEntryHandler.getInstance();
        CommandPacket packet = TestUtil.createCommandPacket(
            "new",
            new String[][]{
                {
                    ParamChecker.PARAM_TIME,
                    "1500"
                },
                {
                    ParamChecker.PARAM_CATEGORY,
                    "oth"
                },
                {
                    ParamChecker.PARAM_INC,
                    "-i"
                },
                {
                    ParamChecker.PARAM_DESCRIPTION,
                    "test description1"
                },
                {
                    ParamChecker.PARAM_AMOUNT,
                    "1600.56"
                }
            }
        );
        try {
            createEntryHandler.handlePacket(packet);
            this.packet = packet;
        } catch (InsufficientParamsException | IncompatibleParamsException exception) {
            //Fall through
        }
        return createEntryHandler.getCurrEntry();
    }

    /**
     * Generates an entry with manually-specified parameters.
     * @return Populated entry.
     */
    public Entry generateTypicalEntry2() {
        CreateEntryHandler createEntryHandler = CreateEntryHandler.getInstance();
        CommandPacket packet = TestUtil.createCommandPacket(
            "new",
            new String[][]{
                {
                    ParamChecker.PARAM_TIME,
                    "1620"
                },
                {
                    ParamChecker.PARAM_CATEGORY,
                    "oth"
                },
                {
                    ParamChecker.PARAM_INC,
                    "-e"
                },
                {
                    ParamChecker.PARAM_DESCRIPTION,
                    "test description2"
                },
                {
                    ParamChecker.PARAM_AMOUNT,
                    "567.00"
                }
            }
        );
        try {
            createEntryHandler.handlePacket(packet);
            this.packet = packet;
        } catch (InsufficientParamsException | IncompatibleParamsException exception) {
            //Fall through
        }
        return createEntryHandler.getCurrEntry();
    }
}
