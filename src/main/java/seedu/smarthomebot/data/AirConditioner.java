package seedu.smarthomebot.data;

import seedu.smarthomebot.data.framework.Appliance;

public class AirConditioner extends Appliance {

    public static final String TYPE_WORD = "airconditioner";
    private String temperature;

    public AirConditioner(String name, String location, String power) {
        super(name, location, power);
        this.temperature = "25";
    }

    public String getParameter() {
        return this.temperature;
    }

    public void getTemperatureFromLoadFile(String loadedTemperature) {
        temperature = loadedTemperature;
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
            temperatureStatement = " set at: " + getParameter() + " degrees";
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
