package seedu.financeit.financetools;

import org.junit.jupiter.api.Test;
import seedu.financeit.common.CommandPacket;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.UiManager;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompoundInterestTest {
    private static InputParser inputParser = new InputParser();

    public static CommandPacket handleInput(String input) {
        UiManager.printInputPrompt();
        return inputParser.parseInput(input.toLowerCase());
    }

    @Test
    void calculateCompoundInterest_inputAmount_expectInterestEarned() {
        CommandPacket packet = handleInput("compoundcalc /amount 1000 /ir 3 /period 2");
        Double interestRate = FinanceTools.handleCompoundInterest(packet);
        assertEquals(interestRate, 60.899999999999864);
    }
}