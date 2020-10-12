package seedu.financeit.financetools;

import org.junit.jupiter.api.Test;
import seedu.financeit.common.CommandPacket;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.UiManager;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CashbackTest {
    private static InputParser inputParser = new InputParser();

    public static CommandPacket handleInput(String input) {
        UiManager.printInputPrompt();
        return inputParser.parseInput(input.toLowerCase());
    }

    @Test
    void calculateCashback_input1000_expect5() {
        CommandPacket packet = handleInput("cashbackcalc /amount 1000 /cashback 5 /cap 1000");
        Double cashbackEarned = FinanceTools.handleCashback(packet);
        assertEquals(cashbackEarned, 50.0);
    }
}