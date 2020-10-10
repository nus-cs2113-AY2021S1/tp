package seedu.duke.data;

import seedu.duke.data.framework.Appliance;

public class Fan extends Appliance {

    public static final String TYPE_WORD = "fan";
    private static String speed;

    public Fan(String name, String location, String power) {
        super(name, location, power);
        speed = "1";
    }


    public String getType() {
        return "Fan";
    }

    public String getSpeed() {
        return this.speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String toString() {
        return super.toString() + " with a speed of: " + getSpeed();
    }
}
