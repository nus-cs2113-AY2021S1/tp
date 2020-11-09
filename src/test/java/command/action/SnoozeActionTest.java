package command.action;

import constants.Constants;
import org.junit.jupiter.api.Test;
import seedu.duke.Domsun;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SnoozeActionTest {
    private String[] testCommand = {"snooze", "snooze 5", "snooze bye"};

    @Test
    void act_customFocusInput_testOutput() {
        Domsun d = new Domsun(false, System.out, System.in, Constants.PATH,
                Constants.TEST_TASK_FILENAME, Constants.TEST_COURSE_FILENAME);
        assertTrue(d.testSut(testCommand[0], false, true).contains("I've snoozed the reminder for 1"
            + " minute. Will remind you in 6 minutes."));
        assertTrue(d.testSut(testCommand[1], false, true).contains(Constants.INVALID));
        assertTrue(d.testSut(testCommand[2], false, true).contains(Constants.INVALID));
    }
}
