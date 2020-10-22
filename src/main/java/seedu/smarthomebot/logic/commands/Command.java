package seedu.smarthomebot.logic.commands;

import seedu.smarthomebot.data.appliance.ApplianceList;
import seedu.smarthomebot.data.location.LocationList;

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
