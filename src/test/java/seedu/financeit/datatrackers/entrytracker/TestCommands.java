package seedu.financeit.datatrackers.entrytracker;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.testutil.TestUtil;
import seedu.financeit.utils.ParamChecker;
import seedu.financeit.utils.RandomGenerator;

import static seedu.financeit.utils.ParamChecker.PARAM_AMOUNT;
import static seedu.financeit.utils.ParamChecker.PARAM_CATEGORY;
import static seedu.financeit.utils.ParamChecker.PARAM_DESCRIPTION;
import static seedu.financeit.utils.ParamChecker.PARAM_INC;
import static seedu.financeit.utils.ParamChecker.PARAM_INDEX;
import static seedu.financeit.utils.ParamChecker.PARAM_TIME;

public class TestCommands {
    public static CommandPacket generateCreateEntryErrorCommand(String wrongParam) {
        int seed = RandomGenerator.generateRandomPositiveIntUntilLimit(3);
        String paramTime = (wrongParam == "time")
            ? (seed == 0) ? "5600"
            : (seed == 1) ? "-1200"
            : " -&&*&*(-"
            : "1500";

        String paramCat = (wrongParam == "cat")
            ? "wrong"
            : "oth";

        String paramAmt = (wrongParam == "amt")
            ? (seed == 0) ? String.format("%f", (Double.MAX_VALUE - 1))
            : (seed == 1) ? "-1200"
            : (seed == 2) ? " -&&*&*(-"
            : "4e2"
            : "5600";

        CommandPacket testPacket = TestUtil.createCommandPacket(
            "new",
            new String[][] {
                {
                    PARAM_TIME,
                    paramTime
                },
                {
                    PARAM_CATEGORY,
                    paramCat
                },
                {
                    ParamChecker.PARAM_INC,
                    ""
                },
                {
                    ParamChecker.PARAM_DESCRIPTION,
                    "test description"
                },
                {
                    ParamChecker.PARAM_AMOUNT,
                    paramAmt
                }
            }
        );
        return testPacket;
    }

    public static CommandPacket generateDeleteEntryByIdCorrectCommand() {
        CommandPacket testPacket = TestUtil.createCommandPacket(
            "delete",
            new String[][] {
                {
                    PARAM_INDEX,
                    "1"
                }
            }
        );
        return testPacket;
    }

    public static CommandPacket generateDeleteEntryByIdErrorCommand() {
        int seed = RandomGenerator.generateRandomPositiveIntUntilLimit(3);
        String wrongInput = (seed == 1) ? "-1000000000000"
            : (seed == 2) ? "100000000000000"
            : (seed == 3) ? "-5"
            : "4e2";
        CommandPacket testPacket = TestUtil.createCommandPacket(
            "delete",
            new String[][] {
                {
                    PARAM_INDEX,
                    wrongInput
                }
            }
        );
        return testPacket;
    }

    public static CommandPacket generateCreateEntryCorrectCommand(int seed) {
        // Generates entries with time range from 0000 to 2400 in 24-h intervals.
        String paramTime = "1500";
        String paramCat = "oth";
        String paramAmt = String.valueOf(1500 + seed);
        CommandPacket testPacket = TestUtil.createCommandPacket(
            "edit",
            new String[][] {
                {
                    PARAM_TIME,
                    paramTime
                },
                {
                    PARAM_CATEGORY,
                    paramCat
                },
                {
                    ParamChecker.PARAM_INC,
                    ""
                },
                {
                    ParamChecker.PARAM_DESCRIPTION,
                    "test description"
                },
                {
                    ParamChecker.PARAM_AMOUNT,
                    paramAmt
                }
            }
        );
        return testPacket;
    }

    public static CommandPacket generateEditEntryCorrectCommand(int seed) {
        // Generates entries with time range from 0000 to 2400 in 24-h intervals.
        String paramTime = "1500";
        String paramCat = "oth";
        String paramAmt = String.valueOf(1500 + seed);
        CommandPacket testPacket = TestUtil.createCommandPacket(
            "edit",
            new String[][] {
                {
                    PARAM_INDEX,
                    "1"
                },
                {
                    PARAM_TIME,
                    paramTime
                },
                {
                    PARAM_CATEGORY,
                    paramCat
                },
                {
                    PARAM_INC,
                    ""
                },
                {
                    PARAM_DESCRIPTION,
                    "test description"
                },
                {
                    PARAM_AMOUNT,
                    paramAmt
                }
            }
        );
        return testPacket;
    }
}
