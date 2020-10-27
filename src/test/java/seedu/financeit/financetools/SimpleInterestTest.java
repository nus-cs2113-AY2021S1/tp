package seedu.financeit.financetools;

import org.junit.jupiter.api.Test;
import seedu.financeit.common.CommandPacket;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.UiManager;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleInterestTest {

    public static CommandPacket handleInput(String input) {
        UiManager.printInputPrompt();
        return InputParser.getInstance().parseInput(input.toLowerCase());
    }

    @Test
    void calculateSimpleInterest_inputAmount_expectCorrectInterestEarned() {
        CommandPacket packet = handleInput("simple /a 1000 /r 5");
        Double interestEarned = Handler.handleSimpleInterest(packet);
        assertEquals(50.0, interestEarned);
    }
}