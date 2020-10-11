package seedu.duke.Finance;

import seedu.duke.Command;
import seedu.duke.backend.UserInput;

public class CommandFinanceDel extends Command {
    UserInput userinput;

    @Override
    public String execute() {
        String input=userinput.getArg("");
        int index=Integer.parseInt(input.trim());
        String output = FinanceList.dellog(index);
        return output;
    }

    @Override
    public String help() {
        return "The format of input to delete a log is: finance delLog index";
    }

    public int validate(UserInput ui) {
        if (ui.getCategory().equals("finance")&&ui.getCommand().equalsIgnoreCase("dellog")) {
            userinput=ui;
            return ACCEPT;
        }
        return NO_MATCH;
    }
}
