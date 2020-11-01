package seedu.duke.finance;

import seedu.duke.Command;
import seedu.duke.backend.UserInput;

/**
 * Represents list the finance summary.
 */
public class CommandFinanceSummary extends Command {
    private UserInput userinput;

    @Override
    public String execute() {
        String output = FinanceList.summary();
        return output;
    }

    @Override
    public String help() {
        return "The format of input to show finance list is: finance summary";
    }

    public int validate(UserInput ui) {
        if (ui.getCategory().equals("finance") && (ui.getCommand().equalsIgnoreCase("summary")
                || ui.getCommand().equalsIgnoreCase("list")
                || ui.getCommand().equalsIgnoreCase("s")
                || ui.getCommand().equalsIgnoreCase("l"))) {
            userinput = ui;
            return ACCEPT;
        }
        return NO_MATCH;
    }
}
