package seedu.duke.data.actuators;

import seedu.duke.data.framework.Appliance;

abstract class Actuators extends Appliance {

    public Actuators(String name, String location, String power) {
        super(name, location, power);
    }

    public String toString() {
        return super.toString();
    }

}
