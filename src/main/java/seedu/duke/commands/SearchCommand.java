package seedu.duke.commands;


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

    public void processCommand() {
        String showName = inputs.get(1);
        if (ShowList.getShowList().containsKey(showName)) {
            Ui.printSearchSuccessful(showName);
        } else {
            throw new NullPointerException();
        }
    }
}
