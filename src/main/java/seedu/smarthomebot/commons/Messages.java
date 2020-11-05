package seedu.smarthomebot.commons;

public class Messages {

    public static final String LINE = "-".repeat(Math.max(0, 52)) + "\n";
    public static final String DIVIDER = "=".repeat(Math.max(0, 52));
    public static final String MESSAGE_WELCOME = "Welcome to your SmartHomeBot V2.1!";
    public static final String MESSAGE_GOODBYE = "Good bye!";
    public static final String MESSAGE_IMPORT = "Importing data........Completed!";
    public static final String MESSAGE_EXPORT = "Exporting data........Completed!";
    public static final String MESSAGE_APPLIANCE_TYPE_NOT_EXIST =
            "Invalid type of appliance entered. Only aircon, fan, light and smartplug are currently accepted as type.";
    public static final String MESSAGE_APPLIANCE_EXIST = "Appliance name already exists.";
    public static final String MESSAGE_LOCATION_NOT_EXIST = "Location does not exist.";
    public static final String MESSAGE_APPLIANCE_LOCATION_CONFLICT =
            "Appliance name already exist in the location list.";
    public static final String MESSAGE_TOTAL_POWER_USAGE = "\n\nTotal power consumption: ";
    public static final String MESSAGE_LIST_LOCATIONS = "Here are the Location in your list:";
    public static final String MESSAGE_LIST_APPLIANCES = "Here are the Appliances in your list:";
    public static final String MESSAGE_POWER_USAGE = "Here are the power usage consumption:";
    public static final String MESSAGE_LIST_NO_LOCATIONS = "There is currently no Locations in the list.";
    public static final String MESSAGE_LIST_NO_APPLIANCES = "There is currently no Appliances in the list.";
    public static final String MESSAGE_USAGE_RESET = "Power usage has been reset!";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid Command Format";
    public static final String MESSAGE_INVALID_ADD_COMMAND =
            "Please follow this order, add NAME l/[LOCATION_NAME] w/[WATTS] t/[TYPE_OF_APPLIANCE]";
    public static final String MESSAGE_INVALID_LIST_COMMAND =
            "Please enter either 'list appliance' or 'list location' or 'list appliance l/LOCATION_NAME'";
    public static final String MESSAGE_VALUE_NOT_NUMBER =
            "Please enter a valid numerical value ranging from 1-9999 for the wattage.";
    public static final String MESSAGE_PARAMETER_INVALID =
            "Please enter a valid numerical value.";
    public static final String MESSAGE_POWER_EXCEEDED =
            "Appliance wattage is not supported. Please enter between 1 to 9999.";
    public static final String MESSAGE_TIME_FORMAT_ERROR = "Time format is wrong.";
    public static final String MESSAGE_APPLIANCE_PREVIOUSLY_ON = " is already ON. ";
    public static final String MESSAGE_APPLIANCE_PREVIOUSLY_OFF = " is already OFF. ";
    public static final String MESSAGE_NO_PARAMETER_IN_ON_BY_LOCATION =
            "There should be no parameter for on by location.";
    public static final String MESSAGE_FILE_CORRUPTED = "Data file is corrupted, some data entry will not be entered.";
    public static final String MESSAGE_APPLIANCE_OR_LOCATION_NOT_EXIST =
            "Appliance or Location does not exist in the list.";
    public static final String MESSAGE_INVALID_TEMPERATURE_AC =
            "Invalid Temperature is inputted, ensure that it is within 16-30 degrees.\n"
                    + "Previous set temperature will be set. ";
    public static final String MESSAGE_INVALID_FAN_SPEED =
            "Invalid speed is inputted, ensure that it is within 1-3 speed.\n"
                    + "Previous set speed will be set.";
    public static final String MESSAGE_ILLEGAL_CHARACTER = "Illegal Character space or /  or | detected in";
    public static final String MESSAGE_EMPTY_PARAMETER =
            "Empty Parameter detected! Please follow format and enter require parameters.";
}
