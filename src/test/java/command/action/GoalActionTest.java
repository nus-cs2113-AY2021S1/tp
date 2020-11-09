//@@ adinata15

package command.action;

import constants.Constants;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import seedu.duke.Domsun;

class GoalActionTest {

    private Domsun domsun = new Domsun(false, System.out, System.in, Constants.PATH,
        Constants.TEST_TASK_FILENAME, Constants.TEST_COURSE_FILENAME);

    private String[] testCommand =
        {"goal -total 160 4.6 -taken 100 4.5",
            "goal -total 160 4.9 -taken 100 4.5",
            "goal -total 220 4.9 -taken 225 4.5",
            "goal -total 160 4.9 -taken 159 5.0",
            "goal -total 160 4.8"
        };

    @Test
    public void act_customModuleInputs_requiredCapValue() {
        String[] expectedOutput = {
            "Your required average CAP is: 4.77\r\n"
                + "Try \"cap\" to see your current cap!\r\n"
                + "Jia you! :D\r\n",

            "Your required average CAP is: 5.57\r\n"
                + "Try \"cap\" to see your current cap!\r\n"
                + "Looks like the target is a bit far away QAQ\r\n",

            "You have taken MC sufficient for your graduation! Goal is reached :D\r\n",

            "Your required average CAP is: -11\r\n"
                + "Try \"cap\" to see your current cap!\r\n"
                + "This means you need to lower your grade to achieve your goal. Tank the bell curve for us! :D\r\n",
        };

        String testCustomInputsCommand = "cap CS2113 A CG1112 A-";
        assertAll("Goal action test",
            () -> assertEquals(expectedOutput[0], domsun.testSut(testCommand[0], false, true),
                "Within target"),
            () -> assertEquals(expectedOutput[1], domsun.testSut(testCommand[1], false, true),
                "High target"),
            () -> assertEquals(expectedOutput[2], domsun.testSut(testCommand[2], false, true),
                "Reached goal"),
            () -> assertEquals(expectedOutput[3], domsun.testSut(testCommand[3], false, true),
                "Low target")
        );
    }

    @Test
    public void act_userModule_requiredCapValue() {
        domsun.testSut("take cs1231 ma1513 cg1112", false, false);
        domsun.testSut("grade cs1231 a ma1513 b cg1112 c", false, false);
        String expectedOutput =
            "Your required average CAP is: 4.93\r\n"
                + "Try \"cap\" to see your current cap!\r\n"
                + "Jia you! :D\r\n";
        assertAll("Goal action test",
            () -> assertEquals(expectedOutput, domsun.testSut(testCommand[4], false, true),
                "User grade and modules")
        );
        domsun.testSut("grade cs1231 ma1513 cg1112", false, false);
        domsun.testSut("untake cs1231 ma1513 cg1112", false, false);
    }
}