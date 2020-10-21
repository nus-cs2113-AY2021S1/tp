package seedu.smarthomebot.commands;

import seedu.smarthomebot.data.ApplianceList;
import seedu.smarthomebot.data.LocationList;

public abstract class Command {

    protected ApplianceList applianceList;
    protected LocationList locationList;

    protected Command() {
    }

    public void setData(ApplianceList applianceList, LocationList locationList) {
        this.applianceList = applianceList;
        this.locationList = locationList;
    }

    public abstract CommandResult execute();

}
