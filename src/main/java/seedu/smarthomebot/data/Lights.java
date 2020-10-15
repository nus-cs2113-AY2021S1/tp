package seedu.smarthomebot.data;

import seedu.smarthomebot.data.framework.Appliance;

public class Lights extends Appliance {

    public static final String TYPE_WORD = "light";

    public Lights(String name, String location, String power) {
        super(name, location, power);
    }

    public String getType() {
        return "Light";
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
