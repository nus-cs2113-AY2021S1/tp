package seedu.smarthomebot.data;

import seedu.smarthomebot.data.framework.Appliance;

public class WaterHeater extends Appliance {

    public static final String TYPE_WORD = "waterheater";
    private String temperature;
    private String duration;

    public WaterHeater(String name, String location, String power) {
        super(name, location, power);
        this.temperature = "40";
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getType() {
        return "WaterHeater";
    }

    public String getParameter() {
        return this.temperature;
    }

    public void getTemperatureFromLoadFile(String loadedTemperature) {
        temperature = loadedTemperature;
    }

    private void setTimer(String duration) {
        this.duration = duration;
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
