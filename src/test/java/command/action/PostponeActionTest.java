package command.action;

import constants.Constants;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import seedu.duke.Domsun;

class PostponeActionTest {

    private String[] testCommand = {"postpone ", "postpone 1", "postpone 5", "postpone h 1", "postpone a 1",
        "postpone", "postpone 2", "postpone 3", "postpone w 3",};
    private Domsun domsun = new Domsun(false, System.out, System.in, Constants.PATH,
            Constants.TEST_TASK_FILENAME, Constants.TEST_COURSE_FILENAME);

    @Test
    void act_postponeInput_testOutput() {
        domsun.testSut("clear", true, true);
        domsun.testSut("deadline e /by 2020-11-11 08:30", false, false);
        assertAll("PostponeActionTest",
            () -> assertTrue(domsun.testSut(testCommand[0], false, true)
                .contains(Constants.INVALID)),
            () -> assertTrue(domsun.testSut(testCommand[1], false, true)
                .contains("I've postpone this task:")),
            () -> assertTrue(domsun.testSut(testCommand[2], false, true)
                .contains(Constants.INDEX_OUT)),
            () -> assertTrue(domsun.testSut(testCommand[3], false, true)
                .contains("I've postpone this task:")),
            () -> assertTrue(domsun.testSut(testCommand[4], false, true)
                .contains(Constants.INVALID))
        );
        domsun.testSut("todo eat banana while watching Minions s4", false, false);
        domsun.testSut("deadline watch Spongebob roundpants s3 -by 22-01-2001 12:00", false, false);
        String[] expectedOutput = {
            "Invalid Command! Please check the syntax.\r\n"
                + "    postpone [index]\r\n"
                + "    postpone (h / w / y) [index]\r\n",

            "Sorry, there is no date in todo task!\r\n",

            "I've postpone this task:\r\n"
                + "[D][X] watch Spongebob roundpants s3 (by: Jan 23 2001 12:00)",

            "I've postpone this task:\r\n"
                + "[D][X] watch Spongebob roundpants s3 (by: Jan 30 2001 12:00)",
        };

        assertAll("PostponeActionTest",
            () -> assertTrue(domsun.testSut(testCommand[5], true, true)
                .contains(expectedOutput[0]), "Invalid command"),
            () -> assertEquals(expectedOutput[1], domsun.testSut(testCommand[6], false, true),
                "Invalid task type (todo)"),
            () -> assertEquals(expectedOutput[2], domsun.testSut(testCommand[7], false, true),
                "Normal input default"),
            () -> assertEquals(expectedOutput[3], domsun.testSut(testCommand[8], false, true),
                "Normal input week")
        );
        domsun.testSut("clear", true, true);
    }
}