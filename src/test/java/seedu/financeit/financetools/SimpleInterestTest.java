package seedu.financeit.financetools;

import org.junit.jupiter.api.Test;
import seedu.financeit.common.CommandPacket;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.UiManager;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleInterestTest {
    private static InputParser inputParser = new InputParser();

    public static CommandPacket handleInput(String input) {
        UiManager.printInputPrompt();
        return inputParser.parseInput(input.toLowerCase());
    }

    @Test
    void calculateSimpleInterest_input1000_expect5() {
        CommandPacket packet = handleInput("simplecalc /amount 1000 /ir 5");
        Double interestRate = FinanceTools.handleSimpleInterest(packet);
        assertEquals(interestRate, 50.0);
    }
}