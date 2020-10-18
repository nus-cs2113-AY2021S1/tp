package seedu.smarthomebot.data;

import seedu.smarthomebot.data.framework.Appliance;

public class Fan extends Appliance {

    private static String speed;
    public static final String TYPE_WORD = "fan";

    public Fan(String name, String location, String power) {
        super(name, location, power);
        this.speed = "1";
    }

    public String getType() {
        return "Fan";
    }

    public void setSpeed(String speed) {
        if (speed.isEmpty()) {
            speed = this.speed;
        }
        this.speed = speed;
    }

    private String getSpeed() {
        return this.speed;
    }

    @Override
    public String writeFileFormat() {
        return super.writeFileFormat();
    }

    public String toString() {
        String speedStatement;
        if (appliancePower.getStatus()) {
            speedStatement = " set at: " + getSpeed() + " speed";
        } else {
            speedStatement = "";
        }

        return super.toString() + speedStatement;
    }
}
