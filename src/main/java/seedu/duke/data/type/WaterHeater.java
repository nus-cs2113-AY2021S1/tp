package seedu.duke.data.type;

import seedu.duke.data.framework.Appliance;

public class WaterHeater extends Appliance {
    private String temperature;
    private String duration;

    public WaterHeater(String name, String location) {
        super(name, location);
        this.temperature = "40";
    }

    private void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getType() {
        return "WaterHeater";
    }

    private void setTimer(String duration) {
        this.duration = duration;
    }

}
