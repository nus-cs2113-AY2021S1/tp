//@@author scjx123

package command.action;

import constants.Constants;
import exceptions.CommandException;
import exceptions.InvalidCommandException;
import org.junit.jupiter.api.Test;
import seedu.duke.Domsun;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StatsActionTest {
    private String[] testCommand = {"stats", "stats -mod CS2113","stats -garbage"};
    private Boolean isMod;
    private String selectedModule;

    @Test
    public void statsAction_statsCommandsInputs_PercentageDisplayed() {
        Domsun d = new Domsun(false, System.out, System.in, Constants.PATH,
                Constants.TEST_TASK_FILENAME, Constants.TEST_COURSE_FILENAME);

        assertAll("StatsActionTest", () -> assertTrue(d.testSut(testCommand[0], false, true)
                        .contains("%")),
            () -> assertNotEquals(Constants.INVALID,d.testSut(testCommand[1], false, true).contains("[0.0%]")),
            () -> assertTrue(d.testSut(testCommand[2], false, true).contains(Constants.INVALID))
        );
    }

    @Test
    void statsAction_statsExceptionTesting_InvalidCommandDisplayed() {
        Exception exception = assertThrows(CommandException.class,
            () -> prepareTest(testCommand[2]));
        assertTrue(exception.getMessage().contains(Constants.INVALID));
    }

    @Test
    void statsAction_LongRatioAndZero_CorrectPercentage() {
        int x = 0;
        double y = 1.0999999;
        assertAll("roundedRatioBarAssertion",
            () -> assertEquals("[0.0%]", roundedRatioBar(x)),
            () -> assertEquals("[110.0%]", roundedRatioBar(y))
        );
    }

    private String roundedRatioBar(double fraction) {
        assert fraction >= 0.0 : "fraction is greater or equal to zero";
        double roundedRatio = Math.round((fraction * 100) * 10) / 10.0;
        return Constants.ICON_LEFT + roundedRatio + Constants.PERCENT + Constants.ICON_RIGHT;
    }

    public void prepareTest(String userInput) throws CommandException {
        String[] optionalParams = Constants.optionalParamMap.get("stats");
        String mod = optionalParams[0];
        isMod = userInput.contains(mod);

        if (userInput == null) {
            isMod = false;
            userInput = "";
        } else {
            if (isMod) {
                selectedModule = userInput.replace("mod", "").trim();
                isMod = true;
                userInput = "";
            } else {
                throw new CommandException();
            }
        }
    }
}
//@@author