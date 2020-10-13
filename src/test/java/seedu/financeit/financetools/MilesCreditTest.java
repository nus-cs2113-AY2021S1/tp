package seedu.financeit.financetools;

import org.junit.jupiter.api.Test;
import seedu.financeit.common.CommandPacket;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.UiManager;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MilesCreditTest {
    private static InputParser inputParser = new InputParser();

    public static CommandPacket handleInput(String input) {
        UiManager.printInputPrompt();
        return inputParser.parseInput(input.toLowerCase());
    }

    @Test
    void calculateMilesCredit_input1000_expect5000() {
        CommandPacket packet = handleInput("milescalc /amount 1000 /miles 5");
        Double interestRate = FinanceTools.handleMilesCredit(packet);
        assertEquals(interestRate, 5000.0);
    }
}
