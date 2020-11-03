package seedu.smarthomebot.logic.commands;

import seedu.smarthomebot.data.appliance.ApplianceList;
import seedu.smarthomebot.data.location.LocationList;

//@@author zongxian-ctrl
/**
 * Represent the parent of all command types.
 */
public abstract class Command {

    protected ApplianceList applianceList;
    protected LocationList locationList;

    protected Command() {
    }

    /**
     * Passes the ApplianceList and LocationList to allow Command classes to use.
     *
     * @param applianceList stores the appliances in SmartHomeBot
     * @param locationList  stores the locations in SmartHomebot
     */
    public void setData(ApplianceList applianceList, LocationList locationList) {
        this.applianceList = applianceList;
        this.locationList = locationList;
    }

    public abstract CommandResult execute();

}
