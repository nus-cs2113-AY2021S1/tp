package seedu.duke.Finance;

import seedu.duke.Command;
import seedu.duke.backend.UserInput;

public class CommandFinanceAdd extends Command {

    UserInput userinput;

    @Override
    public String execute() {
        String input=userinput.getArg("");
        String[] contents=input.trim().split(" ");
        FinanceLog fl = new FinanceLog(contents[0],Double.parseDouble(contents[1]));
        String output = FinanceList.addLog(fl);
        return output;
    }

    @Override
    public String help() {
        return "The format input to add a finance log is: hr addLog title value";
    }

    public int validate(UserInput ui) {
        if (ui.getCategory().equals("finance")&&ui.getCommand().equalsIgnoreCase("addlog")) {
            userinput=ui;
            return ACCEPT;
        }
        return NO_MATCH;
    }
}
