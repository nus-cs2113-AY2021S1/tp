package seedu.duke.finance;

import seedu.duke.Command;
import seedu.duke.DukeNotNumberException;
import seedu.duke.backend.UserInput;

public class CommandFinanceChange extends Command {
    UserInput userinput;

    @Override
    public String execute() throws DukeNotNumberException {
        try {
            int index = Integer.parseInt(userinput.getArg("i"));
            String input = userinput.getArg("n");
            String[] contents = input.trim().split(" ");
            int length = contents.length;
            String item = "";
            for (int i = 0; i < length - 1; i++) {
                if (i == length - 2) {
                    item = item + contents[i];
                } else {
                    item = item + contents[i] + " ";
                }
            }
            for (int i = 0; i < contents[length - 1].length(); i++) {
                if (!Character.isDigit(contents[length - 1].charAt(i)) && contents[length - 1].charAt(i) != '.') {
                    throw new DukeNotNumberException();
                }
            }
            String output = FinanceList.changeFinanceLog(index, item, Double.parseDouble(contents[length - 1]));
            return output;
        } catch (NumberFormatException e) {
            throw new DukeNotNumberException();
        }
    }

    @Override
    public String help() {
        return "Syntax: finance changeLog /i INDEX /n ITEM_NAME ITEM_VALUE";
    }

    public int validate(UserInput ui) {
        if (ui.getCategory().equals("finance") && ui.getCommand().equalsIgnoreCase("changelog")
            && ui.getArg("i") != null && ui.getArg("n") != null) {
            userinput = ui;
            return ACCEPT;
        }
        return NO_MATCH;
    }
}
