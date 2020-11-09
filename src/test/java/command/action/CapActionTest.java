//@@ adinata15

package command.action;

import constants.Constants;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import seedu.duke.Domsun;

/**
 * Test for CAP calculator.
 */

class CapActionTest {

    private Domsun domsun = new Domsun(false, System.out, System.in, Constants.PATH,
        Constants.TEST_TASK_FILENAME, Constants.TEST_COURSE_FILENAME);

    @Test
    public void act_customModuleInputs_calculatedCapValue() {
        String expectedOutput =
            "Calculate cap on specified modules:\r\n"
                + "Module: CG1112: (hypothetical)A-\r\n"
                + "Module: CS2113: (hypothetical)A\r\n"
                + "CAP = 4.7";
        String testCustomInputsCommand = "cap CS2113 A CG1112 A-";
        assertAll("Cap action test",
            () -> assertEquals(expectedOutput, domsun.testSut(testCustomInputsCommand, false, true),
                "Custom input grade and modules")
        );
    }

    @Test
    public void act_userModule_calculatedCapValue() {
        domsun.testSut("take cs1231 ma1513 cg1112", false, false);
        domsun.testSut("grade cs1231 a ma1513 b cg1112 c", false, false);
        String expectedOutput =
            "Calculate cap on specified modules:\r\n"
                + "You did not specify modules, looking for graded modules in your taken modules...\r\n"
                + "CG1112: C\r\n"
                + "CS1231: A\r\n"
                + "MA1513: B\r\n"
                + "CAP = 3.25";
        String testCustomInputsCommand = "cap";
        assertAll("Cap action test",
            () -> assertEquals(expectedOutput, domsun.testSut(testCustomInputsCommand, false, true),
                "User grade and modules")
        );
        domsun.testSut("grade cs1231 ma1513 cg1112", false, false);
        domsun.testSut("untake cs1231 ma1513 cg1112", false, false);
    }
}