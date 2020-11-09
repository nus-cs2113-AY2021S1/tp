package command.action;

import constants.Constants;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import seedu.duke.Domsun;

class LoadActionTest {

    private Domsun domsun = new Domsun(false, System.out, System.in, Constants.PATH,
        Constants.TEST_TASK_FILENAME, Constants.TEST_COURSE_FILENAME);

    @Test
    public void act_userModule_calculatedCapValue() {
        domsun.testSut("clear", true, true);
        domsun.testSut("deadline watch Spongebob roundpants s3 -by 22-01-2001 12:00", false, false);
        domsun.testSut("todo eat banana while watching Minions s4", false, false);
        String[] testCustomInputsCommand = {
            "load geq1000 [D][X]_watch_Spongebob_roundpants_s3_(by:_Jan_22_2001_12:00)",
            "load geq1000 [T][X]_watch_Spongebob_roundpants_s3_(by:_Jan 22_2001_12:00)",
            "load cs9999 [T][X]_eat_banana_while_watching_Minions_s4"
        };
        String[] expectedOutput = {
            "Added Tasks: \r\n"
                + "[D][X] watch Spongebob roundpants s3 (by: Jan 22 2001 12:00)\r\n"
                + "for module: GEQ1000;\r\n"
                + "Try \"detail GEQ1000\" to check it out!\r\n",

            "Invalid Command! Please check the syntax.\r\n"
                + "    load [module code] [task_string] ...\r\n",

            "Your specified module is not found in the current list.\r\n"
                + "    load [module code] [task_string] ...\r\n",
        };
        assertAll("Load action test",
            () -> assertEquals(expectedOutput[0], domsun.testSut(testCustomInputsCommand[0], false, true),
                "Normal task module link"),
            () -> assertTrue(domsun.testSut(testCustomInputsCommand[1], false, true)
                .contains(expectedOutput[1]), "Non-existing task modules link"),
            () -> assertTrue(domsun.testSut(testCustomInputsCommand[2], false, true)
                .contains(expectedOutput[2]), "Non-existing module task link")
        );
        domsun.testSut("clear", true, false);
    }
}

