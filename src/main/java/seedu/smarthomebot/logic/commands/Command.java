package seedu.smarthomebot.logic.commands;

import seedu.smarthomebot.data.appliance.Appliance;
import seedu.smarthomebot.data.appliance.ApplianceList;
import seedu.smarthomebot.data.location.LocationList;

public abstract class Command {

    protected ApplianceList applianceList;
    protected LocationList locationList;
    protected int maxNameLength = 0;
    protected int maxLocationLength = 0;

    protected Command() {
    }

    public void setData(ApplianceList applianceList, LocationList locationList) {
        this.applianceList = applianceList;
        this.locationList = locationList;
    }

    /*
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
