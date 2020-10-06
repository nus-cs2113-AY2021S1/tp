package seedu.duke.data.type.actuators;

import seedu.duke.data.framework.Appliance;

abstract class Actuators extends Appliance {

    public Actuators(String name, String location) {
        super(name, location);
    }

    public String toString() {
        return super.toString();
    }

}
