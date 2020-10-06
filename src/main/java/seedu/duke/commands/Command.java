package seedu.duke.commands;

import seedu.duke.data.ApplianceList;
import seedu.duke.data.HomeLocations;

public class Command {

    protected ApplianceList appliances;
    protected HomeLocations homeLocationsList;

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
