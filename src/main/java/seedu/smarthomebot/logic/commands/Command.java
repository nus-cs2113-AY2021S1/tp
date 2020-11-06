package seedu.smarthomebot.logic.commands;

import seedu.smarthomebot.data.appliance.Appliance;
import seedu.smarthomebot.data.appliance.ApplianceList;
import seedu.smarthomebot.data.location.LocationList;

import java.util.logging.Logger;

//@@author zongxian-ctrl

/**
 * Represent the parent of all command types.
 */
public abstract class Command {

    protected ApplianceList applianceList;
    protected LocationList locationList;
    protected int maxNameLength = 0;
    protected int maxLocationLength = 0;
    public static Logger commandLogger = Logger.getLogger("SmartHomeBotLogger");

    protected Command() {
    }

    /**
     * Passes the ApplianceList and LocationList to allow Command classes to use.
     *
     * @param applianceList stores the appliances in SmartHomeBot
     * @param locationList  stores the locations in SmartHomeBot
     */
    public void setData(ApplianceList applianceList, LocationList locationList) {
        this.applianceList = applianceList;
        this.locationList = locationList;
    }

    //@@author fanceso
    /**
     * Gets the longest length of name and location in the appliance class.
     */
    protected void autoFormattingStringIndex() {
        for (Appliance a : applianceList.getAllAppliance()) {
            if (a.getName().length() > maxNameLength) {
                maxNameLength = a.getName().length();
            }
            if (a.getLocation().length() > maxLocationLength) {
                maxLocationLength = a.getLocation().length();
            }
        }
    }

    public abstract CommandResult execute();

}
