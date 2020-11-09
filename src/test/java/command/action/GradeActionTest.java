package command.action;

import constants.Constants;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import seedu.duke.Domsun;

/**
 * Test for grade actions.
 */
class GradeActionTest {

    private Domsun domsun = new Domsun(false, System.out, System.in, Constants.PATH,
        Constants.TEST_TASK_FILENAME, Constants.TEST_COURSE_FILENAME);

    @Test
    public void act_ModuleInputs_addedResponseMessage() {
        domsun.testSut("take cs2040c ma1508e ee1001x", false, true);
        String expectedOutput =
            "Grade operation on the specified modules:\r\n"
            + "Module: CS2040C: D\r\n"
            + "Module: EE1001X: B+\r\n"
            + "Module: MA1508E: A-\r\n";
        String testCustomInputsCommand = "grade Cs2040C d ma1508e a- ee1001x b+";
        assertEquals(expectedOutput, domsun.testSut(testCustomInputsCommand, false, true),
            "Add grade CAP custom input fails");
        domsun.testSut("grade cs2040c ma1508e ee1001x", false, false);
        domsun.testSut("untake cs2040c ma1508e ee1001x", false, false);
    }
}