package seedu.smarthomebot.data.framework.type;

import seedu.smarthomebot.data.framework.Appliance;

public class Lights extends Appliance {

    public static final String TYPE_WORD = "light";

    public Lights(String name, String location, String wattage) {
        super(name, location, wattage);
    }

    public String getType() {
        return TYPE_WORD;
    }

    public String getParameter(boolean isList) {
        return " ";
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
