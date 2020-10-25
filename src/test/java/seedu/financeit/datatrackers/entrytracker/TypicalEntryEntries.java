package seedu.financeit.datatrackers.entrytracker;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.datatrackers.entrytracker.commands.CreateEntryCommand;
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
        CreateEntryCommand command = new CreateEntryCommand();
        try {
            command.handlePacket(this.packet);
        } catch (InsufficientParamsException exception) {
            //Fall through
        }
        return command.getCurrEntry();
    }

    /**
     * Generates an entry with manually-specified parameters.
     * @return Populated entry.
     */
    public Entry generateTypicalEntry1() {
        CreateEntryCommand command = new CreateEntryCommand();
        CommandPacket packet = TestUtil.createCommandPacket(
            "new",
            new String[][]{
                {
                    ParamChecker.PARAM_TIME,
                    "1500"
                },
                {
                    ParamChecker.PARAM_CATEGORY,
                    "tpt"
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
            command.handlePacket(packet);
            this.packet = packet;
        } catch (InsufficientParamsException exception) {
            //Fall through
        }
        return command.getCurrEntry();
    }

    /**
     * Generates an entry with manually-specified parameters.
     * @return Populated entry.
     */
    public Entry generateTypicalEntry2() {
        CreateEntryCommand command = new CreateEntryCommand();
        CommandPacket packet = TestUtil.createCommandPacket(
            "new",
            new String[][]{
                {
                    ParamChecker.PARAM_TIME,
                    "1620"
                },
                {
                    ParamChecker.PARAM_CATEGORY,
                    "bll"
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
            command.handlePacket(packet);
            this.packet = packet;
        } catch (InsufficientParamsException exception) {
            //Fall through
        }
        return command.getCurrEntry();
    }
}
