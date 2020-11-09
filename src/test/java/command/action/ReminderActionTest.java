//@author johanesrafael

package command.action;

import constants.Constants;
import org.junit.jupiter.api.Test;
import seedu.duke.Domsun;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ReminderActionTest {

    @Test
    public void act_reminderInput_testOutput() {
        Domsun d = new Domsun(false, System.out, System.in, Constants.PATH,
            Constants.TEST_TASK_FILENAME, Constants.TEST_COURSE_FILENAME);
        assertTrue(d.testSut("reminder", false, true).contains(Constants.REMINDER_HEAD));
        assertTrue(d.testSut("reminder on", false, true).contains(Constants.REMINDER_ON));
        assertTrue(d.testSut("reminder off", false, true).contains(Constants.REMINDER_OFF));
    }
}