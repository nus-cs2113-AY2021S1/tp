package seedu.financeit.datatrackers.entrytracker;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.testutil.TestUtil;
import seedu.financeit.utils.ParamChecker;

import static seedu.financeit.utils.ParamChecker.PARAM_AMOUNT;
import static seedu.financeit.utils.ParamChecker.PARAM_CATEGORY;
import static seedu.financeit.utils.ParamChecker.PARAM_DESCRIPTION;
import static seedu.financeit.utils.ParamChecker.PARAM_INC;
import static seedu.financeit.utils.ParamChecker.PARAM_INDEX;
import static seedu.financeit.utils.ParamChecker.PARAM_TIME;

public class TestCommands {
    public static CommandPacket generateCreateEntryErrorCommand(String wrongParam) {
        String paramTime = (wrongParam == "time")
            ? "5600"
            : "1500";
        String paramCat = (wrongParam == "cat")
            ? "wrong"
            : "tpt";
        String paramAmt = (wrongParam == "amt")
            ? String.format("%f", (Double.MAX_VALUE - 1))
            : "5600";

        System.out.println("lol: " + paramAmt.length());
        System.out.println("lol: " + paramAmt);
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
        CommandPacket testPacket = TestUtil.createCommandPacket(
            "delete",
            new String[][] {
                {
                    PARAM_INDEX,
                    "100000000000000"
                }
            }
        );
        return testPacket;
    }

    public static CommandPacket generateCreateEntryCorrectCommand(int seed) {
        // Generates entries with time range from 0000 to 2400 in 24-h intervals.
        String paramTime = ("".concat(String.valueOf(seed % 24)));
        String paramCat = "tpt";
        String paramAmt = "1500";
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
        String paramTime = ("".concat(String.valueOf(seed % 24)));
        String paramCat = "tpt";
        String paramAmt = "1500";
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
