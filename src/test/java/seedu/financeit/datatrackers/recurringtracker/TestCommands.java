package seedu.financeit.datatrackers.recurringtracker;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.testutil.TestUtil;
import seedu.financeit.utils.RandomGenerator;

import static seedu.financeit.utils.ParamChecker.*;


public class TestCommands {

    public static CommandPacket generateCreateEntryErrorCommand(String wrongParam) {
        String paramAmt = (wrongParam == "amt")
                ? "-343478.56456"
                : "43554.22197";
        String paramDay = (wrongParam == "day")
                ? "0"
                : "26";
        CommandPacket testPacket = TestUtil.createCommandPacket(
                "new",
                new String[][] {
                        {
                                PARAM_DAY,
                                paramDay
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
                                PARAM_AUTO,
                                ""
                        },
                        {
                                PARAM_AMOUNT,
                                paramAmt
                        },
                        {
                                PARAM_NOTES,
                                "aslk(*& 67687 687^*& ^*&&*7334"
                        }
                }
        );
        return testPacket;
    }

    public static CommandPacket generateCreateCorrectEntryCommandAutoIncome() {
        String paramDay = String.valueOf(RandomGenerator.generateRandomPositiveIntUntilLimit(31));
        String paramDescription = RandomGenerator.generateRandomString();
        String paramAmt = String.valueOf(RandomGenerator.generateRandomDouble());
        String paramNotes = RandomGenerator.generateRandomString();
        CommandPacket testPacket = TestUtil.createCommandPacket(
                "new",
                new String[][] {
                        {
                                PARAM_INC,
                                ""
                        },
                        {
                                PARAM_AUTO,
                                ""
                        },
                        {
                                PARAM_DAY,
                                paramDay
                        },
                        {
                                PARAM_DESCRIPTION,
                                paramDescription
                        },
                        {
                                PARAM_AMOUNT,
                                paramAmt
                        },
                        {
                                PARAM_NOTES,
                                paramNotes
                        }
                }
        );
        return testPacket;
    }
}
