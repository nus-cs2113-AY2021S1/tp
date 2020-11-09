package ui;

// This class holds the ANSI escape codes used to format console output.
public enum ConsoleColorsEnum {
    BLACK_TEXT("\u001B[30m"),
    RED_TEXT("\u001B[31m"),
    GREEN_TEXT("\u001B[32m"),
    YELLOW_TEXT("\u001B[33m"),
    BLUE_TEXT("\u001B[34m"),
    MAGENTA_TEXT("\u001B[35m"),
    CYAN_TEXT("\u001B[36m"),
    WHITE_TEXT("\u001B[37m"),

    BOLD_BLACK_TEXT("\u001B[30;1m"),
    BOLD_RED_TEXT("\u001B[31;1m"),
    BOLD_GREEN_TEXT("\u001B[32;1m"),
    BOLD_YELLOW_TEXT("\u001B[33;1m"),
    BOLD_BLUE_TEXT("\u001B[34;1m"),
    BOLD_MAGENTA_TEXT("\u001B[35;1m"),
    BOLD_CYAN_TEXT("\u001B[36;1m"),
    BOLD_WHITE_TEXT("\u001B[37;1m"),

    BRIGHT_BLACK_TEXT("\u001B[90m"),
    BRIGHT_RED_TEXT("\u001B[91m"),
    BRIGHT_GREEN_TEXT("\u001B[92m"),
    BRIGHT_YELLOW_TEXT("\u001B[93m"),
    BRIGHT_BLUE_TEXT("\u001B[94m"),
    BRIGHT_MAGENTA_TEXT("\u001B[95m"),
    BRIGHT_CYAN_TEXT("\u001B[96m"),
    BRIGHT_WHITE_TEXT("\u001B[97m"),

    BLACK_TEXT_BG("\u001B[40m"),
    RED_TEXT_BG("\u001B[41m"),
    GREEN_TEXT_BG("\u001B[42m"),
    YELLOW_TEXT_BG("\u001B[43m"),
    BLUE_TEXT_BG("\u001B[44m"),
    MAGENTA_TEXT_BG("\u001B[45m"),
    CYAN_TEXT_BG("\u001B[46m"),
    WHITE_TEXT_BG("\u001B[47m"),

    BOLD("\u001B[1m"),

    RESET_TEXT("\u001b[37m");

    private String ansiCode;

    /**
     * Constructor for ConsoleColorsEnum object which sets the exact ANSI code it holds.
     */
    ConsoleColorsEnum(String ansiCode) {
        this.ansiCode = ansiCode;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString() {
        return ansiCode;
    }
}
