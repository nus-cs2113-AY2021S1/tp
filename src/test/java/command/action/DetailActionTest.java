//@@author TomLBZ

package command.action;

import constants.Constants;
import org.junit.jupiter.api.Test;
import seedu.duke.Domsun;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DetailActionTest {
    private String[] testCommand = {"detail -mod CS2113", "detail -mod ST2334"};
    Domsun domsun = new Domsun(false, System.out, System.in, Constants.PATH,
        Constants.TEST_TASK_FILENAME, Constants.TEST_COURSE_FILENAME);

    @Test
    public void containsModuleTest() {
        domsun.testSut("focus list", false, false);
        domsun.testSut("clear", true, false);
        assertTrue(domsun.testSut(testCommand[0], false, true)
            .contains("CS2113"));
    }

    //@Test
    //public void doesNotContainModuleTest() {
    //    assertEquals(Constants.DETAIL_HEAD + Constants.NOT_FOUND,
    //        duke.testSut(testCommand[1],false,true));
    //}
}
