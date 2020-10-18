package seedu.smarthomebot.data;

import seedu.smarthomebot.data.framework.Appliance;

public class AirConditioner extends Appliance {

    public static final String TYPE_WORD = "airconditioner";
    private String temperature;

    public AirConditioner(String name, String location, String power) {
        super(name, location, power);
        this.temperature = "25";
    }

    private String getTemperature() {
        return this.temperature;
    }

    public void setTemperature(String temperature) {
        if (temperature.isEmpty()) {
            temperature = this.temperature;
        }
        this.temperature = temperature;
    }

    public String getType() {
        return "AirConditioner";
    }

    public String toString() {
        String temperatureStatement;
        if (appliancePower.getStatus()) {
            temperatureStatement = " set at: " + getTemperature() + " degrees";
        } else {
            temperatureStatement = "";
        }

        return super.toString() + temperatureStatement;
    }

    @Override
    public String writeFileFormat() {
        return super.writeFileFormat();
    }

}
