package seedu.duke.commands;


import seedu.duke.classes.Show;
import seedu.duke.utility.ShowList;
import seedu.duke.utility.Ui;

import java.util.ArrayList;


public class SearchCommand extends Command {
    ArrayList<String> inputs;


    public SearchCommand(String description, ArrayList<String> inputs) throws NullPointerException {
        super(description);
        this.inputs = inputs;
        if (inputs.size() != 2) {
            throw new NullPointerException();
        }
    }

    public boolean processCommand() {
        String showName = inputs.get(1);
        String showsWithKeyword = "";
        boolean searchSuccess = false;

        for (Show show : ShowList.showList.values()) {
            if (show.toString().contains(showName.toLowerCase())) {
                showsWithKeyword += show.toString() + "\n\t";
                searchSuccess = true;
            }
        }
        if (searchSuccess) {
            Ui.printSearchSuccessful(showName, showsWithKeyword);
            return true;
        } else {
            Ui.printShowNotInList();
            return false;
        }
    }
}
