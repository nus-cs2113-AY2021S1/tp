package com.scrumptious.ui;

/**
 * Container for user visible messages.
 */
public class Messages {
    /**
     * General messages.
     */
    //public static final String NEWLINE =  System.lineSeparator();
    public static final String MESSAGE_LOGO = "   _____  _____ _____  _    _ __  __       _   _  \n"
            + "  / ____|/ ____|  __ \\| |  | |  \\/  |     | | (_) \n"
            + " | (___ | |    | |__) | |  | | \\  / |_ __ | |_ _  ___  _   _ ___\n"
            + "  \\___ \\| |    |  _  /| |  | | |\\/| | '_ \\| __| |/ _ \\| | | / __|\n"
            + "  ____) | |____| | \\ \\| |__| | |  | | |_) | |_| | (_) | |_| \\__ \\ \n"
            + " |_____/ \\_____|_|  \\_\\\\____/|_|  |_| .__/ \\__|_|\\___/ \\__,_|___/ \n"
            + "                                    | |\n"
            + "                                    |_|\n";
    public static final String MESSAGE_WELCOME = "WELCOME TO SCRUMPTIOUS";
    public static final String MESSAGE_PROMPT_INPUT = "command> ";
    public static final String MESSAGE_INVALID_IDTYPE = "Task ID entered is not an integer!";
    public static final String MESSAGE_INVALID_DURATION = "Duration must be a multiple of sprint duration. "
           + "Duration must be longer than the project sprint";
}
