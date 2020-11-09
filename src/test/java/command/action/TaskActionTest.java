//@@author adinata15

package command.action;

import constants.Constants;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import seedu.duke.Domsun;

/**
 * Tests for various task actions.
 */
class TaskActionTest {

    private Domsun domsun = new Domsun(false, System.out, System.in, Constants.PATH,
        Constants.TEST_TASK_FILENAME, Constants.TEST_COURSE_FILENAME);

    @Test
    public void actClear_normalCase_clearResponseMessage() {
        String expectedOutput = "Nice! I've cleared all tasks from the list and left modules alone.";
        String testCustomInputsCommand = "clear";

        assertAll("McActionTest",
            () -> assertEquals(expectedOutput, domsun.testSut(testCustomInputsCommand, true, true),
                "Clear fails")
        );
    }

    @Test
    public void actDelete_normalCase_deleteResponseMessage() {
        domsun.testSut("clear", true, false);
        domsun.testSut("todo eat ice cream", true, false);
        domsun.testSut("deadline eat -by Tuesday", true, false);
        String[] expectedOutput = {
            "Noted. I've removed this task:\r\n"
                + "[T][X] eat ice cream\r\n"
                + "Now you have 1 tasks in the list.",

            "Index out of range. Index must be a positive integer referencing an existing item.\r\n"
        };
        String[] testCustomInputsCommand = {"delete 1", "delete 10"};
        assertAll("Delete action test",
            () -> assertEquals(expectedOutput[0], domsun.testSut(testCustomInputsCommand[0], true, true),
                "Normal input"),
            () -> assertEquals(expectedOutput[1], domsun.testSut(testCustomInputsCommand[1], true, true),
                "Out of bound")
        );
    }

    @Test
    public void actDeadline_randomByTime_customTimeDeadline() {
        domsun.testSut("clear", true, true);
        String expectedOutput = "Got it. I've added this task:\r\n"
            + "[D][X] watch powerRangers episode 2 (by: TODAY!!)\r\n"
            + "Now you have 1 tasks in the list.";
        String testCustomInputsCommand = "deadline watch powerRangers episode 2 -by TODAY!!";
        assertAll("Deadline random time",
            () -> assertEquals(expectedOutput, domsun.testSut(testCustomInputsCommand, false, true),
                "Deadline custom time inputs fails")
        );
    }

    @Test
    public void actDeadline_dateTimeFormat_formattedTimeDeadline() {
        domsun.testSut("clear", true, true);
        String expectedOutput = "Got it. I've added this task:\r\n"
            + "[D][X] watch powerRangers episode 3 (by: Feb 23 2022 09:00)\r\n"
            + "Now you have 1 tasks in the list.";
        String testCustomInputsCommand = "deadline watch powerRangers episode 3 /by 2022-02-23 09:00";
        assertAll("Deadline date time",
            () -> assertEquals(expectedOutput, domsun.testSut(testCustomInputsCommand, false, true),
                "Deadline format time inputs fails"));
    }

    @Test
    public void actTodo_normalTodo_todoRespondMessage() {
        domsun.testSut("clear", true, true);
        String expectedOutput = "Got it. I've added this task:\r\n"
            + "[T][X] finish CS2113 Project!\r\n"
            + "Now you have 1 tasks in the list.";
        String testCustomInputsCommand = "todo finish CS2113 Project!";
        assertAll("Todo",
            () -> assertEquals(expectedOutput, domsun.testSut(testCustomInputsCommand, false, true),
                "Todo normal inputs fails"));
    }

    @Test
    public void actEvent_randomAtTime_customTimeDeadline() {
        domsun.testSut("clear", true, true);
        String expectedOutput = "Got it. I've added this task:\r\n"
            + "[E][X] watch Dora The Explorer episode 2 (at: your Free Time :))\r\n"
            + "Now you have 1 tasks in the list.";
        String testCustomInputsCommand = "event watch Dora The Explorer episode 2 -at your Free Time :)";
        assertAll("Event random time",
            () -> assertEquals(expectedOutput, domsun.testSut(testCustomInputsCommand, false, true),
                "Event custom time inputs fails")
        );
    }

    @Test
    public void actEvent_dateTimeFormat_formattedTimeDeadline() {
        domsun.testSut("clear", true, true);
        String expectedOutput = "Got it. I've added this task:\r\n"
            + "[E][X] watch Dora The Explorer episode 3 (at: Feb 23 2022 00:00)\r\n"
            + "Now you have 1 tasks in the list.";
        String testCustomInputsCommand = "event watch Dora The Explorer episode 3 /at 2022-02-23";
        assertAll("Event custom time",
            () -> assertEquals(expectedOutput, domsun.testSut(testCustomInputsCommand, false, true),
                "Event format time inputs fails")
        );
    }

    @Test
    public void actDone_indexInList_doneResponseMessage() {
        domsun.testSut("clear", true, true);
        domsun.testSut("deadline watch Spongebob roundpants s3 /by 22-01-2001 12:00", false, false);
        domsun.testSut("todo eat banana while watching Minions s4", false, false);
        domsun.testSut("event Tinder meetingg!! /at THE END OF TIME TT", false, false);

        String expectedOutput = "Nice! I've marked this task as done:\r\n"
            + "[D][V] watch Spongebob roundpants s3 (by: Jan 22 2001 12:00)";
        String testCustomInputsCommand = "done 1";
        assertAll("Mark done",
            () -> assertEquals(expectedOutput, domsun.testSut(testCustomInputsCommand, false, true),
                "Mark done normal fails")
        );
    }

    @Test
    public void actList_filledList_listResponseMessage() {
        domsun.testSut("clear", true, true);
        domsun.testSut("deadline watch Spongebob roundpants s3 /by 22-01-2001 12:00", false, false);
        domsun.testSut("todo eat banana while watching Minions s4", false, false);
        domsun.testSut("event Tinder meetingg!! /at THE END OF TIME TT", false, false);

        String expectedOutput = "Here is the list of items:\r\n"
            + "[D][X] watch Spongebob roundpants s3 (by: Jan 22 2001 12:00)\r\n"
            + "[T][X] eat banana while watching Minions s4\r\n"
            + "[E][X] Tinder meetingg!! (at: THE END OF TIME TT)\r\n";
        String testCustomInputsCommand = "list";
        assertAll("List normal tasks",
            () -> assertEquals(expectedOutput, domsun.testSut(testCustomInputsCommand, false, true),
                "List normal tasks fails")
        );
    }

    @Test
    public void actUndone_indexInList_undoneResponseMessage() {
        domsun.testSut("clear", true, true);
        domsun.testSut("deadline watch Spongebob round pants s3 /by 22-01-2001 12:00", false, false);
        domsun.testSut("todo eat banana while watching Minions s4", false, false);
        domsun.testSut("event Tinder meetingg!! /at THE END OF TIME TT", false, false);
        domsun.testSut("done 1", false, false);//mark first item as done

        String expectedOutput = "Nice! I've marked this task as undone:\r\n"
            + "[D][X] watch Spongebob round pants s3 (by: Jan 22 2001 12:00)";
        String testCustomInputsCommand = "undone 1";
        assertAll("Mark undone",
            () -> assertEquals(expectedOutput, domsun.testSut(testCustomInputsCommand, false, true),
                "Mark undone normal fails")
        );
    }
}
