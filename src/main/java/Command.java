public class Command {
    public static void executeCommand(String command, CommandType commandType) {
        if (commandType == CommandType.EXIT_PROGRAM) {
            Ui.printExit();
        }else if (commandType == CommandType.EXIT_MODE) {
            Ui.exitMode();
        } else if (commandType == CommandType.LOCATION) {
            Ui.printLocation();
        } else if (commandType == CommandType.CHANGE_MODE) {
            Ui.changeMode(command);
        } else {
            ErrorMessage.printUnidentifiableCommand();
        }
    }
}