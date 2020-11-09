package seedu.smarthomebot.data.appliance;

import seedu.smarthomebot.commons.exceptions.InvalidNumericalValueException;
import seedu.smarthomebot.data.location.LocationList;
import seedu.smarthomebot.logic.commands.exceptions.InvalidApplianceNameException;
import seedu.smarthomebot.logic.commands.exceptions.LocationNotFoundException;

//@@author leonlowzd

/**
 * Abstract Class representing the appliances for SmartHomeBot.
 */
public abstract class Appliance {
    private static final String ON = "ON";
    private static final String OFF = "OFF";
    private static LocationList locationList;
    protected String name;
    protected String location;
    protected String wattage;
    protected Power appliancePower;

    /**
     * Constructor for Appliance.
     *
     * @param name         name of appliance.
     * @param location     location of appliance.
     * @param wattage      wattage of appliance.
     * @param locationList locations available in SmartHomeBot, appliance will only be created:
     *                     Appliance will only be created when:
     *                     1. location inputted is present in the locationList.
     *                     2. User inputted name does not exist in locationList.
     * @throws InvalidApplianceNameException When name already exists in LocationList.
     * @throws LocationNotFoundException     When Location does not exist in LocationList.
     */
    public Appliance(String name, String location, String wattage, LocationList locationList)
            throws LocationNotFoundException, InvalidApplianceNameException {

        this.locationList = locationList;
        if (locationList.isLocationCreated(location) && (!locationList.isLocationCreated(name))) {
            this.name = name;
            this.location = location;
            this.wattage = wattage;
            appliancePower = new Power(wattage);
        } else {
            if (locationList.isLocationCreated(name)) {
                throw new InvalidApplianceNameException();
            } else {
                throw new LocationNotFoundException();
            }
        }

    }

    /**
     * Converts String value into integer.
     *
     * @return the parameter in integer.
     */
    private static int convertParameterToInt(String parameter) throws InvalidNumericalValueException {
        try {
            return Integer.parseInt(parameter);
        } catch (NumberFormatException e) {
            throw new InvalidNumericalValueException();
        }

    }

    /**
     * Sets the status of Appliance to on.
     *
     * @return the outcome of the operation.
     */
    public boolean switchOn() {
        return appliancePower.onAppliance();
    }

    /**
     * Sets the status of Appliance to off.
     *
     * @return the outcome of the operation.
     */
    public boolean switchOff() {
        return appliancePower.offAppliance();
    }

    public void resetPowerUsage() {
        appliancePower.resetPower();
    }

    /**
     * Gets the status of the appliance.
     *
     * @return the status of the appliance.
     */
    public String getStatus() {
        return (appliancePower.getStatus() ? ON : OFF);
    }

    /**
     * Compute the power consumption of the appliance.
     *
     * @return the power consumption of the appliance in double.
     */
    public double getPowerInDouble() {
        return appliancePower.getPower();
    }

    /**
     * Gets the power consumption of the appliance in String.
     *
     * @return the power consumption of the appliance in String.
     */
    public String getPowerInString() {
        return appliancePower.toString();
    }

    /**
     * Recomputes the power consumption of the appliance.
     */
    public void loadDataFromFile(String powerConsumption) {
        appliancePower.loadDataFromFile(Double.parseDouble(powerConsumption));
    }

    /**
     * Gets the power rating of the appliance in String.
     *
     * @return the power rating of the appliance in String.
     */
    public String getWattage() {
        return wattage;
    }

    /**
     * Method to gets the name of appliance.
     *
     * @return the type of the appliance in String.
     */
    public String getName() {
        return name;
    }

    /**
     * Method to gets the location of appliance.
     *
     * @return the location of the appliance in String.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Abstract method to gets the type of appliance.
     *
     * @return the type of the appliance in String.
     */
    public abstract String getType();

    /**
     * Abstract method to gets the parameter of appliance.
     *
     * @return the type of the appliance in String.
     */
    public abstract String getParameter(boolean isList);

    /**
     * Method to gets the printout for the appliance.
     *
     * @return the printout for appliance.
     */
    public String toString() {
        return getName() + "(" + getWattage() + "W)" + ", located at " + getLocation() + " ";
    }

    /**
     * Method to save the printout for the appliance to text file.
     *
     * @return the printout for appliance.
     */
    public String writeFileFormat() {
        return location + "|" + name + "|" + wattage + "|" + getType()
                + "|" + getPowerInString() + "|" + getParameter(false);
    }

    /**
     * Method to check validity of inputted parameter.
     *
     * @param lowerBound parameter must be larger than lowerBound.
     * @param upperBound parameter must be smaller than upperBound.
     * @return true if parameter is valid.
     */
    protected boolean isParameterValid(String parameter, int lowerBound, int upperBound) {
        try {
            int parameterValue = convertParameterToInt(parameter);
            if ((parameterValue < upperBound) && (parameterValue > lowerBound)) {
                return true;
            }
        } catch (InvalidNumericalValueException e) {
            return false;
        }
        return false;
    }
}
