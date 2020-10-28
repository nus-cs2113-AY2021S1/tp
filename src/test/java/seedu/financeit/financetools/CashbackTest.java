package seedu.financeit.financetools;

import org.junit.jupiter.api.Test;
import seedu.financeit.common.CommandPacket;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.UiManager;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CashbackTest {

    public static CommandPacket handleInput(String input) {
        UiManager.printInputPrompt();
        return InputParser.getInstance().parseInput(input.toLowerCase());
    }

    @Test
    void calculateCashback_inputAmount_expectCorrectCashbackEarned() {
        CommandPacket packet = handleInput("cashb /a 1000 /r 5 /c 1000");
        Double cashbackEarned = Handler.handleCashback(packet);
        assertEquals(50.0, cashbackEarned);
    }

    @Test
    void calculateCashback_inputAmountWithValidCap_expectCorrectCashbackEarned() {
        CommandPacket packet = handleInput("cashb /a 1000 /r 5 /c 10");
        Double cashbackEarned = Handler.handleCashback(packet);
        assertEquals(10.0, cashbackEarned);
    }
}