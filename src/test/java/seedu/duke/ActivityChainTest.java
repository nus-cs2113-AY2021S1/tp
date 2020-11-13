package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.logic.parser.ChainingParser.CHAIN_SEPARATOR;
import static seedu.duke.logic.parser.ChainingParser.CHAIN_SEPARATOR_LENGTH;
import static seedu.duke.logic.parser.CommandParser.SPACE;

public class ActivityChainTest {
    @Test
    public void parseChainCommand_testExample1() {
        String userInput = "add f/ food c/ 90 && add e/ running c/ 80";
        int chainIndex = userInput.indexOf(CHAIN_SEPARATOR);
        String firstString = userInput.substring(0, chainIndex).trim();
        assertEquals("add f/ food c/ 90", firstString);

        userInput = userInput.substring(chainIndex + CHAIN_SEPARATOR_LENGTH).trim();
        assertEquals("add e/ running c/ 80", userInput);
    }

    @Test
    public void parseChainCommand_testExample2() {
        String userInput = "graph && list";
        int chainIndex = userInput.indexOf(CHAIN_SEPARATOR);
        String firstString = userInput.substring(0, chainIndex).trim();
        assertEquals("graph", firstString);

        userInput = userInput.substring(chainIndex + CHAIN_SEPARATOR_LENGTH).trim();
        assertEquals("list", userInput);
    }

    @Test
    public void parseChainCommand_testExample3() {
        String userInput = "edita 2 e/ jumping c/ 88 && add f/ food c/ 77 d/ 2020-11-09 && list";
        int chainIndex = userInput.indexOf(CHAIN_SEPARATOR);
        String firstString = userInput.substring(0, chainIndex).trim();
        assertEquals("edita 2 e/ jumping c/ 88", firstString);

        userInput = userInput.substring(chainIndex + CHAIN_SEPARATOR_LENGTH).trim();
        assertEquals("add f/ food c/ 77 d/ 2020-11-09 && list", userInput);

        userInput = userInput + SPACE + CHAIN_SEPARATOR;
        chainIndex = userInput.indexOf(CHAIN_SEPARATOR);
        firstString = userInput.substring(0, chainIndex).trim();
        assertEquals("add f/ food c/ 77 d/ 2020-11-09", firstString);

        userInput = userInput.substring(chainIndex + CHAIN_SEPARATOR_LENGTH).trim();
        assertEquals("list &&", userInput);

        chainIndex = userInput.indexOf(CHAIN_SEPARATOR);
        firstString = userInput.substring(0, chainIndex).trim();
        assertEquals("list", firstString);
    }
}
