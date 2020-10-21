package seedu.smarthomebot.model.framework.type;

import seedu.smarthomebot.model.framework.Appliance;

public class SmartPlug extends Appliance {

    public static final String TYPE_WORD = "smartplug";

    public SmartPlug(String name, String location, String wattage) {
        super(name, location, wattage);
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
