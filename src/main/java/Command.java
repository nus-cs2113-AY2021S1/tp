public class Command {
    public static void executeCommand(String command, CommandType commandType) {
        if (commandType == CommandType.EXIT_PROGRAM) {
            Ui.printExit();
        } else if (commandType == CommandType.EXIT_MODE) {
            Ui.exitMode();
        } else if (commandType == CommandType.LOCATION) {
            Ui.printLocation();
        } else if (commandType == CommandType.CHANGE_MODE) {
            Ui.changeModeCommand(command);
        } else if (commandType == CommandType.HELP) {
            HelpMessage.printHelpMessage();
        } else if (StudyIt.getCurrentMode() != Mode.MENU){
            handleNonGeneralCommand();
        } else {
            ErrorMessage.printUnidentifiableCommand();
        }
    }

    public static void handleNonGeneralCommand() {
        Mode currentMode = StudyIt.getCurrentMode();
        try {
            if (currentMode == Mode.BOOKMARK) {
                executeBookmarkModeCommand();
            } else if (currentMode == Mode.TIMETABLE) {
                executeTimetableModeCommand();
            } else if (currentMode == Mode.ACADEMIC) {
                executeAcademicModeCommand();
            } else if (currentMode == Mode.FLASHCARD) {
                executeFlashcardCommand();
            }
        } catch (InvalidCommandException e) {
            // TODO: This part can be changed depending on what exception you guys will have later
            ErrorMessage.printUnidentifiableCommand();
        }
    }

    public static void executeBookmarkModeCommand() throws InvalidCommandException {}

    public static void executeTimetableModeCommand() throws InvalidCommandException {}

    public static void executeAcademicModeCommand() throws InvalidCommandException {}

    public static void executeFlashcardCommand() throws InvalidCommandException {}
}