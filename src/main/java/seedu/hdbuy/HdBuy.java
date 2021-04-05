package seedu.hdbuy;

import seedu.hdbuy.command.Command;
import seedu.hdbuy.common.HdBuyLogger;
import seedu.hdbuy.data.UserInput;
import seedu.hdbuy.parser.Parser;
import seedu.hdbuy.ui.TextUi;

public class HdBuy {

    /**
     * Main entry-point for the java.duke.Duke application.
     */

    public static void main(String[] args) {
        HdBuyLogger.enableLogger(true);
        HdBuyLogger.info("Starting process");
        UserInput userInput = UserInput.getInstance();
        TextUi.showWelcome();
        receiveCommand(false);
    }

    private static void receiveCommand(boolean isExit) {
        if (!isExit) {
            String fullCommand = TextUi.readCommand();
            TextUi.showSeparator();
            Command command = Parser.parse(fullCommand);
            assert UserInput.getInputs() != null : "Input is not initiated";
            command.execute();
            TextUi.showSeparator();
            receiveCommand(command.isExit());
        }
    }
}
