//@@author bqxy

package seedu.financeit.financetools;

import org.junit.jupiter.api.Test;
import seedu.financeit.common.CommandPacket;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.UiManager;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MilesCreditTest {
    public static CommandPacket handleInput(String input) {
        UiManager.printInputPrompt();
        return InputParser.getInstance().parseInput(input.toLowerCase());
    }

    @Test
    void calculateMilesCredit_inputAmount_expectCorrectMilesEarned() {
        CommandPacket packet = handleInput("miles /a 1000 /r 5");
        Double milesEarned = Handler.handleMilesCredit(packet);
        assertEquals(5000.0, milesEarned);
    }
}
