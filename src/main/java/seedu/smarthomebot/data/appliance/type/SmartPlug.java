package seedu.smarthomebot.data.appliance.type;

import seedu.smarthomebot.data.appliance.Appliance;
import seedu.smarthomebot.data.location.LocationList;
import seedu.smarthomebot.logic.commands.exceptions.InvalidApplianceNameException;
import seedu.smarthomebot.logic.commands.exceptions.LocationNotFoundException;

public class SmartPlug extends Appliance {

    public static final String TYPE_WORD = "smartplug";

    public SmartPlug(String name, String location, String wattage, LocationList locationList)
            throws InvalidApplianceNameException, LocationNotFoundException {
        super(name, location, wattage, locationList);
    }

    public String getType() {
        return TYPE_WORD;
    }

    @Override
    public String getParameter(boolean isList) {
        return " None";
    }

    @Override
    public String writeFileFormat() {
        return super.writeFileFormat();
    }

}
