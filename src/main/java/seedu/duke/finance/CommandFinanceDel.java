package seedu.duke.finance;

import seedu.duke.Command;
import seedu.duke.DukeNotNumberException;
import seedu.duke.backend.UserInput;

public class CommandFinanceDel extends Command {
    private UserInput userinput;

    @Override
    public String execute() throws DukeNotNumberException {
        String input = userinput.getArg("");
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                throw new DukeNotNumberException();
            }
        }
        int index = Integer.parseInt(input.trim());
        String output = FinanceList.dellog(index);
        return output;
    }

    @Override
    public String help() {
        return "The format of input to delete a log is: finance delLog index";
    }

    public int validate(UserInput ui) {
        if (ui.getCategory().equals("finance") && ui.getCommand().equalsIgnoreCase("dellog")) {
            userinput = ui;
            return ACCEPT;
        }
        return NO_MATCH;
    }
}
