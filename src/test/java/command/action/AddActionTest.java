package command.action;

import constants.Constants;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import seedu.duke.Domsun;

class AddActionTest {

    private Domsun domsun = new Domsun(false, System.out, System.in, Constants.PATH,
        Constants.TEST_TASK_FILENAME, Constants.TEST_COURSE_FILENAME);

    @Test
    public void act_userModule_calculatedCapValue() {
        domsun.testSut("take cs1231 ma1513 cg1112", false, false);
        domsun.testSut("grade cs1231 a ma1513 b cg1112 c", false, false);
        domsun.testSut("clear", true, true);
        domsun.testSut("deadline watch Spongebob roundpants s3 /by 22-01-2001 12:00", false, false);
        domsun.testSut("todo eat banana while watching Minions s4", false, false);
        String[] testCustomInputsCommand = {"add -task 1 -mod CS1231", "add -task 2 -mod CS1231 ma1513"};
        String[] expectedOutput = {
            "I have added the specified tasks to the specified modules.\r\n"
                + "CS1231 << tasks: watch Spongebob roundpants s3; \r\n",

            "I have added the specified tasks to the specified modules.\r\n"
                + "CS1231 << tasks: eat banana while watching Minions s4; \r\n",
        };
        assertAll("Add action test",
            () -> assertEquals(expectedOutput[0], domsun.testSut(testCustomInputsCommand[0], false, true),
                "Deadline module link"),
            () -> assertEquals(expectedOutput[1], domsun.testSut(testCustomInputsCommand[1], false, true),
                "Todo multiple modules link")
        );
        domsun.testSut("grade cs1231 ma1513 cg1112", false, false);
        domsun.testSut("untake cs1231 ma1513 cg1112", false, false);
        domsun.testSut("clear", true, false);
    }
}