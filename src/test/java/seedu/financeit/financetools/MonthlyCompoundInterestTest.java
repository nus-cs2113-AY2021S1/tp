package seedu.financeit.financetools;

import org.junit.jupiter.api.Test;
import seedu.financeit.common.CommandPacket;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.UiManager;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonthlyCompoundInterestTest {

    public static CommandPacket handleInput(String input) {
        UiManager.printInputPrompt();
        return InputParser.getInstance().parseInput(input.toLowerCase());
    }

    @Test
    void calculateCompoundInterest_inputAmount_expectCorrectInterestEarned() {
        CommandPacket packet = handleInput("cmonthly /a 1000 /r 3 /p 2");
        Double interestEarned = Handler.handleMonthlyCompoundInterest(packet);
        assertEquals(5.01, interestEarned);
    }

    @Test
    void calculateCompoundInterest_inputAmountWithDeposit_expectCorrectInterestEarned() {
        CommandPacket packet = handleInput("cmonthly /a 1000 /r 3 /p 2 /d 100");
        Double interestEarned = Handler.handleMonthlyCompoundInterest(packet);
        assertEquals(5.76, interestEarned);
    }
}