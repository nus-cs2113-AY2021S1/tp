package seedu.smarthomebot.data.appliance.type;

import seedu.smarthomebot.data.appliance.Appliance;
import seedu.smarthomebot.data.location.LocationList;
import seedu.smarthomebot.logic.commands.exceptions.InvalidApplianceNameException;
import seedu.smarthomebot.logic.commands.exceptions.LocationNotFoundException;

import static seedu.smarthomebot.commons.Messages.MESSAGE_INVALID_TEMPERATURE_AC;

public class AirConditioner extends Appliance {

    public static final String TYPE_WORD = "aircon";
    private static final int LOWER_BOUND = 15;
    private static final int UPPER_BOUND = 30;
    private String temperature;

    public AirConditioner(String name, String location, String wattage, LocationList locationList)
            throws InvalidApplianceNameException, LocationNotFoundException {
        super(name, location, wattage, locationList);
        this.temperature = "25";
    }

    @Override
    public String getParameter(boolean isList) {
        if (isList) {
            return String.format(" %s Degrees",getParameter(false));
        } else {
            return this.temperature;
        }
    }

    public void getTemperatureFromLoadFile(String loadedTemperature) {
        temperature = loadedTemperature;
    }

    public String setTemperature(String temperature) {
        String toPrint = "";
        if (temperature.isEmpty()) {
            // default temperature will be set.
            toPrint = toString();
        } else if (isParameterValid(temperature, LOWER_BOUND, UPPER_BOUND)) {
            // user input speed will be set.
            this.temperature = temperature;
            toPrint = toString();
        } else {
            // user inputs an invalid temperature.
            System.out.println(toPrint);
            toPrint = "\n" + MESSAGE_INVALID_TEMPERATURE_AC;
        }
        return toPrint;
    }

    public String getType() {
        return TYPE_WORD;
    }

    public String toString() {
        String temperatureStatement;
        if (appliancePower.getStatus()) {
            temperatureStatement = "@" + getParameter(true);
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
