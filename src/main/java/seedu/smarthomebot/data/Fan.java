package seedu.smarthomebot.data;

import seedu.smarthomebot.data.framework.Appliance;

public class Fan extends Appliance {

    private static String speed;
    public static final String TYPE_WORD = "fan";

    public Fan(String name, String location, String power) {
        super(name, location, power);
        speed = "1";
    }


    public String getType() {
        return "Fan";
    }

    private void setSpeed(String speed) {
        Fan.speed = speed;
    }

    @Override
    public String writeFileFormat() {
        return super.writeFileFormat();
    }
}
