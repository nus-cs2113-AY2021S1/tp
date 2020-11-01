package seedu.financeit.datatrackers.manualtracker;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.testutil.TestUtil;

import static seedu.financeit.utils.ParamChecker.PARAM_DATE;
import static seedu.financeit.utils.ParamChecker.PARAM_INDEX;

public class TestCommands {
    public static CommandPacket generateCreateLedgerErrorCommand() {
        CommandPacket testPacket = TestUtil.createCommandPacket(
            "new",
            new String[][] {
                {
                    PARAM_DATE,
                    "2020-25-4"
                }
            }
        );
        return testPacket;
    }

    public static CommandPacket generateDeleteLedgerByDateCorrectCommand() {
        CommandPacket testPacket = TestUtil.createCommandPacket(
            "delete",
            new String[][] {
                {
                    PARAM_DATE,
                    "2020-5-4"
                }
            }
        );
        return testPacket;
    }

    public static CommandPacket generateDeleteLedgerByDateErrorCommand() {
        CommandPacket testPacket = TestUtil.createCommandPacket(
            "delete",
            new String[][] {
                {
                    PARAM_DATE,
                    "2020-5-400"
                }
            }
        );
        return testPacket;
    }

    public static CommandPacket generateDeleteLedgerByIdCorrectCommand() {
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

    public static CommandPacket generateDeleteLedgerByIdErrorCommand() {
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

    public static CommandPacket generateCreateLedgerCorrectCommand(int seed) {
        CommandPacket testPacket = TestUtil.createCommandPacket(
            "new",
            new String[][] {
                {
                    PARAM_DATE,
                    ("2020-".concat(String.valueOf(1 + seed % 12) + "-" + String.valueOf(1 + seed % 31)))
                }
            }
        );
        return testPacket;
    }
}

