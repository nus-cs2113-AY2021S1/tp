package seedu.financeit.datatrackers.recurringtracker;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.testutil.TestUtil;
import seedu.financeit.utils.RandomGenerator;

import static seedu.financeit.utils.ParamChecker.PARAM_DAY;
import static seedu.financeit.utils.ParamChecker.PARAM_INC;
import static seedu.financeit.utils.ParamChecker.PARAM_EXP;
import static seedu.financeit.utils.ParamChecker.PARAM_DESCRIPTION;
import static seedu.financeit.utils.ParamChecker.PARAM_AUTO;
import static seedu.financeit.utils.ParamChecker.PARAM_AMOUNT;
import static seedu.financeit.utils.ParamChecker.PARAM_NOTES;

public class TestCommands {

    static final String[] sampleValidParamTypes = new String[] {"-e", "/desc", "/amt", "/day", "/notes"};
    static final String[] sampleValidParamArguments = new String[] {"", "Test23123//>?>_+_~#$#@",
        "3490.34", "15", "OIYH(*^(*ot9w3848(*&(*~~||///"};

    public static CommandPacket[] generateCreateEntryMissingParamsCommands() {
        CommandPacket[] packets = new CommandPacket[4];
        //Do a for loop to vary which params are missing
        //Only loop 4 times, as there are 4 compulsory params
        for (int i = 0; i < 4; i++) {
            String[] paramTypes = sampleValidParamTypes.clone();
            String[] paramArguments = sampleValidParamArguments.clone();
            paramTypes[i] = "missingParam";
            paramArguments[i] = "missingArgument";
            packets[i] = TestUtil.createCommandPacket("new", paramTypes, paramArguments);
        }
        return packets;
    }

    public static CommandPacket generateCreateCorrectEntryCommandAutoIncome() {
        String paramDay = String.valueOf(RandomGenerator.generateRandomDayOfMonth());
        String paramDescription = RandomGenerator.generateRandomString();
        String paramAmt = String.valueOf(RandomGenerator.generateRandomPositiveDouble());
        String paramNotes = RandomGenerator.generateRandomString();
        CommandPacket testPacket = TestUtil.createCommandPacket(
                "new",
                new String[][] {
                        { PARAM_INC, "" },
                        { PARAM_AUTO, "" },
                        { PARAM_DAY, paramDay },
                        { PARAM_DESCRIPTION, paramDescription },
                        { PARAM_AMOUNT, paramAmt },
                        { PARAM_NOTES, paramNotes }
                }
        );
        return testPacket;
    }

    public static CommandPacket generateCreateCorrectEntryCommandManualExpenditure() {
        String paramDay = String.valueOf(RandomGenerator.generateRandomPositiveIntUntilLimit(31));
        String paramDescription = RandomGenerator.generateRandomString();
        String paramAmt = String.valueOf(RandomGenerator.generateRandomPositiveDouble());
        String paramNotes = RandomGenerator.generateRandomString();
        CommandPacket testPacket = TestUtil.createCommandPacket(
                "new",
                new String[][] {
                        { PARAM_EXP, "" },
                        { PARAM_DAY, paramDay },
                        { PARAM_DESCRIPTION, paramDescription },
                        { PARAM_AMOUNT, paramAmt },
                        { PARAM_NOTES, paramNotes }
                }
        );
        return testPacket;
    }

}
