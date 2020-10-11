
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
        } else if (StudyIt.getCurrentMode() != Mode.MENU) {
            handleNonGeneralCommand(command,commandType);
        } else {
            ErrorMessage.printUnidentifiableCommand();
        }
    }

    public static void handleNonGeneralCommand(String command, CommandType commandType) {
        Mode currentMode = StudyIt.getCurrentMode();
        try {
            if (currentMode == Mode.BOOKMARK) {
                executeBookmarkModeCommand();
            } else if (currentMode == Mode.TIMETABLE) {
                executeTimetableModeCommand();
            } else if (currentMode == Mode.ACADEMIC) {
                executeAcademicModeCommand(command,commandType);
            } else if (currentMode == Mode.FLASHCARD) {
                executeFlashcardCommand();
            }
        } catch (InvalidCommandException e) {
            // TODO: This part can be changed depending on what exception you guys will have later
            ErrorMessage.printUnidentifiableCommand();
        }
    }

    public static void executeBookmarkModeCommand() throws InvalidCommandException {

    }

    public static void executeTimetableModeCommand() throws InvalidCommandException {

    }

    public static void executeAcademicModeCommand(String command
            ,CommandType commandType) throws InvalidCommandException {
        if (commandType == CommandType.ADD_CONTACT) {
            Ui.printLine("Adding Contact"); //TODO: Remove placeholder line.
        } else if (commandType == CommandType.CHECK_CONTACT) {
            Ui.printLine("Checking Contact"); //TODO: Remove placeholder line.
        } else if (commandType == CommandType.ADD_GRADE) {
            Ui.printLine("Adding Grade"); //TODO: Remove placeholder line.
        } else if (commandType == CommandType.CHECK_GRADE) {
            Ui.printLine("Checking Contact"); //TODO: Remove placeholder line.
        } else {
            ErrorMessage.printUnidentifiableCommand();
        }

    }

    public static void executeFlashcardCommand() throws InvalidCommandException {

    }
}