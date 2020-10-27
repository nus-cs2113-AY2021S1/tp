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
    void calculateMilesCredit_input1000_expect5000() {
        CommandPacket packet = handleInput("miles /a 1000 /r 5");
        Double interestRate = Handler.handleMilesCredit(packet);
        assertEquals(interestRate, 5000.0);
    }
}
