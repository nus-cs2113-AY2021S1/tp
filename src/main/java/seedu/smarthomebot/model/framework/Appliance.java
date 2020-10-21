package seedu.smarthomebot.model.framework;

import seedu.smarthomebot.commons.exceptions.InvalidNumericalValueException;

/**
 * Abstract Class representing the appliances for SmartHomeBot.
 */
public abstract class Appliance {
    private static final String ON = "ON";
    private static final String OFF = "OFF";
    private static int maxNameLength = 0;
    private static int maxLocationLength = 0;
    protected String name;
    protected String location;
    protected String wattage;
    protected Power appliancePower;

    public Appliance(String name, String location, String wattage) {
        this.name = name;
        this.location = location;
        this.wattage = wattage;
        appliancePower = new Power(wattage);
        if (this.name.length() > maxNameLength) {
            maxNameLength = this.name.length();
        }
        if (this.location.length() > maxLocationLength) {
            maxLocationLength = this.location.length();
        }
    }

    /**
     * Gets the longest length of name in the appliance class.
     *
     * @return the length of the longest name of appliance in integer.
     */
    public static int getMaxNameLength() {
        return maxNameLength;
    }

    /**
     * Gets the longest length of name in the location.
     *
     * @return the length of the longest name of location in integer.
     */
    public static int getMaxLocationLength() {
        return maxLocationLength;
    }

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

    public void resetPower() {
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
        return this.wattage;
    }

    /**
     * Abstract method to gets the type of appliance.
     *
     * @return the type of the appliance in String.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method to gets the type of appliance.
     *
     * @return the type of the appliance in String.
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Abstract method to gets the type of appliance.
     *
     * @return the type of the appliance in String.
     */
    public abstract String getType();

    public abstract String getParameter(boolean isList);

    public String toString() {
        return this.getName() + "(" + this.getWattage() + "W)" + " in " + this.getLocation();
    }

    public String writeFileFormat() {
        return this.location + "|" + this.name + "|" + this.wattage + "|" + this.getType()
                + "|" + this.getPowerInString() + "|" + this.getParameter(false);
    }

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
