package seedu.duke.data.type;

import seedu.duke.data.framework.Appliance;

public class AirConditioner extends Appliance {

    private String temperature;

    public AirConditioner(String name, String location) {
        super(name, location);
        this.temperature = "25";
    }

    private String getTemperature() {
        return this.temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getType() {
        return "AirConditioner";
    }

    public String toString() {
        return super.toString();
    }

}
