package studyit;

import exceptions.InvalidModeException;

public class CommandParser {

    /**
     * Standardize the command to lowercase and trimmed
     *
     * @param text String text
     * @return the standardized command
     */
    public static String standardizeCommand(String text) {
        return text.trim().toLowerCase();
    }

    /**
     * Identifies the general commands
     * @param command raw string of the user input
     * @return the command type of the command
     */
    public static CommandType getCommandType(String command) {
        String commandModified = standardizeCommand(command);

        if ((StudyIt.getCurrentMode() == Mode.MENU) && (commandModified.equals("exit"))) {
            return CommandType.EXIT_PROGRAM;
        } else if (commandModified.equals("exit")) {
            return CommandType.EXIT_MODE;
        } else if (commandModified.equals("location")) {
            return CommandType.LOCATION;
        } else if (commandModified.startsWith("cd")) {
            return CommandType.CHANGE_MODE;
        } else if (commandModified.equals("help")) {
            return CommandType.HELP;
        } else if (commandModified.equals("highlight")) {
            return CommandType.HIGHLIGHT;
        } else {
            return CommandType.UNIDENTIFIABLE;
        }
    }

    /**
     * Parses which mode to switch to for "cd" command
     *
     * @param command raw string of user input
     * @return the destination mode
     * @throws InvalidModeException
     */
    public static Mode getDestinationMode(String command) throws InvalidModeException {
        String commandModified = standardizeCommand(command);
        int initialLength = "cd".length();
        String destination = commandModified.substring(initialLength).trim();

        if (destination.equals("1") || destination.equals(ModeNames.MENU_NAME)) {
            return Mode.MENU;
        } else if (destination.equals("2") || destination.equals(ModeNames.BOOKMARK_NAME)) {
            return Mode.BOOKMARK;
        } else if (destination.equals("3") || destination.equals(ModeNames.TIMETABLE_NAME)) {
            return Mode.TIMETABLE;
        } else if (destination.equals("4") || destination.equals(ModeNames.ACADEMIC_NAME)) {
            return Mode.ACADEMIC;
        } else if (destination.equals("5") || destination.equals(ModeNames.FLASHCARD_NAME)) {
            return Mode.FLASHCARD;
        } else {
            StudyItLog.logger.info("Invalid mode was chosen.");
            throw new InvalidModeException();
        }
    }
}