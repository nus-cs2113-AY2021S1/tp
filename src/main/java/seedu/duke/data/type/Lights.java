package seedu.duke.data.type;

import seedu.duke.data.framework.Appliance;

public class Lights extends Appliance {

    public Lights(String name, String location) {
        super(name, location);
    }

    public String getType() {
        return "Light";
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
