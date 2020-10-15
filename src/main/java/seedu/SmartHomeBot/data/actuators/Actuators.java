package seedu.SmartHomeBot.data.actuators;

import seedu.SmartHomeBot.data.framework.Appliance;

abstract class Actuators extends Appliance {

    public Actuators(String name, String location, String power) {
        super(name, location, power);
    }

    public String toString() {
        return super.toString();
    }

}
