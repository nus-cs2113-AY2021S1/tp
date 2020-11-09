package seedu.smarthomebot.data.appliance.type;

import seedu.smarthomebot.data.appliance.Appliance;
import seedu.smarthomebot.data.location.LocationList;
import seedu.smarthomebot.logic.commands.exceptions.InvalidApplianceNameException;
import seedu.smarthomebot.logic.commands.exceptions.LocationNotFoundException;

//@@author leonlowzd

/**
 * Represents Fan type extended from Appliance.
 */
public class Fan extends Appliance {

    public static final String TYPE_WORD = "fan";
    private static final int LOWER_BOUND = 0;
    private static final int UPPER_BOUND = 4;
    private String speed;

    /**
     * Constructor for Fan type.
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
    public Fan(String name, String location, String wattage, LocationList locationList)
            throws InvalidApplianceNameException, LocationNotFoundException {
        super(name, location, wattage, locationList);
        this.speed = "1";
    }

    /**
     * Method to return the set speed of the Fan.
     *
     * @return the set set speed of the appliance in String.
     */
    @Override
    public String getParameter(boolean isList) {
        if (isList) {
            return "Speed " + getParameter(false);
        } else {
            return speed;
        }
    }

    /**
     * Method to set the speed of appliance from user text file.
     *
     * @param loadedSpeed speed to set.
     */
    public void getSpeedFromLoadFile(String loadedSpeed) {
        setSpeed(loadedSpeed);
    }

    /**
     * Method to set the temperature of appliance from user input.
     *
     * @param speed speed to set.
     * @return the corresponding printout message after setting speed in String.
     */
    public boolean setSpeed(String speed) {
        boolean isSuccess = true;
        if (speed.isEmpty()) {
            // default speed will be set.
        } else if (isParameterValid(speed, LOWER_BOUND, UPPER_BOUND)) {
            // user input speed will be set.
            this.speed = speed;
        } else {
            // user inputs an invalid speed.
            isSuccess = false;
        }
        return isSuccess;
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
    @Override
    public String toString() {
        String speedStatement;
        if (appliancePower.getStatus()) {
            speedStatement = "@ " + getParameter(true);
        } else {
            speedStatement = "";
        }
        return super.toString() + speedStatement;
    }

}
