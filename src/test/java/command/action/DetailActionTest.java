//@@author TomLBZ

package command.action;

import constants.Constants;
import org.junit.jupiter.api.Test;
import seedu.duke.Domsun;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DetailActionTest {
    private String[] testCommand = {"detail -mod CS2113", "detail -mod ST2999"};
    Domsun domsun = new Domsun(false, System.out, System.in, Constants.PATH,
        Constants.TEST_TASK_FILENAME, Constants.TEST_COURSE_FILENAME);

    @Test
    public void act_listedModuleTest_moduleDetail() {
        domsun.testSut("focus list", false, false);
        domsun.testSut("clear", true, false);
        assertTrue(domsun.testSut(testCommand[0], false, true)
            .contains("CS2113"));
    }
    //@@author

    @Test
    public void act_unlistedModuleTest_notFoundMessage() {
        assertEquals(Constants.DETAIL_HEAD + Constants.NOT_FOUND,
            domsun.testSut(testCommand[1],false,true));
    }
}
