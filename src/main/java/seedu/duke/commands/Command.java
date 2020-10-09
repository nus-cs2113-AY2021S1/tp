package seedu.duke.commands;

import seedu.duke.data.ApplianceList;
import seedu.duke.data.HomeLocations;
import seedu.duke.ui.TextUi;

public class Command {

    protected ApplianceList appliances;
    protected HomeLocations homeLocationsList;
    protected static TextUi ui = new TextUi();

    protected Command() {
    }

    public void setData(ApplianceList appliances, HomeLocations homeLocations) {
        this.appliances = appliances;
        this.homeLocationsList = homeLocations;
    }

    public void execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }

}
