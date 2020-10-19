package seedu.duke.finance;

import seedu.duke.Command;
import seedu.duke.backend.UserInput;

public class CommandFinanceDel extends Command {
    private UserInput userinput;

    @Override
    public String execute() {
        String input = userinput.getArg("");
        int index = Integer.parseInt(input.trim());
        String output = FinanceList.dellog(index);
        return output;
    }

    @Override
    public String help() {
        return "Syntax: finance delLog <index>";
    }

    public int validate(UserInput ui) {
        if (ui.getCategory().equals("finance") && ui.getCommand().equalsIgnoreCase("dellog")
                || ui.getCommand().equalsIgnoreCase("del")
                || ui.getCommand().equalsIgnoreCase("d")) {
            try {
                Integer.parseInt(ui.getArg(""));
            } catch (NumberFormatException e) {
                return ARGUMENT_ERR;
            }
            userinput = ui;
            return ACCEPT;
        }
        return NO_MATCH;
    }
}
