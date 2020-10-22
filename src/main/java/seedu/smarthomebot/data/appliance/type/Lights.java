package seedu.smarthomebot.data.appliance.type;

import seedu.smarthomebot.data.appliance.Appliance;
import seedu.smarthomebot.data.location.LocationList;
import seedu.smarthomebot.logic.commands.exceptions.InvalidApplianceNameException;
import seedu.smarthomebot.logic.commands.exceptions.LocationNotFoundException;

public class Lights extends Appliance {

    public static final String TYPE_WORD = "light";

    public Lights(String name, String location, String wattage, LocationList locationList)
            throws InvalidApplianceNameException, LocationNotFoundException {
        super(name, location, wattage, locationList);
    }

    public String getType() {
        return TYPE_WORD;
    }

    public String getParameter(boolean isList) {
        return "None";
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String writeFileFormat() {
        return super.writeFileFormat();
    }

}
