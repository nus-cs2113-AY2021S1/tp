package seedu.duke.common;

public class Messages {
    public static final String LINE = "-".repeat(Math.max(0, 54)) + "\n";
    public static final String DIVIDER = "=".repeat(Math.max(0, 50));

    public static final String MESSAGE_REPEATED_LOCATION = "Input location already exist.";
    public static final String MESSAGE_LOCATION_DOES_NOT_EXIST = "Input location does not exist.";
    public static final String MESSAGE_REPEATED_APPLIANCE_NAME = "Name of appliance already exist in Smart Home.";
    public static final String MESSAGE_INVALID_APPLIANCE_TYPE = "Appliance type is not supported.";
}
