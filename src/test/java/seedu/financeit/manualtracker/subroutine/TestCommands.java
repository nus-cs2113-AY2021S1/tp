package seedu.financeit.manualtracker.subroutine;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.testutil.TestUtil;
import seedu.financeit.utils.ParamChecker;

public class TestCommands {
    public static CommandPacket generateCreateEntryErrorCommand() {
        CommandPacket testPacket = TestUtil.createCommandPacket(
            "new",
            new String[][] {
                {
                    ParamChecker.PARAM_DATE,
                    "2020-25-4"
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
        CommandPacket testPacket = TestUtil.createCommandPacket(
            "new",
            new String[][] {
                {
                    ParamChecker.PARAM_DATE,
                    ("2020-5-".concat(String.valueOf(seed)))
                }
            }
        );
        return testPacket;
    }
}
