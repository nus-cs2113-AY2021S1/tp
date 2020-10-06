package seedu.duke.data.type;

import seedu.duke.data.framework.Appliance;

public class Fan extends Appliance {

    private static String speed;

    public Fan(String name, String location) {
        super(name, location);
        speed = "1";
    }


    public String getType() {
        return "Fan";
    }

    private void setSpeed(String speed) {
        Fan.speed = speed;
    }

}
