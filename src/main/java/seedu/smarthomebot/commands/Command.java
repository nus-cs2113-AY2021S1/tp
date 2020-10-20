package seedu.smarthomebot.commands;

import seedu.smarthomebot.data.ApplianceList;
import seedu.smarthomebot.data.LocationList;

import static seedu.smarthomebot.common.Messages.LINE;
import static seedu.smarthomebot.common.Messages.MESSAGE_LIST_NO_APPLIANCES;

public class Command {

    protected ApplianceList applianceList;
    protected LocationList locationList;

    protected Command() {
    }

    public void setData(ApplianceList applianceList, LocationList locationList) {
        this.applianceList = applianceList;
        this.locationList = locationList;
    }


    public CommandResult execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }

}
