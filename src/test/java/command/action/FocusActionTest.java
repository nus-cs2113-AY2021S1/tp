//@@author TomLBZ

package command.action;

import constants.Constants;
import org.junit.jupiter.api.Test;
import seedu.duke.Domsun;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Focus Action Test.
 */
class FocusActionTest {
    //@author: johanesrafael
    private String[] testCommand = {"focus", "focus deadline", "focus todo", "focus event", "focus task",
                                    "focus mod", "focus selected", "focus taken", "focus asdsds"};

    @Test
    void act_customFocusInput_testOutput() {
        Domsun d = new Domsun(false, System.out, System.in, Constants.PATH,
            Constants.TEST_TASK_FILENAME, Constants.TEST_COURSE_FILENAME);
        assertAll("FocusActionTest",
            () -> assertTrue(d.testSut(testCommand[0], false, true)
                .equals("Now we are focusing on:\r\ntask")),
            () -> assertTrue(d.testSut(testCommand[1], false, true)
                .equals("Now we are focusing on:\r\ndeadline")),
            () -> assertTrue(d.testSut(testCommand[2], false, true)
                .equals("Now we are focusing on:\r\ntodo")),
            () -> assertTrue(d.testSut(testCommand[3], false, true)
                .equals("Now we are focusing on:\r\nevent")),
            () -> assertTrue(d.testSut(testCommand[4], false, true)
                .equals("Now we are focusing on:\r\ntask")),
            () -> assertTrue(d.testSut(testCommand[5], false, true)
                .equals("Now we are focusing on:\r\nmod")),
            () -> assertTrue(d.testSut(testCommand[6], false, true)
                .equals("Now we are focusing on:\r\nselected")),
            () -> assertTrue(d.testSut(testCommand[7], false, true)
                .equals("Now we are focusing on:\r\ntaken")),
            () -> assertTrue(d.testSut(testCommand[8], false, true)
                .contains("Invalid Command! Please check the syntax.\r\n    focus\r\n"
                        + "    focus [deadline / todo / event / task / mod / selected / taken]\r\nNotes:\r\n"))
        );
    }
}