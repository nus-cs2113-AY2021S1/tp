package seedu.smarthomebot.commons;

import seedu.smarthomebot.logic.commands.AddCommand;
import seedu.smarthomebot.logic.commands.CreateCommand;
import seedu.smarthomebot.logic.commands.DeleteCommand;
import seedu.smarthomebot.logic.commands.ExitCommand;
import seedu.smarthomebot.logic.commands.HelpCommand;
import seedu.smarthomebot.logic.commands.ListCommand;
import seedu.smarthomebot.logic.commands.OffCommand;
import seedu.smarthomebot.logic.commands.OnCommand;
import seedu.smarthomebot.logic.commands.RemoveCommand;
import seedu.smarthomebot.logic.commands.ResetCommand;
import seedu.smarthomebot.logic.commands.UsageCommand;

public class Messages {

    public static final String LINE = "-".repeat(Math.max(0, 52)) + "\n";
    public static final String DIVIDER = "=".repeat(Math.max(0, 52));
    public static final String MESSAGE_WELCOME = "Welcome to your SmartHomeBot V2.0!";
    public static final String MESSAGE_GOODBYE = "Good bye!";
    public static final String MESSAGE_IMPORT = "Importing data........Completed!";
    public static final String MESSAGE_EXPORT = "Exporting data........Completed!";
    public static final String MESSAGE_APPLIANCE_TYPE_NOT_EXIST = "Type Entered does not exist.";
    public static final String MESSAGE_APPLIANCE_EXIST = "Appliance name already exists.";
    public static final String MESSAGE_LOCATION_NOT_EXIST = "Location does not exist.";
    public static final String MESSAGE_APPLIANCE_LOCATION_CONFLICT =
            "Appliance name already exist in the location list.";
    public static final String MESSAGE_TOTAL_POWER_USAGE = "\n\nTotal power consumption: ";
    public static final String MESSAGE_LIST_LOCATIONS = "Here are the location you have entered:";
    public static final String MESSAGE_LIST_APPLIANCES = "Here are the appliances in your list:";
    public static final String MESSAGE_POWER_USAGE = "Here are the power usage consumption:";
    public static final String MESSAGE_LIST_NO_LOCATIONS = "There is currently no locations in the list.";
    public static final String MESSAGE_LIST_NO_APPLIANCES = "There is currently no appliances in the list.";
    public static final String MESSAGE_USAGE_RESET = "Resetting previous recorded usage.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid Command Format";
    public static final String MESSAGE_INVALID_ADD_COMMAND =
            "Please follow this order, add NAME l/[LOCATION_NAME] w/[WATTS] t/[TYPE_OF_APPLIANCE]";
    public static final String MESSAGE_INVALID_LIST_COMMAND =
            "Please enter either 'list appliance' or 'list location' or 'list appliance l/LOCATION_NAME'";
    public static final String MESSAGE_VALUE_NOT_NUMBER = "Please enter a valid numerical value.";
    public static final String MESSAGE_POWER_EXCEEDED =
            "Appliance wattage is uncommon. Please enter between 0 to 9999.";
    public static final String MESSAGE_TIME_FORMAT_ERROR = "Time format is wrong.";
    public static final String MESSAGE_APPLIANCE_PREVIOUSLY_ON = "The appliance is already ON. ";
    public static final String MESSAGE_APPLIANCE_PREVIOUSLY_OFF = "The appliance is already OFF. ";
    public static final String MESSAGE_NO_PARAMETER_IN_ON_BY_LOCATION =
            "There should be no parameter for on by location.";
    public static final String MESSAGE_FILE_CORRUPTED = "Data file is corrupted, some data will not be entered";
    public static final String MESSAGE_APPLIANCE_OR_LOCATION_NOT_EXIST =
            "Appliance or Location does not exist in the list.";
    public static final String MESSAGE_INVALID_TEMPERATURE_AC =
            "Invalid Temperature is inputted, ensure that it is within 16-30 degrees.\n"
                    + "Previous set temperature will be set. ";
    public static final String MESSAGE_INVALID_FAN_SPEED =
            "Invalid speed is inputted, ensure that it is within 1-3 speed.\n"
                    + "Previous set speed will be set. ";
    public static final String MESSAGE_ILLEGAL_CHARACTER = "Illegal Character space or / detected in";
    public static final String MESSAGE_EMPTY_PARAMETER =
            "Empty Parameter detected! Please follow format and enter require parameters.";
    public static final String MESSAGE_HELP = LINE + "Here is the list of commands available:\n" + LINE
            + "\t1. " + HelpCommand.MESSAGE_USAGE + "\n"
            + "\t2. " + CreateCommand.MESSAGE_USAGE + "\n"
            + "\t3. " + RemoveCommand.MESSAGE_USAGE + "\n"
            + "\t4. " + AddCommand.MESSAGE_USAGE + "\n"
            + "\t5. " + DeleteCommand.MESSAGE_USAGE + "\n"
            + "\t6. " + OnCommand.MESSAGE_USAGE + "\n"
            + "\t7. " + OffCommand.MESSAGE_USAGE + "\n"
            + "\t8. " + ListCommand.MESSAGE_USAGE + "\n"
            + "\t9. " + UsageCommand.MESSAGE_USAGE + "\n"
            + "\t10. " + ResetCommand.MESSAGE_USAGE + "\n"
            + "\t11. " + ExitCommand.MESSAGE_USAGE + "\n";

}
