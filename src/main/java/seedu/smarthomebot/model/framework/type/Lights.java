package seedu.smarthomebot.model.framework.type;

import seedu.smarthomebot.model.framework.Appliance;

public class Lights extends Appliance {

    public static final String TYPE_WORD = "light";

    public Lights(String name, String location, String wattage) {
        super(name, location, wattage);
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
