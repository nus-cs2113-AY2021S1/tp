package seedu.smarthomebot.data.appliance.type;

import seedu.smarthomebot.data.appliance.Appliance;
import seedu.smarthomebot.data.location.LocationList;
import seedu.smarthomebot.logic.commands.exceptions.InvalidApplianceNameException;
import seedu.smarthomebot.logic.commands.exceptions.LocationNotFoundException;

import static seedu.smarthomebot.commons.Messages.MESSAGE_INVALID_TEMPERATURE_AC;

/**
 * Represents Air Conditioner type extended from Appliance.
 */
public class AirConditioner extends Appliance {

    public static final String TYPE_WORD = "aircon";
    private static final int LOWER_BOUND = 15;
    private static final int UPPER_BOUND = 30;
    private String temperature;

    /**
     * Constructor for Air Conditioner type
     *
     * @param name         name of appliance.
     * @param location     location of appliance.
     * @param wattage      wattage of appliance.
     * @param locationList locations available in SmartHomeBot, appliance will only be created:
     *                     Appliance will only be created when:
     *                     1. location inputted is present in the locationList.
     *                     2. User inputted name does not exist in locationList.
     */
    public AirConditioner(String name, String location, String wattage, LocationList locationList)
            throws InvalidApplianceNameException, LocationNotFoundException {
        super(name, location, wattage, locationList);
        this.temperature = "25";
    }

    /**
     * Method to return the set temperature of the Air conditioner.
     *
     * @return the set set temperature of the appliance in String.
     */
    @Override
    public String getParameter(boolean isList) {
        if (isList) {
            return String.format(" %s Degrees", getParameter(false));
        } else {
            return this.temperature;
        }
    }


    /**
     * Method to set the temperature of appliance from user text file.
     *
     * @param loadedTemperature temperature to set.
     */
    public void getTemperatureFromLoadFile(String loadedTemperature) {
        setTemperature(loadedTemperature);
    }

    /**
     * Method to set the temperature of appliance from user input.
     *
     * @param temperature temperature to set.
     * @return the reciprocal printout message after setting temperature in String.
     */
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

    /**
     * Method to return the type of appliance.
     *
     * @return the type of appliance in String.
     */
    public String getType() {
        return TYPE_WORD;
    }

    /**
     * Method to return the full details of the appliance.
     *
     * @return the full details of the appliance in String.
     */
    public String toString() {
        String temperatureStatement;
        if (appliancePower.getStatus()) {
            temperatureStatement = "@" + getParameter(true);
        } else {
            temperatureStatement = "";
        }

        return super.toString() + temperatureStatement;
    }


}
