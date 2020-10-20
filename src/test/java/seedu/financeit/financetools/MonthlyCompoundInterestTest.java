package seedu.financeit.financetools;

import org.junit.jupiter.api.Test;
import seedu.financeit.common.CommandPacket;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.UiManager;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonthlyCompoundInterestTest {
    private static InputParser inputParser = new InputParser();

    public static CommandPacket handleInput(String input) {
        UiManager.printInputPrompt();
        return inputParser.parseInput(input.toLowerCase());
    }

    @Test
    void calculateCompoundInterest_inputAmount_expectCorrectInterestEarned() {
        CommandPacket packet = handleInput("cmonthly /amount 1000 /ir 3 /period 2");
        Double interestRate = FinanceTools.handleMonthlyCompoundInterest(packet);
        assertEquals(interestRate, 5.01);
    }

    @Test
    void calculateCompoundInterest_inputAmountWithDeposit_expectCorrectInterestEarned() {
        CommandPacket packet = handleInput("cmonthly /amount 1000 /ir 3 /period 2 /deposit 100");
        Double interestRate = FinanceTools.handleMonthlyCompoundInterest(packet);
        assertEquals(interestRate, 5.76);
    }
}