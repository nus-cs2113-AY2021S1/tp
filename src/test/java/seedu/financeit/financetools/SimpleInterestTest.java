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
    void calculateSimpleInterest_input1000_expect5() {
        CommandPacket packet = handleInput("simple /a 1000 /r 5");
        Double interestRate = Handler.handleSimpleInterest(packet);
        assertEquals(interestRate, 50.0);
    }
}