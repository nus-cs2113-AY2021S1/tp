package command.action;

import constants.Constants;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import seedu.duke.Domsun;

/**
 * Test for grade actions.
 */
class ModuleActionTest {

    private Domsun domsun = new Domsun(false, System.out, System.in, Constants.PATH,
        Constants.TEST_TASK_FILENAME, Constants.TEST_COURSE_FILENAME);

    @Test
    public void actComplete_ModuleInputs_completedResponseMessage() {
        String[] testCustomInputsCommand = {
            "take Cs2040C ma1508e ee1001x",
            "complete Cs2040C ma1508e",
            "edit -mod CS2040 grade=A",
        };
        String[] expectedOutput = {
            "Your \"taken\" list has been changed, \"list\" it again to see effects.\r\n"
                + "Module: CS2040C: now taken\r\n"
                + "Module: EE1001X: now taken\r\n"
                + "Module: MA1508E: now taken\r\n",

            "Marking the specified module as Completed (final): \r\n"
                + "Module: [COMPLETED]CS2040C: now completed\r\n"
                + "Module: [COMPLETED]MA1508E: now completed\r\n",

            "Trying to modify the attribute(s) you specified:\r\nWorking on Module: CS2040\r\n"
                + "CS2040: grade = A AND taken = true;\r\n(The module must be taken in order to have a grade);\r\n",
        };
        assertEquals(expectedOutput[0], domsun.testSut(testCustomInputsCommand[0], false, true),
            "Take normal module");
        domsun.testSut("grade Cs2040C a ma1508e b ee1001x c+", false, false);
        assertEquals(expectedOutput[1], domsun.testSut(testCustomInputsCommand[1], false, true),
            "Complete normal module");
        assertEquals(expectedOutput[2], domsun.testSut(testCustomInputsCommand[2], false, true),
            "Edit graded module");

        domsun.testSut("grade cs2040c ma1508e ee1001x", false, false);
        domsun.testSut("untake cs2040c ma1508e ee1001x", false, false);
    }

}