package seedu.financeit.financetools;

import org.junit.jupiter.api.Test;
import seedu.financeit.common.CommandPacket;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.UiManager;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class YearlyCompoundInterestTest {

    public static CommandPacket handleInput(String input) {
        UiManager.printInputPrompt();
        return InputParser.getInstance().parseInput(input.toLowerCase());
    }

    @Test
    void calculateCompoundInterest_inputAmount_expectCorrectInterestEarned() {
        CommandPacket packet = handleInput("cyearly /a 1000 /r 3 /p 2");
        Double interestRate = Handler.handleYearlyCompoundInterest(packet);
        assertEquals(interestRate, 60.9);
    }

    @Test
    void calculateCompoundInterest_inputAmountWithDeposit_expectCorrectInterestEarned() {
        CommandPacket packet = handleInput("cyearly /a 1000 /r 3 /p 2 /d 1200");
        Double interestRate = Handler.handleYearlyCompoundInterest(packet);
        assertEquals(interestRate, 169.98000000000002);
    }
}