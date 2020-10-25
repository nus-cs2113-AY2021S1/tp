package seedu.financeit.manualtracker.subroutine;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.testutil.TestUtil;
import seedu.financeit.utils.ParamChecker;

import static seedu.financeit.manualtracker.subroutine.TestCommands.generateCreateEntryCorrectCommand;

public class TypicalEntryEntries {
    static CommandPacket packet;
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

    public Entry generateTypicalEntryFromSeed() {
        Entry entry = new Entry();
        try {
            entry.handlePacket(this.packet);
        } catch (InsufficientParamsException exception) {
            //Fall through
        }
        return entry;
    }

    public Entry generateTypicalEntry1() {
        Entry entry = new Entry();
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
            entry.handlePacket(packet);
            this.packet = packet;
        } catch (InsufficientParamsException exception) {
            //Fall through
        }
        return entry;
    }

    public Entry generateTypicalEntry2() {
        Entry entry = new Entry();
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
            entry.handlePacket(packet);
            this.packet = packet;
        } catch (InsufficientParamsException exception) {
            //Fall through
        }
        return entry;
    }
}
