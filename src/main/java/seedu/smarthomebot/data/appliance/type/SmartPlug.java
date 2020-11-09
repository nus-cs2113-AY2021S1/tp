package seedu.smarthomebot.data.appliance.type;

import seedu.smarthomebot.data.appliance.Appliance;
import seedu.smarthomebot.data.location.LocationList;
import seedu.smarthomebot.logic.commands.exceptions.InvalidApplianceNameException;
import seedu.smarthomebot.logic.commands.exceptions.LocationNotFoundException;

//@@author leonlowzd

/**
 * Represents SmartPlug type extended from Appliance.
 */
public class SmartPlug extends Appliance {

    public static final String TYPE_WORD = "smartplug";

    /**
     * Constructor for SmartPlug type.
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
    public SmartPlug(String name, String location, String wattage, LocationList locationList)
            throws InvalidApplianceNameException, LocationNotFoundException {
        super(name, location, wattage, locationList);
    }

    /**
     * Returns the type of appliance.
     *
     * @return the type of appliance in String.
     */
    public String getType() {
        return TYPE_WORD;
    }

    /**
     * Returns the set temperature of the Air conditioner.
     *
     * @return the set set temperature of the appliance in String.
     */
    @Override
    public String getParameter(boolean isList) {
        return "None";
    }


}
