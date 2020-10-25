package seedu.financeit.datatrackers.entrytracker;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.testutil.TestUtil;
import seedu.financeit.utils.ParamChecker;

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
                    ParamChecker.PARAM_TIME,
                    paramTime
                },
                {
                    ParamChecker.PARAM_CATEGORY,
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
                    ParamChecker.PARAM_INDEX,
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
                    ParamChecker.PARAM_INDEX,
                    "100000000000000"
                }
            }
        );
        return testPacket;
    }

    public static CommandPacket generateCreateEntryCorrectCommand(int seed) {
        String paramTime = ("".concat(String.valueOf(seed % 24))) ;
        String paramCat = "tpt";
        String paramAmt = "1500";
        CommandPacket testPacket = TestUtil.createCommandPacket(
            "edit",
            new String[][] {
                {
                    ParamChecker.PARAM_TIME,
                    paramTime
                },
                {
                    ParamChecker.PARAM_CATEGORY,
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
        String paramTime = ("".concat(String.valueOf(seed % 24))) ;
        String paramCat = "tpt";
        String paramAmt = "1500";
        CommandPacket testPacket = TestUtil.createCommandPacket(
            "edit",
            new String[][] {
                {
                    ParamChecker.PARAM_INDEX,
                    "1"
                },
                {
                    ParamChecker.PARAM_TIME,
                    paramTime
                },
                {
                    ParamChecker.PARAM_CATEGORY,
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
}
