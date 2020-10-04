package seedu.duke.parsers;

public enum CommandChecker {
    HELP,
    SETTINGS,
    ADD,
    LIST,
    HISTORY,
    STATS,
    REVIEW,
    QUIZ,
    CLEAR,
    EXIT,
    UNRECOGNISED;

    /**
     *  Figure out the command type from userInput.
     *
     * @param userInput string containing user input command
     * @return the type of command detected
     */
    public static CommandChecker extractCommandType(String userInput) {
        if (userInput.toLowerCase().trim().startsWith("help")) {
            return CommandChecker.HELP;
        } else if (userInput.toLowerCase().trim().startsWith("settings")) {
            return CommandChecker.SETTINGS;
        } else if (userInput.toLowerCase().trim().startsWith("add")) {
            return CommandChecker.ADD;
        } else if (userInput.toLowerCase().trim().startsWith("list")) {
            return CommandChecker.LIST;
        } else if (userInput.toLowerCase().trim().startsWith("history")) {
            return CommandChecker.HISTORY;
        } else if (userInput.toLowerCase().trim().startsWith("stats")) {
            return CommandChecker.STATS;
        } else if (userInput.toLowerCase().trim().startsWith("review")) {
            return CommandChecker.REVIEW;
        } else if (userInput.toLowerCase().trim().startsWith("quiz")) {
            return CommandChecker.QUIZ;
        } else if (userInput.toLowerCase().trim().startsWith("clear")) {
            return CommandChecker.CLEAR;
        } else if (userInput.toLowerCase().trim().startsWith("exit")) {
            return CommandChecker.EXIT;
        } else {
            return CommandChecker.UNRECOGNISED;
        }
    }
}