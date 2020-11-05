package seedu.smarthomebot.data.appliance.type;

import seedu.smarthomebot.data.appliance.Appliance;
import seedu.smarthomebot.data.location.LocationList;
import seedu.smarthomebot.logic.commands.exceptions.InvalidApplianceNameException;
import seedu.smarthomebot.logic.commands.exceptions.LocationNotFoundException;

//@@author leonlowzd

/**
 * Represents Lights type extended from Appliance.
 */
public class Lights extends Appliance {

    public static final String TYPE_WORD = "light";

    /**
     * Constructor for light type.
     *
     * @param name         name of appliance.
     * @param location     location of appliance.
     * @param wattage      wattage of appliance.
     * @param locationList locations available in SmartHomeBot, appliance will only be created:
     *                     Appliance will only be created when:
     *                     1. location inputted is present in the locationList.
     *                     2. User inputted name does not exist in locationList.
     */
    public Lights(String name, String location, String wattage, LocationList locationList)
            throws InvalidApplianceNameException, LocationNotFoundException {
        super(name, location, wattage, locationList);
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
     * Method to return the set temperature of the Air conditioner.
     *
     * @return the set set temperature of the appliance in String.
     */
    public String getParameter(boolean isList) {
        return "None";
    }


}
